package com.example.javacore

import android.os.Bundle
import android.view.Display
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var adapterStudent:Adapter
    var students:MutableList<Student> = mutableListOf()
    var listToSort:MutableList<Student> = mutableListOf()
    var check:Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        students.add(Student("Hà Văn Hoàng",1998,"0399408879","CNTT","Đại học"))
        students.add(Student("Bùi Văn Quang",1999,"9876543219","CNTT","Cao Đẳng"))
        students.add(Student("Phạm Minh Hiếu",1999,"5469871233","CNTT","Đại học"))
        students.add(Student("Bùi Xuân Trường",2000,"54615487","CNTT","Cao Đẳng"))
        adapterStudent = Adapter(this@MainActivity,students)
        lv_Student.setOnItemClickListener { adapterView, view, i, l ->
            ed_Major.setText(students[i].major)
            ed_Name.setText(students[i].name)
            ed_PhoneNumber.setText(students[i].phoneNumber)
            ed_YearOfBirth.setText(students[i].yearOfBirth.toString())

            if(students[i].education.equals("Cao Đẳng")){
                rb_College.isChecked = true
            }else{
                rb_University.isChecked = true
            }
        }
        lv_Student.apply {
            adapter = adapterStudent
        }
        btn_Add.setOnClickListener {
            var education:String
            if(rb_University.isChecked){
                education = "Đại học"
            }else {
                education = "Cao Đẳng"
            }
            var student: Student = Student(ed_Name.text.toString(),ed_YearOfBirth.text.toString().toInt(),ed_PhoneNumber.text.toString(),ed_Major.text.toString(),education)
            AddStudent(student)
            listToSort = students
            Display(students)
        }
        btn_Update.setOnClickListener {
            var phoneNumber:String = ed_PhoneNumber.text.toString()
            if(!phoneNumber.isEmpty()){

                var education:String
                if(rb_University.isChecked){
                    education = "Đại học"
                }else {
                    education = "Cao Đẳng"
                }
                var student: Student = Student(ed_Name.text.toString(),ed_YearOfBirth.text.toString().toInt(),ed_PhoneNumber.text.toString(),ed_Major.text.toString(),education)
                UpdateStudent(student)
                listToSort = students
                Display(students)
            }else{
                Toast.makeText(this@MainActivity,"Chưa nhập SDT",Toast.LENGTH_LONG).show()
            }
        }
        btn_Remove.setOnClickListener {
            var phoneNumber:String = ed_PhoneNumber.text.toString()
            DeleteStudent(phoneNumber)
            listToSort = students
            Display(students)
        }
        btn_SortByName.setOnClickListener {
            SortByName()
        }
        btn_FilterCollege.setOnClickListener {
            var listStudent = Filter(students,"Cao Đẳng")
            listToSort = listStudent
            Display(listStudent)
        }
        btn_FilterUniversity.setOnClickListener {
            var listStudent = Filter(students,"Đại học")
            listToSort = listStudent
            Display(listStudent)
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val tableStudent:MutableList<Student> = Search(p0)
                listToSort = tableStudent
                Display(tableStudent)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

        btn_Display.setOnClickListener {
            listToSort = students
            Display(students)
        }
    }
    fun AddStudent(student: Student){
        for( i in 0 until students.size){
            if(student.phoneNumber.equals(students.get(i).phoneNumber)){
                check = false
            }
        }
        if(check){
            students.add(student)
        }
        check = true
    }
    fun UpdateStudent(student: Student){
        for(i in 0 until students.size){
            if(student.phoneNumber.equals(students.get(i).phoneNumber)){
                students.set(i,student)
            }
        }
    }
    fun DeleteStudent(sdt: String){
        for(i in 0 until students.size){
            if(students[i].phoneNumber.equals(sdt)){
                students.removeAt(i)
                break
            }
        }
    }
    fun SortByName() {
        var sortByName:List<Student> = listToSort.sortedBy { it.name.substringAfterLast(" ") }
        listToSort = sortByName as MutableList<Student>
        Display(listToSort)
    }
    fun Filter(students: MutableList<Student>,education: String): MutableList<Student> {
        var filterColllege:List<Student> = students.filter {
            it.education.equals(education)
        }
        return filterColllege as MutableList<Student>
    }
    fun Search(strSearch: String?): MutableList<Student> {
        val str = strSearch.toString().trim()
        var tableStudent: MutableList<Student> = mutableListOf()
        for(i in 0 until  students.size){
            if(students[i].education.toLowerCase().indexOf(str) != -1 || students[i].yearOfBirth.toString().indexOf(str) != -1
                || students[i].name.toLowerCase().indexOf(str) != -1 || students[i].major.toLowerCase().indexOf(str) != -1 ||
                students[i].phoneNumber.indexOf(str) != -1){
                tableStudent.add(students[i])
            }
        }
        return tableStudent
    }
    fun Display(students:MutableList<Student>){
        lv_Student.adapter = Adapter(this@MainActivity, students)
    }
}