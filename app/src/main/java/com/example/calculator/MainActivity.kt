package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var clMain: ConstraintLayout
    lateinit var buttonList: List<Button>
    lateinit var displayText: TextView

    private var num1 = ""
    private var num2 = ""
    private var op = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clMain = findViewById(R.id.clMain)
        displayText = findViewById(R.id.tvDisplay)

        buttonList = listOf(
            findViewById(R.id.bt0),
            findViewById(R.id.bt1),
            findViewById(R.id.bt2),
            findViewById(R.id.bt3),
            findViewById(R.id.bt4),
            findViewById(R.id.bt5),
            findViewById(R.id.bt6),
            findViewById(R.id.bt7),
            findViewById(R.id.bt8),
            findViewById(R.id.bt9),
            findViewById(R.id.btDecimal),
            findViewById(R.id.btNegative),
            findViewById(R.id.btClear),
            findViewById(R.id.btDel),
            findViewById(R.id.btPlus),
            findViewById(R.id.btMinus),
            findViewById(R.id.btMultiply),
            findViewById(R.id.btDiv),
            findViewById(R.id.btEquals),
            findViewById(R.id.btDecimal),
        )

        for (button in buttonList)
            button.setOnClickListener(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("num1", num1)
        outState.putString("num2", num2)
        outState.putString("op", op)
        outState.putString("display", displayText.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        num1 = savedInstanceState.getString("num1", "")
        num2 = savedInstanceState.getString("num2", "")
        op = savedInstanceState.getString("op", "")
        displayText.text = savedInstanceState.getString("display", "")
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.bt0 -> setValues("0")
            R.id.bt1 -> setValues("1")
            R.id.bt2 -> setValues("2")
            R.id.bt3 -> setValues("3")
            R.id.bt4 -> setValues("4")
            R.id.bt5 -> setValues("5")
            R.id.bt6 -> setValues("6")
            R.id.bt7 -> setValues("7")
            R.id.bt8 -> setValues("8")
            R.id.bt9 -> setValues("9")
            R.id.btClear -> clearValues()
            R.id.btEquals -> result()
            R.id.btPlus -> {
                op = "+"
                displayText.text = "+"
            }
            R.id.btMinus -> {
                op = "-"
                displayText.text = "-"
            }
            R.id.btMultiply -> {
                op = "*"
                displayText.text = "*"
            }
            R.id.btDiv -> {
                op = "/"
                displayText.text = "/"

            }
            R.id.btDecimal -> setDecimal()
            R.id.btNegative -> setNeg()
            R.id.btDel -> del()
        }
    }

    private fun result() {
        try {
            val a = num1.toFloat()
            val b = num2.toFloat()

            when (op) {
                "+" -> displayText.text = "${a + b}"
                "-" -> displayText.text = "${a - b}"
                "*" -> displayText.text = "${a * b}"
                "/" -> {
                    if (b == 0F) {
                        Toast.makeText(this, "Can't Divide by Zero", Toast.LENGTH_SHORT).show()
                        clearValues()
                    }
                    displayText.text = "${a / b}"
                }
            }
        }catch (e:Exception){
            clearValues()
        }
    }

    private fun setDecimal() {
        if (op.isEmpty()) {
            if (num1.contains("."))
                return
            num1 += "."
            displayText.text = num1
        } else {
            if (num2.contains("."))
                return
            num2 += "."
            displayText.text = num2
        }
    }

    private fun setNeg() {
        if (op.isEmpty()) {
            num1 = if (num1.startsWith("-"))
                num1.substring(1)
            else
                "-$num1"
            displayText.text = num1
        } else {
            num2 = if (num2.startsWith("-"))
                num2.substring(1)
            else
                "-$num2"
            displayText.text = num2
        }
    }

    private fun del() {
        try {
            if (op.isEmpty()) {
                num1 = num1.substring(0, num1.length - 1)
                displayText.text = num1
            } else {
                num2 = num2.substring(0, num2.length - 1)
                displayText.text = num2
            }
        } catch (e: Exception) {
            Snackbar.make(clMain, "No Number to Delete!", Snackbar.LENGTH_LONG).show()
            return
        }
    }


    private fun clearValues() {
        displayText.text = ""
        num1 = ""
        num2 = ""
        op = ""
    }

    private fun setValues(s: String) {

        if (op.isEmpty()) {
            num1 += s
            displayText.text = num1
        } else {
            num2 += s
            displayText.text = num2
        }

    }
}