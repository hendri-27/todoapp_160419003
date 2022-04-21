package com.ubaya.todoapp_160419003.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubaya.todoapp_160419003.model.TodoDatabase

val DB_NAME = "newtodb"

fun buildDb(context: Context) = Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME)
    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
    .build()

val MIGRATION_1_2 = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
             "ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL"
        )
    }
}

val MIGRATION_2_3 = object : Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL"
        )
    }
}
// Karena tidak ada data type boolean pada database sehingga lebih efektif menggunakan langsung dengan angka 0 atau 1 yang melambangkan false atau true tanpa melakukan konversi.