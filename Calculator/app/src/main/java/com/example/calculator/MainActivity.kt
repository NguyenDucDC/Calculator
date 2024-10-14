package com.example.test1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textResult: TextView
    private var lastNumber = ""
    private var operator = ""
    private var result = 0
    private var isNewOp = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.text_result)

        // Gắn các nút với sự kiện onClick
        arrayOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide, R.id.btnEqual, R.id.btnCE, R.id.btnC,
            R.id.btnBS, R.id.btnPlusMinus
        ).forEach { id ->
            findViewById<Button>(id).setOnClickListener(this)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn0 -> appendNumber("0")
            R.id.btn1 -> appendNumber("1")
            R.id.btn2 -> appendNumber("2")
            R.id.btn3 -> appendNumber("3")
            R.id.btn4 -> appendNumber("4")
            R.id.btn5 -> appendNumber("5")
            R.id.btn6 -> appendNumber("6")
            R.id.btn7 -> appendNumber("7")
            R.id.btn8 -> appendNumber("8")
            R.id.btn9 -> appendNumber("9")
            R.id.btnPlusMinus -> togglePlusMinus()
            R.id.btnAdd -> handleOperation("+")
            R.id.btnSubtract -> handleOperation("-")
            R.id.btnMultiply -> handleOperation("*")
            R.id.btnDivide -> handleOperation("/")
            R.id.btnEqual -> calculateResult()
            R.id.btnC -> clearAll()
            R.id.btnCE -> clearEntry()
            R.id.btnBS -> backspace()
        }
    }

    private fun appendNumber(number: String) {
        if (isNewOp) {
            textResult.text = number
            isNewOp = false
        } else {
            textResult.text = "${textResult.text}$number"
        }
        lastNumber = textResult.text.toString()
    }

    private fun togglePlusMinus() {
        val currentText = textResult.text.toString()
        if (currentText.isNotEmpty()) {
            if (currentText.startsWith("-")) {
                textResult.text = currentText.substring(1)
            } else {
                textResult.text = "-$currentText"
            }
        }
        lastNumber = textResult.text.toString()
    }

    private fun handleOperation(op: String) {
        operator = op
        result = lastNumber.toIntOrNull() ?: 0
        isNewOp = true
    }

    private fun calculateResult() {
        val secondNumber = lastNumber.toIntOrNull() ?: 0
        result = when (operator) {
            "+" -> result + secondNumber
            "-" -> result - secondNumber
            "*" -> result * secondNumber
            "/" -> if (secondNumber != 0) result / secondNumber else 0 // Tránh chia cho 0
            else -> result
        }
        textResult.text = result.toString()
        isNewOp = true
    }

    private fun clearAll() {
        textResult.text = "0"
        lastNumber = ""
        result = 0
        operator = ""
    }

    private fun clearEntry() {
        textResult.text = "0"
        lastNumber = ""
    }

    private fun backspace() {
        if (textResult.text.length > 1) {
            textResult.text = textResult.text.substring(0, textResult.text.length - 1)
        } else {
            textResult.text = "0"
        }
        lastNumber = textResult.text.toString()
    }
}
