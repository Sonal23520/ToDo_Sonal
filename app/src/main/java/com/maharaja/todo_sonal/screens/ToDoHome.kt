package com.maharaja.todo_sonal.screens

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.maharaja.todo_sonal.util.Adapter
import com.maharaja.todo_sonal.R
import com.maharaja.todo_sonal.controller.ToDoController
import com.maharaja.todo_sonal.model.ToDo


class ToDoHome : Fragment() {

    private var database = ToDoController().getToDo()
    private lateinit var todoRecyclerview : RecyclerView
    private lateinit var todoArrayList : ArrayList<ToDo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_to_do_home, container, false)

        //////////////TOOL_BAR_CONFIG///////////////
        val toolbar = view.findViewById<Toolbar>(R.id.appbar)
        toolbar.title = "ToDo"
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)

        //////////////RECYCLER_VIEW_CONFIG//////////////////
        todoRecyclerview = view.findViewById(R.id.todolistview)
        todoRecyclerview.layoutManager = LinearLayoutManager(context)
        todoRecyclerview.setHasFixedSize(true)
        todoArrayList = arrayListOf<ToDo>()
        getToDoData()

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

    private fun getToDoData() {

        database.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (todoSnapshot in snapshot.children){

                        val todo = todoSnapshot.getValue(ToDo::class.java)
                        todoArrayList.add(todo!!)
                    }
                    todoRecyclerview.adapter = Adapter(todoArrayList)

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }


}