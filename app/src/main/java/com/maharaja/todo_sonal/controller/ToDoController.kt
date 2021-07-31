package com.maharaja.todo_sonal.controller

import android.util.Log
import com.google.firebase.database.*
import com.maharaja.todo_sonal.model.ToDo

class ToDoController {
    var count = 0
    private val database = FirebaseDatabase.getInstance().getReference("ToDos")
    fun addToDo(todo: ToDo): Boolean {
        todo.date?.let { database.child(it).setValue(todo).addOnSuccessListener(return true) }
        return false
    }

    fun getToDo():DatabaseReference{
        return database
    }

    fun deleteToDo(todo:String):Boolean{
        database.child(todo).removeValue().addOnSuccessListener(return true)
    }

    fun updateToDo(todo:ToDo):Boolean{
        todo.date?.let { database.child(it).setValue(todo).addOnSuccessListener(return true) }

        return false
    }

}