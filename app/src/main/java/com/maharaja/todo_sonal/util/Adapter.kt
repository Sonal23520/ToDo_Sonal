package com.maharaja.todo_sonal.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.maharaja.todo_sonal.R
import com.maharaja.todo_sonal.model.ToDo
import com.maharaja.todo_sonal.controller.ToDoHomeDirections

class Adapter(private val userList : ArrayList<ToDo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val todo : TextView = itemView.findViewById(R.id.todoitemtext)
        var date:String = ""

        init {
            itemView.setOnClickListener {
               val action = ToDoHomeDirections.navigateToToDoDetail(todo.text as String,date)
                Navigation.findNavController(itemView).navigate(action)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.todo_cardview,
            parent,false)

        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.todo.text = userList[position].todo
        holder.date= userList[position].date.toString()

    }

    override fun getItemCount(): Int {

        return userList.size
    }



}
