package com.phongvv.calculatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.textview.MaterialTextView
import com.phongvv.calculatorkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listNumber = mutableListOf<String>()
    private val listOperation = mutableListOf<String>()
    private val OP_ADD = '+'
    private val OP_MINUS = '-'
    private val OP_MULTIPLY = '*'
    private val OP_DIVIDE = '/'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun equalsClick(view: View) {
        listOperation.add(
            listNumber.joinToString(
                separator = ""
            )
        )
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
            OP_ADD -> a + b
            OP_MINUS -> a - b
            OP_MULTIPLY -> a * b
            OP_DIVIDE -> if (b == 0.0) 0.0
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

        listOperation.add(
            listNumber.joinToString(
                separator = ""
            )
        )
        Log.e("TAG", "operatorClick: $listOperation")

        listNumber.clear()
        listOperation.add(button.text.toString())
        updateDisplay(button.text.toString())
    }

    fun actionClear(view: View) {
        clearCache()
        binding.textPlaceholder.text = ""
        binding.textAnswer.text = ""
    }

    private fun clearCache() {
        listNumber.clear()
        listOperation.clear()
    }

    private fun updateDisplay(mainDisplayString: String) {
        binding.textPlaceholder.append(mainDisplayString)
    }
}
