package com.example.todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var myRV: RecyclerView
    private lateinit var rvAdapter: ToDoRecycler
    private lateinit var todoList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.title = "To Do App"
        this.actionBar

        todoList = ArrayList()

        val fButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        myRV = findViewById(R.id.rvMain)


        fButton.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Add new task")
            val input = EditText(this)
            input.hint = "Enter your new task"
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            builder.setPositiveButton("Add", DialogInterface.OnClickListener{ _, _ ->
                val text = input.text.toString()
                if(text.isNotEmpty())
                    todoList.add(text)
            })

            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener{_, _ ->})

            builder.show()

            rvAdapter = ToDoRecycler(todoList)
            myRV.adapter = rvAdapter
            myRV.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.trash, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        rvAdapter.selectedTodo.sort()
        for(i in rvAdapter.selectedTodo.reversed()){
            todoList.removeAt(i)
        }
        rvAdapter.bool = false
        rvAdapter.selectedTodo.clear()
        rvAdapter.notifyDataSetChanged()

        return super.onOptionsItemSelected(item)
    }
}