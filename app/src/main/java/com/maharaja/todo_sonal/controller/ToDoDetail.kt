package com.maharaja.todo_sonal.controller

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import com.maharaja.todo_sonal.R
import com.maharaja.todo_sonal.dao.impl.CrudDaoImpl


class ToDoDetail : Fragment() {

    private val toDoController = CrudDaoImpl()

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
        toolbar.setNavigationOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_toDoHome)

        }

        ///////////////GET/SET_ARGS/////////////////

       var args = arguments?.let { ToDoDetailArgs.fromBundle(it) }
        view.findViewById<TextView>(R.id.detailtext).text = args?.detailTodoText

        /////////////////ALERT//////////////////
        var alert = context?.let {
            AlertDialog.Builder(it)
                .setIcon(R.drawable.delete_icon)
                .setTitle("Delete")
                .setMessage("Are you sure?")
                .setPositiveButton("Delete",DialogInterface.OnClickListener {
                        dialogInterface, i ->

                        var delete_result = toDoController.deleteToDo(args!!.detailTodoDate)
                        if (delete_result){
                        Toast.makeText(context,"ToDo Delete Success", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(view).navigate(R.id.navigate_to_toDoHome)
                        }

                })
                .setNegativeButton("Cancel",null)

        }

        ////////////////DELETE_TODO//////////////////
        view.findViewById<Button>(R.id.detail_delete_btn).setOnClickListener {
            alert!!.show()
        }

        /////////////EDIT_TODO//////////////////////

        view.findViewById<Button>(R.id.detail_edit_btn).setOnClickListener {
            val action =ToDoDetailDirections.navigateDetailToEdit(args!!.detailTodoText,args.detailTodoDate)
            Navigation.findNavController(view).navigate(action)
        }

        return view
    }

}