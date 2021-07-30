package com.maharaja.todo_sonal.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.maharaja.todo_sonal.R


class ToDoDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_to_do_detail, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.appbar)
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        toolbar.setTitle("Details")
        toolbar.setNavigationIcon(R.drawable.backicon)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        return view
    }

}