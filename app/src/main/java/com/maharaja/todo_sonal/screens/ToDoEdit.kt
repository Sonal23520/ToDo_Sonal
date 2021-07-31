package com.maharaja.todo_sonal.screens

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.maharaja.todo_sonal.R


class ToDoEdit : Fragment() {

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

        return view
    }


}