package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculate.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val costTip = binding.etCostOfService.text.toString().toDoubleOrNull()
        if(costTip == null){
            binding.tvTipResult.text = ""
            return
        }
        val tipPercentage = when(binding.rgTipOptions.checkedRadioButtonId){
            R.id.rb_option_15_percent -> 0.15
            R.id.rb_option_18_percent -> 0.18
            else -> 0.20
        }
        val roundUp = binding.swRoundUp.isChecked
        val tip = if(roundUp) ceil(tipPercentage * costTip) else tipPercentage * costTip
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tvTipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}