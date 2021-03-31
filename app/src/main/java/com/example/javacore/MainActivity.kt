package com.example.javacore

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var check:Boolean = true
        var students:MutableList<Student> = mutableListOf()
        students.add(Student("Hà Văn Hoàng",1998,"0399408879","CNTT","Đại học"))
        students.add(Student("Nguyễn Thái Dương",2000,"1234567891","CNTT","Đại học"))
        students.add(Student("Bùi Văn Quang",1999,"9876543219","CNTT","Cao Đẳng"))
        students.add(Student("Phạm Minh Hiếu",1999,"5469871233","CNTT","Đại học"))
        listView.adapter = Adapter(this@MainActivity,students)
        btnAdd.setOnClickListener {
            var phoneNumber:String = phoneNumber.text.toString()
            var level:String
            if(university.isChecked){
                level = "Đại học"
            }else{
                level = "Cao Đẳng"
            }
            for( i in 0 until students.size){
                if(phoneNumber.equals(students.get(i).phoneNumber)){
                    check = false
                }
            }
            if(check){
                var date:Int = dateOfBirth.text.toString().toInt()
                students.add(Student(name.text.toString(),date,phoneNumber,major.text.toString(),level))
                listView.adapter = Adapter(this@MainActivity,students)
            }
            check = true
        }
        btnUpdate.setOnClickListener {
            var phoneNumber:String = phoneNumber.text.toString()
            if(!phoneNumber.isEmpty()){
                var level:String
                if(university.isChecked){
                    level = "Đại học"
                }else {
                    level = "Cao Đẳng"
                }
                for(i in 0 until students.size){
                    if(phoneNumber.equals(students.get(i).phoneNumber)){
                        students.get(i).dateOfBirth =dateOfBirth.text.toString().toInt()
                        students.get(i).level = level
                        students.get(i).major = major.text.toString()
                        students.get(i).name = name.text.toString()
                    }
                }
                listView.adapter = Adapter(this@MainActivity,students)
            }else{
                Toast.makeText(this@MainActivity,"Chưa nhập SDT",Toast.LENGTH_LONG).show()
            }
        }
        btnSortByName.setOnClickListener {

            var sortByName:List<Student> = students.sortedBy { it.name.substringAfterLast(" ") }
            listView.adapter = Adapter(this@MainActivity, sortByName as MutableList<Student>)
        }
        btnFilterCollege.setOnClickListener {
            var filterColllege:List<Student> = students.filter {
                it.level.equals("Cao Đẳng")
            }
            listView.adapter = Adapter(this@MainActivity, filterColllege as MutableList<Student>)
        }
        btnFilterUniversity.setOnClickListener {
            var filterUniver:List<Student> = students.filter {
                it.level.equals("Đại học")
            }
            listView.adapter = Adapter(this@MainActivity, filterUniver as MutableList<Student>)
        }
        btnSearch.setOnClickListener {
            var search:String = search.text.toString().toLowerCase().trim()
            var tableStudent: MutableList<Student> = mutableListOf()
            for(i in 0 until  students.size){
                if(students[i].level.toLowerCase().indexOf(search) != -1 || students[i].dateOfBirth.toString().indexOf(search) != -1
                    || students[i].name.toLowerCase().indexOf(search) != -1 || students[i].major.toLowerCase().indexOf(search) != -1 ||
                        students[i].phoneNumber.indexOf(search) != -1){
                    tableStudent.add(students[i])
                }
            }
            listView.adapter = Adapter(this@MainActivity, tableStudent)
        }
    }
}