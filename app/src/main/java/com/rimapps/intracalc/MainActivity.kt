package com.rimapps.intracalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    lateinit var exp: TextView
    lateinit var result: TextView
    lateinit var scrollView: HorizontalScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        exp = findViewById(R.id.expression)
        result = findViewById(R.id.result)
        //scrollView = findViewById(R.id.scrollView)

        findViewById<Button>(R.id.btnZero).setOnClickListener { clickedN(0) }
        findViewById<Button>(R.id.btnOne).setOnClickListener { clickedN(1) }
        findViewById<Button>(R.id.btnTwo).setOnClickListener { clickedN(2) }
        findViewById<Button>(R.id.btnThree).setOnClickListener { clickedN(3) }
        findViewById<Button>(R.id.btnFour).setOnClickListener { clickedN(4) }
        findViewById<Button>(R.id.btnFive).setOnClickListener { clickedN(5) }
        findViewById<Button>(R.id.btnSix).setOnClickListener { clickedN(6) }
        findViewById<Button>(R.id.btnSeven).setOnClickListener { clickedN(7) }
        findViewById<Button>(R.id.btnEight).setOnClickListener { clickedN(8) }
        findViewById<Button>(R.id.btnNine).setOnClickListener { clickedN(9) }
        findViewById<Button>(R.id.btnDecimal).setOnClickListener { clickedN(-1) }
        findViewById<Button>(R.id.btnBopen).setOnClickListener { clickedB('o') }
        findViewById<Button>(R.id.btnBclose).setOnClickListener { clickedB('c') }
        findViewById<Button>(R.id.btnAdd).setOnClickListener { clickedO('+') }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { clickedO('-') }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { clickedO('/') }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { clickedO('*') }
        findViewById<Button>(R.id.btnBackSpace).setOnClickListener { clickedA(it) }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clickedA(it) }
        findViewById<Button>(R.id.btnEqual).setOnClickListener { clickedA(it) }

        viewModel.exp.observe(this,{ exp.text =it
        })
        viewModel.answer.observe(this, {
            result.text = it
        })
    }

    private fun clickedA(it: View) {
        when (it.id) {
            R.id.btnBackSpace -> viewModel.delete()
            R.id.btnEqual -> viewModel.calculate()
            R.id.btnClear -> viewModel.reset()
        }
//        scrollView.postDelayed({
//            kotlin.run {
//                scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
//            }
//        }, 100L)
    }

    private fun clickedO(o: Char) {
        viewModel.appendOperator(o)
//        scrollView.postDelayed({
//            kotlin.run {
//                scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
//            }
//        }, 100L)
    }

    private fun clickedB(c: Char) {
        if (c == 'o')
            viewModel.bOpen()
        else
            viewModel.bClose()
//        scrollView.postDelayed({
//            kotlin.run {
//                scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
//            }
//        }, 100L)
    }

    private fun clickedN(n: Int) {
        if (n != -1)
            viewModel.appendNumber(n.toString())
        else
            viewModel.appendNumber(".")
//        scrollView.postDelayed({
//            kotlin.run {
//                scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
//            }
//        }, 100L)
    }

}