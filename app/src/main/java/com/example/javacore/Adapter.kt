package com.example.javacore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_student.view.*

class Adapter(val listStudent:MutableList<Student>,val listener : IonClick) :
    RecyclerView.Adapter<Adapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.item_student,parent,false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = listStudent[position]
        holder.name.text = student.name
        holder.phoneNumber.text = student.phoneNumber
        holder.education.text = student.education
    }
    override fun getItemCount(): Int {
        return listStudent.size
    }
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        val name : TextView = itemView.tvName
        val phoneNumber : TextView = itemView.tvPhoneNumber
        val education : TextView = itemView.tvEducation
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val positon: Int = adapterPosition
            listener.ClickStudent(positon)
        }
    }



}