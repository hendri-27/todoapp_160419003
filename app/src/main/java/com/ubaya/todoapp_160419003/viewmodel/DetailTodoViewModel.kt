package com.ubaya.todoapp_160419003.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.ubaya.todoapp_160419003.model.Todo
import com.ubaya.todoapp_160419003.model.TodoDatabase
import com.ubaya.todoapp_160419003.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val todoLD = MutableLiveData<Todo>()

    fun addTodo(list:List<Todo>){
        launch {
            val db = buildDb(getApplication())
            db.todoDao().insertAll(*list.toTypedArray())
        }
    }

    override val coroutineContext:CoroutineContext get() = job + Dispatchers.Main

    fun fetch(uuid:Int){
        launch {
            val db = buildDb(getApplication())
            todoLD.value = db.todoDao().selectTodo(uuid)
        }
    }

    fun update(id:Int,title:String,notes:String,priority:Int, isDone:Int){
        launch {
            val db = buildDb(getApplication())
            db.todoDao().update(id,title,notes,priority, isDone)
        }
    }
}