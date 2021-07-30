package com.maharaja.todo_sonal.controller

import com.google.firebase.database.FirebaseDatabase
import com.maharaja.todo_sonal.model.ToDo

class ToDoControler {
    private val database = FirebaseDatabase.getInstance().getReference("ToDos")
    fun addToDo(todo: ToDo): Boolean {
//        var result=false
        todo.todo?.let { database.child(it).setValue(todo).addOnSuccessListener(return true)}
        return false
    }
}