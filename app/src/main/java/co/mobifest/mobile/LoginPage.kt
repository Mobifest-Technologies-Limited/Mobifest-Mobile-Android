package co.mobifest.mobile

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import co.mobifest.mobile.ui.UserHomeActivity
import com.google.android.material.button.MaterialButton

class LoginPage : AppCompatActivity() {
    lateinit var userNameEt: EditText
    lateinit var passwordEt: EditText
    lateinit var loginBtn: MaterialButton
    lateinit var registerLink: TextView
    lateinit var userName: String
    lateinit var userPassword: String
    var status: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        userNameEt = findViewById(R.id.login_user_name)
        passwordEt = findViewById(R.id.login_user_password)
        loginBtn = findViewById(R.id.btnlogin)
        registerLink = findViewById(R.id.tvreg)
        status = findViewById(R.id.tvstatus)
        status!!.text = ""
        loginBtn.setOnClickListener {

            userName = userNameEt.text.toString()
            userPassword = passwordEt.text.toString()
            if (userName == "user123" && userPassword == "user123") {
                val i = Intent(this@LoginPage, UserHomeActivity::class.java)
                i.putExtra("NAME", userName)
                i.putExtra("PHONE", userName)
                i.putExtra("PASSWORD", userPassword)
                i.putExtra("CALLINGACTIVITY", "LoginPage")
                startActivity(i)
            } else {
                status!!.text = "Invalid Credentials"
            }

        }
        registerLink.setOnClickListener {
            val intent = Intent(this@LoginPage, RegisterPage::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@LoginPage, MainActivity::class.java))
    }

    companion object {
        var tmpname: String? = null
    }
}