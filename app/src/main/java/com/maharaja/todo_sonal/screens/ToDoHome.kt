package com.maharaja.todo_sonal.screens

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.maharaja.todo_sonal.R


class ToDoHome : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_to_do_home, container, false)


        val toolbar = view.findViewById<Toolbar>(R.id.appbar)
        toolbar.title = "ToDo"
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.addmenu, menu)
        super.onCreateOptionsMenu(menu,inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.addtodoicon){
            view?.let { Navigation.findNavController(it).navigate(R.id.navigate_to_toDoAdd) }
        }
        return true
    }


}