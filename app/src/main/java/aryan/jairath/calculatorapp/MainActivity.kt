package aryan.jairath.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    var lastNumeric: Boolean =false
    var lastDot: Boolean =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput=findViewById(R.id.tvInput)
    }
    fun onDelete(view: View){
        var tvValue = tvInput?.text.toString()
        tvValue=tvValue.substring(0,tvValue.length-1)
        tvInput?.text=tvValue

    }
    fun onDigit(view: View){
            tvInput?.append((view as Button).text)
            lastNumeric=true
            lastDot=false
    }
    fun onClear(view: View){
        tvInput?.text= ""
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric&& !lastDot) {
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onOperator(view: View) {
        tvInput?.text?.let{

            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric=false
                lastDot=false
            }
        }
    }
    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            try {
                if (tvValue.contains("-")){
                    val one = tvValue.substringBeforeLast("-")
                    val two = tvValue.substringAfterLast("-")
                    tvInput?.text = (one.toDouble() - two.toDouble()).toString()
                }
                if (tvValue.contains("+")){
                    val one = tvValue.substringBeforeLast("+")
                    val two = tvValue.substringAfterLast("+")
                    tvInput?.text = (one.toDouble() + two.toDouble()).toString()
                }
                if (tvValue.contains("x")){
                    val one = tvValue.substringBeforeLast("x")
                    val two = tvValue.substringAfterLast("x")
                    tvInput?.text = (one.toDouble() * two.toDouble()).toString()
                }
                if (tvValue.contains("/")){
                    val one = tvValue.substringBeforeLast("/")
                    val two = tvValue.substringAfterLast("/")
                    tvInput?.text = (one.toDouble() / two.toDouble()).toString()
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
            ||value.contains("x")
            ||value.contains("+")
            ||value.contains("-")

        }
    }
}