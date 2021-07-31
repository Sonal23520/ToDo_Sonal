package com.maharaja.todo_sonal.controller

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
import com.maharaja.todo_sonal.dao.impl.CrudDaoImpl
import com.maharaja.todo_sonal.model.ToDo
import java.util.*
import kotlin.collections.ArrayList


class ToDoHome : Fragment() {

    private var database = CrudDaoImpl().getToDo()
    private lateinit var todoRecyclerview : RecyclerView
    private lateinit var todoArrayList : ArrayList<ToDo>
    private lateinit var todoSearchview : SearchView
    private lateinit var searchArrayList : ArrayList<ToDo>


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
        todoSearchview=view.findViewById(R.id.todo_search)
        todoRecyclerview.layoutManager = LinearLayoutManager(context)
        todoRecyclerview.setHasFixedSize(true)
        todoArrayList = arrayListOf<ToDo>()
        searchArrayList = arrayListOf<ToDo>()


        getToDoData()
        searchData()


        return view
    }

    private fun searchData() {
        todoSearchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchArrayList.clear()
                var search = newText?.lowercase(Locale.getDefault())
                if (search!!.isNotEmpty()){

                    todoArrayList.forEach {
                        if (it.todo!!.lowercase(Locale.getDefault()).contains(search)){

                            searchArrayList.add(it)

                        }
                    }

                    todoRecyclerview.adapter?.notifyDataSetChanged()

                }else{
                    searchArrayList.clear()
                    searchArrayList.addAll(todoArrayList)
                    todoRecyclerview.adapter?.notifyDataSetChanged()
                }
                return false
            }

        })
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
                    searchArrayList.addAll(todoArrayList)
                    todoRecyclerview.adapter = Adapter(searchArrayList)

//                    todoRecyclerview.adapter = Adapter(todoArrayList)

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }


}