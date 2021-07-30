package com.maharaja.todo_sonal.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maharaja.todo_sonal.R
import com.maharaja.todo_sonal.model.ToDo

class Adapter(private val userList : ArrayList<ToDo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){


        val todo : TextView = itemView.findViewById(R.id.todoitemtext)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.todo_cardview,
            parent,false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.todo.text = currentitem.todo

    }

    override fun getItemCount(): Int {

        return userList.size
    }



}
