package com.ubaya.todoapp_160419003.model

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo WHERE is_done = 0 ORDER BY priority DESC")
    suspend fun selectAllTodo():List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id")
    suspend fun selectTodo(id:Int):Todo

    @Query("UPDATE todo SET title = :title, notes = :notes, priority = :priority, is_done = :isDone WHERE uuid = :id")
    suspend fun update(id:Int,title:String,notes:String,priority:Int, isDone:Int)

    @Delete
    suspend fun deleteTodo(todo:Todo)
}