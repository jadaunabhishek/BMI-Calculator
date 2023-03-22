package com.example.bmicalculator

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var gender : String = "male"
    private var height : Int = 169
    private var weight : Int = 68
    private var age : Int = 19

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserGender()

        getUserheight()

        getUserweight()

        getUserage()
        onButtonClicked()

    }


    private fun getUserage() {
        binding.mainActivityTvAddAge.setOnClickListener {
            age++
            binding.mainActivityTvAge.text = age.toString()
        }

        binding.mainActivityTvdecAge.setOnClickListener {
            if(age!=1){
            age--
            binding.mainActivityTvAge.text = age.toString()}
        }
    }

    private fun getUserweight() {
        binding.mainActivityTvAddWeight.setOnClickListener {
            weight++
            binding.mainActivityTvWeight.text = weight.toString()
        }

        binding.mainActivityTvdecWeight.setOnClickListener {
            if(weight!=1){
            weight--
            binding.mainActivityTvWeight.text = weight.toString()}
        }
    }

    private fun getUserheight() {
        binding.mainActivitySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.mainActivityTvHeight.text = progress.toString()
                height = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

    private fun getUserGender() {
        binding.mainActivitylLayoutMale.setOnClickListener {
            binding.mainActivitylLayoutFemale.setBackgroundResource(R.drawable.design3)
            binding.mainActivitylLayoutMale.setBackgroundResource(R.drawable.design5)
            gender = "male"
        }
        binding.mainActivitylLayoutFemale.setOnClickListener {
            binding.mainActivitylLayoutMale.setBackgroundResource(R.drawable.design3)
            binding.mainActivitylLayoutFemale.setBackgroundResource(R.drawable.design5)
            gender = "female"
        }
    }
    private fun onButtonClicked() {
        binding.mainActivityBtnCalculateBmi.setOnClickListener {
            BmiResult()

        }
    }

    private fun BmiResult(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.outputbmi)
        var imgClose : ImageView = dialog.findViewById(R.id.dialogImgClose)
        var bmiValue : TextView = dialog.findViewById(R.id.dialogTvBmiBValue)
        imgClose.setOnClickListener {
            dialog.dismiss()
        }
        bmiValue.text = String.format("%.1f",calculateBmi())
        dialog.show()
    }

    private fun calculateBmi() : Double{
        val bmi = (weight/(height*height).toDouble())*10000
        return bmi
    }

}