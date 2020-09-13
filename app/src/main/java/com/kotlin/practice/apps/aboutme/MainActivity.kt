package com.kotlin.practice.apps.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.kotlin.practice.apps.aboutme.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Ahmed Tantawy")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //binding the variable in the layout to the object of the data class
        binding.myName = myName

        binding.doneButton.setOnClickListener {
            addNickname(it)
        }

        binding.nicknameText.setOnClickListener {
            showViews(it)
        }

    }

    private fun showViews(view: View) {
        binding.apply {
            myName?.nickname = nicknameText.text.toString()
            // Invalidate all binding expressions and request a new rebind to refresh UI
            invalidateAll()
            editNickname.visibility = View.VISIBLE
            view.visibility = View.GONE
            doneButton.visibility = View.VISIBLE
        }

    }

    private fun addNickname(view: View) {
        binding.apply {
            myName?.nickname = editNickname.text.toString()
            invalidateAll()
            editNickname.visibility = View.GONE
            view.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
}