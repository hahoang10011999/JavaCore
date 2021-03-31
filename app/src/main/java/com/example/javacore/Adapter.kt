package com.example.javacore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class Adapter(var context:Context, var students: MutableList<Student>) : BaseAdapter(){
    class ViewHolder(row: View){
         var tvName : TextView
         var tvPhoneNumber : TextView
         var tv : TextView
        init {
            tvName  = row.findViewById(R.id.tvName);
            tvPhoneNumber  = row.findViewById(R.id.tvPhoneNumber);
            tv  = row.findViewById(R.id.tv);
        }
    }
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view : View?
        var viewHolder : ViewHolder
        if (p1 == null){
            var layoutInflater : LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.item_student,null);
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = p1
            viewHolder = p1.tag as ViewHolder
        }
        var student:Student = getItem(p0) as Student
        viewHolder.tvName.text = student.name
        viewHolder.tvPhoneNumber.text = student.phoneNumber
        viewHolder.tv.text = student.level
        return view!!
    }

    override fun getItem(p0: Int): Any {
        return students.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return students.size
    }
}