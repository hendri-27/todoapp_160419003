package com.ubaya.todoapp_160419003.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.todoapp_160419003.model.Todo
import com.ubaya.todoapp_160419003.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext:CoroutineContext get() = job + Dispatchers.Main

    fun refresh(){
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    fun clearTask(todo:Todo){
        launch {
            val db = buildDb(getApplication())
//            db.todoDao().deleteTodo(todo)
            todo.is_done = 1
            db.todoDao().update(todo.uuid,todo.title,todo.notes,todo.priority,todo.is_done)

            todoLD.value = db.todoDao().selectAllTodo()
        }
    }
}