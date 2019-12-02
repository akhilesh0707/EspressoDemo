package com.aqube.espressotest.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import android.util.Patterns
import android.text.TextUtils
import com.aqube.espressotest.R

class LoginActivity : AppCompatActivity(), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Setting login button click listener
        buttonLogin.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buttonLogin -> {
                login()
            }
        }
    }

    private fun login() {
        // Reset errors.
        editTextEmail.error=null
        editTextPassword.error=null

        // Store values at the time of the login attempt.
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            editTextEmail.error =getString(R.string.error_field_required)
            focusView = editTextEmail
            cancel = true
        } else if (!isEmailValid(email)) {
            editTextEmail.error=getString(R.string.error_invalid_email)
            focusView = editTextEmail
            cancel = true
        }

        // Check for a valid password.
        if (TextUtils.isEmpty(password)) {
            editTextPassword.error =getString(R.string.error_field_required)
            focusView = editTextPassword
            cancel = true
        } else if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            editTextPassword.error = getString(R.string.error_invalid_password)
            focusView = editTextPassword
            cancel = true
        }
        if (cancel) {
            focusView!!.requestFocus()
        } else {
            if (email == "user@email.com" && password == "123456") {
                loginSuccessfully(email)
            } else {
                Toast.makeText(applicationContext, getString(R.string.error_login_failed), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }

    private fun loginSuccessfully(email: String) {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra("email", email)
        startActivity(intent)
        finish()
        Toast.makeText(applicationContext, getString(R.string.login_successfully), Toast.LENGTH_SHORT).show()
    }
}
