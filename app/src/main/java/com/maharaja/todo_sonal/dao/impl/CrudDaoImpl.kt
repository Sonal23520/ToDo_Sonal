package com.maharaja.todo_sonal.dao.impl

import com.google.firebase.database.*
import com.maharaja.todo_sonal.dao.CrudDao
import com.maharaja.todo_sonal.model.ToDo

class CrudDaoImpl : CrudDao {
    private val database = FirebaseDatabase.getInstance().getReference("ToDos")
    override fun addToDo(todo: ToDo): Boolean {
        todo.date?.let { database.child(it).setValue(todo).addOnSuccessListener(return true) }
        return false
    }

    override fun getToDo():DatabaseReference{
        return database
    }

    override fun deleteToDo(todo:String):Boolean{
        database.child(todo).removeValue().addOnSuccessListener(return true)
    }

    override fun updateToDo(todo:ToDo):Boolean{
        todo.date?.let { database.child(it).setValue(todo).addOnSuccessListener(return true) }

        return false
    }

}