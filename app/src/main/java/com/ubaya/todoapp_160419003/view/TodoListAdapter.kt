package com.ubaya.todoapp_160419003.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.todoapp_160419003.R
import com.ubaya.todoapp_160419003.model.Todo
import kotlinx.android.synthetic.main.layout_item_todo.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick: (Todo) -> Unit):RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_item_todo, parent, false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        with(holder.view){
            val priority = when(todo.priority){
                1->"Low"
                2->"Medium"
                else->"HIGH"
            }
            checkTask.text = "[${priority}] ${todo.title}"
            checkTask.isChecked = false
            checkTask.setOnCheckedChangeListener { compoundButton, value ->
                if (value) adapterOnClick(todo)
            }
            buttonEdit.setOnClickListener {
                val action = TodoListFragmentDirections.actionEditTodo(todo.uuid)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = todoList.size

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
}