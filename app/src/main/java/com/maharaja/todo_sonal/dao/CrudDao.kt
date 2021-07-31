package com.maharaja.todo_sonal.dao

import com.google.firebase.database.DatabaseReference
import com.maharaja.todo_sonal.model.ToDo

interface CrudDao {
    fun addToDo(todo: ToDo): Boolean
    fun getToDo(): DatabaseReference
    fun deleteToDo(todo:String):Boolean
    fun updateToDo(todo:ToDo):Boolean
}