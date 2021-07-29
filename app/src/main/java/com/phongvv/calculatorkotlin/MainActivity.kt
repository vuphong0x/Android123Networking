package com.phongvv.calculatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.textview.MaterialTextView
import com.phongvv.calculatorkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listNumber: MutableList<String> = arrayListOf()
    private val listOperation: MutableList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun equalsClick(view: View) {
        val number = StringBuilder()
        listNumber.forEach {
            number.append(it)
        }
        listOperation.add(number.toString())
        listNumber.clear()

        val answer = applyOp(
            listOperation[0].toDouble(),
            listOperation[2].toDouble(),
            listOperation[1].single()
        )

        updateDisplay("\n=$answer")
        clearCache()
    }

    private fun applyOp(a: Double, b: Double, op: Char): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '×' -> a * b
            '/' -> if (b == 0.0) 0.0
            else a / b
            else -> 0.0
        }
    }

    fun numberClick(view: View) {
        val button = view as MaterialTextView
        listNumber.add(button.text.toString())
        updateDisplay(button.text.toString())
    }

    fun operatorClick(view: View) {
        val button = view as MaterialTextView
        if (listNumber.isEmpty()) return
        val number = StringBuilder()
        listNumber.forEach {
            number.append(it)
        }
        listOperation.add(number.toString())
        listNumber.clear()
        listOperation.add(button.text.toString())
        updateDisplay(button.text.toString())
    }

    fun actionClear(view: View) {
        clearCache()
        binding.placeholder.text = ""
        binding.answer.text = ""
    }

    private fun clearCache() {
        listNumber.clear()
        listOperation.clear()
    }

    private fun updateDisplay(mainDisplayString: String) {
        binding.placeholder.append(mainDisplayString)
    }
}
