package com.example.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imgViewBMI.setImageResource(R.drawable.empty)
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }

        btnCalculate.setOnClickListener {
            evaluate()
        }
        btnReset.setOnClickListener {
            resultClear();
        }
    }
    private  fun calculateBMI():Double
    {
        val dblWeight:Double?
        val dblHeight:Double?
        var dblBMI=-0.1
        try {

            dblWeight=(editTextWeight.text.toString().toDouble())
            dblHeight=(editTextHeight.text.toString().toDouble())
            dblBMI=dblWeight/((dblHeight*dblHeight))


        }catch (e : Exception)
        {
        Toast.makeText(getApplicationContext(),"Please make sure your input is enter",Toast.LENGTH_SHORT).show()
        }
        return dblBMI

    }

    private fun evaluate()
    {
        val BMI=calculateBMI()
        if(BMI>=0.0 && BMI<18.5)
        {
            imgViewBMI.setImageResource(R.drawable.under)
            BMIAnswerTxt.text=String.format("%.2f",BMI)+"(Under)"
        }
        else if(BMI >18.5 && BMI<=24.9)
        {
            imgViewBMI.setImageResource(R.drawable.normal)
            BMIAnswerTxt.text=String.format("%.2f",BMI)+"(Normal)"
        }
        else if(BMI>25)
        {
            imgViewBMI.setImageResource(R.drawable.over)
            BMIAnswerTxt.text=String.format("%.2f",BMI)+"(Over)"
        }
    }
    private fun resultClear()
    {
        editTextWeight.text.clear()
        editTextHeight.text.clear()
        BMIAnswerTxt.text=null
        imgViewBMI.setImageResource(R.drawable.empty)

    }
}
