package com.example.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemRowBinding

class ToDoRecycler(private val toDoList: List<String>): RecyclerView.Adapter<ToDoRecycler.ItemViewHolder>() {

    val selectedTodo = arrayListOf<Int>()
    var bool: Boolean = false

    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val todo = toDoList[position]


        holder.binding.apply {
            toDoName.text = todo
            if(!bool)
                checkBox.isChecked = false

            checkBox.setOnClickListener {
                bool = true
                if(checkBox.isChecked)
                    selectedTodo.add(position)
                else
                    selectedTodo.remove(position)
            }
        }

    }
    override fun getItemCount() = toDoList.size

    fun removeCheck(){

    }

}