package com.sorongos.binary2something

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.sorongos.binary2something.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var numberTxt = StringBuilder("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCall.setOnClickListener {
            with(Intent(Intent.ACTION_SENDTO)) {
                val phoneNumber = binding.numberTxt.text.toString()
                data = Uri.parse("tel:$phoneNumber")

//                val uriTxt = "mailto:" + "dosora9@naver.com" +
//                        "?subject=" + Uri.encode("emailTitle") +
//                        "&body=" + Uri.encode("emailContent")
//                data = Uri.parse(uriTxt)

                startActivity(this)
            }
        }

//        binding.numberTxt.text =
    }

    fun numberClicked(view : View){
        Log.d(TAG,"numberClicked")
        val numberString = (view as? Button)?.text?.toString() ?: ""
        binding.numberTxt.text = numberTxt.append(numberString)
    }

    fun deleteClicked(view: View){
        Log.d(TAG,"deleteClicked")
        if(numberTxt.isNotEmpty())
            binding.numberTxt.text = numberTxt.deleteCharAt(numberTxt.length-1)
    }

    /**화면이 종료될 때 데이터를 저장*/
    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG,"onSaveInstanceState")
        outState.putString("number",binding.numberTxt.text.toString())
        super.onSaveInstanceState(outState)
    }

    /**화면 복구*/
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d(TAG,"onRestoreInstanceState")
        numberTxt.append(savedInstanceState.getString("number"))
        binding.numberTxt.text = numberTxt
        super.onRestoreInstanceState(savedInstanceState)
    }
}