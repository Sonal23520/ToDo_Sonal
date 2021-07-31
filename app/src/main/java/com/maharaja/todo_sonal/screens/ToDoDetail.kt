package com.maharaja.todo_sonal.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import com.maharaja.todo_sonal.R
import com.maharaja.todo_sonal.controller.ToDoController


class ToDoDetail : Fragment() {

    private val toDoController = ToDoController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_to_do_detail, container, false)

        ////////////TOOLBAR_CONFIG////////////

        val toolbar = view.findViewById<Toolbar>(R.id.appbar)
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        toolbar.setTitle("Details")
        toolbar.setNavigationIcon(R.drawable.backicon)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        ///////////////GET/SET_ARGS/////////////////

       var args = arguments?.let { ToDoDetailArgs.fromBundle(it) }
        view.findViewById<TextView>(R.id.detailtext).text = args?.detailTodoText

        ////////////////DELETE_TODO//////////////////
        view.findViewById<Button>(R.id.detail_delete_btn).setOnClickListener {
            var delete_result = toDoController.deleteToDo(args!!.detailTodoText)
            if (delete_result){
                Toast.makeText(context,"ToDo Delete Success", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.navigate_to_toDoHome)
            }
        }

        /////////////EDIT_TODO//////////////////////
        view.findViewById<Button>(R.id.detail_edit_btn).setOnClickListener {
            val action =ToDoDetailDirections.navigateDetailToEdit(args!!.detailTodoText)
            Navigation.findNavController(view).navigate(action)
        }

        return view
    }

}