package com.maharaja.todo_sonal.controller

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.maharaja.todo_sonal.R
import com.maharaja.todo_sonal.dao.CrudDao

import com.maharaja.todo_sonal.dao.impl.CrudDaoImpl
import com.maharaja.todo_sonal.model.ToDo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ToDoAdd : Fragment() {

    private val toDoController:CrudDao = CrudDaoImpl()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_to_do_add, container, false)

        ////////////TOOLBAR_CONFIG////////

        val toolbar = view.findViewById<Toolbar>(R.id.appbar)
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        toolbar.title = "Add ToDo"
        toolbar.setNavigationIcon(R.drawable.backicon)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        ////////////ALERT//////////////
        var alert = context?.let {
            AlertDialog.Builder(it)
                .setIcon(R.drawable.warning)
                .setTitle("Warning")
                .setMessage("Please Fill Fields")
        }

        /////////ADD_TODO_LISTNER///////

        view.findViewById<Button>(R.id.btnaddtodo).setOnClickListener {
            val todotext = view.findViewById<EditText>(R.id.addtodotext).text.toString()
            if (todotext.isEmpty()){
                alert!!.show()
            }else{
            val result =
                toDoController.addToDo(ToDo(todotext, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
            if (result==true){
                Toast.makeText(context,"ToDo Add Success",Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.navigate_to_toDoHome)
            }
            }
        }

        return view
    }


}