package uk.org.websolution.lessonone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    private var userList = listOf(
        Person("Ivanov", 1),
        Person("Petrov", 2),
        Person("Sidorov", 3)
    )
    private lateinit var userText: TextView
    private lateinit var classText: TextView
    private lateinit var loopText: TextView
    private lateinit var runLoopBtn : Button
    private lateinit var nextBtn: Button
    private lateinit var prevBtn: Button
    private lateinit var showBtn: Button

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        setListeners()
    }

    private fun updateText() {
        ("Surname: " + userList[currentIndex].surname + " ID: " + userList[currentIndex].id).also {
            userText.text = it
        }
    }

    private fun setListeners() {
        nextBtn.setOnClickListener {
            currentIndex = (currentIndex + 1) % userList.size
            updateText()
        }

        prevBtn.setOnClickListener {
            currentIndex = if (currentIndex == 0) {
                userList.size - 1
            } else
                (currentIndex - 1) % userList.size
            updateText()
        }

        showBtn.setOnClickListener {
            val copiedPerson: Person = userList[currentIndex].copy(id = 100)
            classText.text = ("Surname: " + copiedPerson.surname + " ID: " + copiedPerson.id).also {
                classText.text = it
            }
        }

        runLoopBtn.setOnClickListener {
            runLoop()
        }
    }

    private fun runLoop(){
        for (i in 1..5){
            Log.d("CONSOLE_TAG", "Loop for 1 to 5 -  $i")
        }

        for (user in userList){
            Log.d("CONSOLE_TAG", "User loop -  $user")
        }

        loopText.text = getString(R.string.check_logcat)
    }

    private fun initialize() {
        nextBtn = findViewById(R.id.button_next)
        prevBtn = findViewById(R.id.button_prev)
        showBtn = findViewById(R.id.show_class_btn)
        runLoopBtn = findViewById(R.id.run_loop_btn)
        userText = findViewById(R.id.user_text)
        classText = findViewById(R.id.object_text)
        loopText = findViewById(R.id.loop_result)
        updateText()
    }
}