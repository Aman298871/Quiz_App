package com.example.quizapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val questions = arrayOf("What is the built-in database in android studio?",
        "What is the full form of apk in Android Development?",
        "In which year, first android version was released by Google ? ")
    private val options = arrayOf(arrayOf("MySQL","SQLITE","Firebase","NoSql"),
        arrayOf("Application Programming Interference","Application programming Interference","Application Programming Interface"," Application Programming Information"),
        arrayOf("2008","2002","2006","2020"))
    private val correctAnswers = arrayOf(1,2,0)
    private var currentquestionIndex =0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayQuestion()
        binding.option1.setOnClickListener {
            checkAnswer(0)
        }
        binding.option2.setOnClickListener {
            checkAnswer(1)
        }
        binding.option3.setOnClickListener {
            checkAnswer(2)
        }
        binding.option4.setOnClickListener {
            checkAnswer(3)
        }
        binding.restartquiz.setOnClickListener {
            restartQuiz()
        }

    }
    private fun correctButtonColor(buttonIndex: Int){
        when(buttonIndex){
            0-> binding.option1.setBackgroundColor(Color.GREEN)
            1-> binding.option2.setBackgroundColor(Color.GREEN)
            2-> binding.option3.setBackgroundColor(Color.GREEN)
            3-> binding.option4.setBackgroundColor(Color.GREEN)
        }
    }
    private fun wrongButtonColor(buttonIndex: Int){
        when(buttonIndex){
            0-> binding.option1.setBackgroundColor(Color.RED)
            1-> binding.option2.setBackgroundColor(Color.RED)
            2-> binding.option3.setBackgroundColor(Color.RED)
            3-> binding.option4.setBackgroundColor(Color.RED)
        }
    }
    private fun resetButtonColor(){
        binding.option1.setBackgroundColor(Color.rgb(50,59,96))
        binding.option2.setBackgroundColor(Color.rgb(50,59,96))
        binding.option3.setBackgroundColor(Color.rgb(50,59,96))
        binding.option4.setBackgroundColor(Color.rgb(50,59,96))
    }
    private fun showResults(){
        Toast.makeText(this,"You Scored : $score out of ${questions.size}",Toast.LENGTH_LONG).show()
        binding.restartquiz.isEnabled= true
    }
    private fun displayQuestion(){
        binding.question.text = questions[currentquestionIndex]
        binding.option1.text = options[currentquestionIndex][0]
        binding.option2.text = options[currentquestionIndex][1]
        binding.option3.text = options[currentquestionIndex][2]
        binding.option4.text = options[currentquestionIndex][3]
        resetButtonColor()
    }
    private fun checkAnswer(selectedAnswerIndex : Int){
        val correctAswerIndex = correctAnswers[currentquestionIndex]
        if (selectedAnswerIndex==correctAswerIndex){
            score++
            correctButtonColor(selectedAnswerIndex)
        }else{
            wrongButtonColor(selectedAnswerIndex)
            correctButtonColor(correctAswerIndex)
        }
        if (currentquestionIndex<questions.size -1){
            currentquestionIndex++
            binding.question.postDelayed({displayQuestion()},1000)
        }else{
            showResults()
        }
    }
    private fun restartQuiz(){
        currentquestionIndex=0
        score=0
        displayQuestion()
        binding.restartquiz.isEnabled= false
    }
}