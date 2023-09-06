package com.example.set1revision

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.set1revision.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.courseSpinner.onItemSelectedListener = this

        ArrayAdapter.createFromResource(this, R.array.course_selection, android.R.layout.simple_spinner_item).also{
                adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.courseSpinner.adapter = adapter
        }

        binding.getGradeBtn.setOnClickListener{
            var markString : String = binding.markInput.text.toString()
            getGrade(markString)
        }

        binding.contactUsBtn.setOnClickListener{
            contactUs()
        }
    }

    private fun getGrade(markString: String){
        if(markString.isEmpty()){
            binding.gradeObtained.text = "-"
        }else{
            val mark: Int = markString.toInt()
            if(mark in 0..49){
                binding.gradeObtained.text = "D"
            }else if(mark in 50 .. 54){
                binding.gradeObtained.text = "C"
            }else if(mark in 55..59){
                binding.gradeObtained.text = "C+"
            }else if(mark in 60..64){
                binding.gradeObtained.text = "B-"
            }else if(mark in 65..69){
                binding.gradeObtained.text = "B"
            }else if(mark in 70..74){
                binding.gradeObtained.text = "B+"
            }else if(mark in 75..79){
                binding.gradeObtained.text = "A-"
            }else if(mark in 80..100){
                binding.gradeObtained.text = "A"
            }else{
                binding.gradeObtained.text = "Invalid mark"
            }
        }
    }

    private fun contactUs(){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:03-61261600")
        startActivity(intent)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, Id: Long) {
        val selectedCourse : String = parent.getItemAtPosition(position).toString()
        when(position){
            0 -> binding.courseCode.text = "BBFA1043"
            1 -> binding.courseCode.text = "MPU-3232"
            2 -> binding.courseCode.text = "BMIT3084"
            3 -> binding.courseCode.text = "BACS3033"
            4 -> binding.courseCode.text = "BAIT2073"
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        binding.courseCodeLabel.text = "No course selected"
    }
}