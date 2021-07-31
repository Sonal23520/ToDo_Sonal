package com.maharaja.todo_sonal.controller

import android.os.Build
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import com.maharaja.todo_sonal.R
import com.maharaja.todo_sonal.dao.impl.CrudDaoImpl
import com.maharaja.todo_sonal.model.ToDo


class ToDoEdit : Fragment() {

    private val toDoController = CrudDaoImpl()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_to_do_edit, container, false)

        /////////////TOOLBAR_CONFIG////////////////
        val toolbar = view.findViewById<Toolbar>(R.id.appbar)
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        toolbar.setTitle("Edit")
        toolbar.setNavigationIcon(R.drawable.backicon)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        /////////////GET/SET_ARGS//////////////////
        var args = arguments?.let { ToDoEditArgs.fromBundle(it) }
        view.findViewById<EditText>(R.id.edittodotext).text= Editable.Factory.getInstance().newEditable(
            args?.editTodoText
        )
        /////////////EDIT_TODO////////////////////
        view.findViewById<Button>(R.id.btnedittodo).setOnClickListener {
            val editText = view.findViewById<EditText>(R.id.edittodotext).text.toString()
            toDoController.updateToDo(ToDo(editText, args?.editTodoDate))
            Toast.makeText(context,"ToDo Edit Success", Toast.LENGTH_SHORT).show()
            val action = ToDoEditDirections.navigateEditToDetail(editText, args?.editTodoDate.toString())
            Navigation.findNavController(view).navigate(action)

        }

        return view
    }


}