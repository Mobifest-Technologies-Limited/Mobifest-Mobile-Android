package co.mobifest.mobile.ui

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import co.mobifest.mobile.databinding.ActivityLoginBinding
import co.mobifest.mobile.ui.home.UserHomeActivity
import co.mobifest.mobile.utils.ComponentNavigator
import com.google.android.material.button.MaterialButton
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {

    lateinit var userNameEt: EditText
    lateinit var passwordEt: EditText
    lateinit var loginBtn: MaterialButton
    lateinit var registerLink: TextView
    private lateinit var resetPasswordLink: TextView
    lateinit var userName: String
    lateinit var userPassword: String
    var status: TextView? = null

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        userNameEt = binding.loginUserName
        passwordEt = binding.loginUserPassword
        loginBtn = binding.btnlogin
        registerLink = binding.tvreg
        status = binding.signInErrorDisplay
        resetPasswordLink = binding.resetPasswordLinkTv
        status!!.text = ""
        loginBtn.setOnClickListener(returnOnClickListener())
        resetPasswordLink.setOnClickListener(returnOnClickListener())
        registerLink.setOnClickListener(returnOnClickListener())
    }

    private fun returnOnClickListener(): View.OnClickListener? {
        return View.OnClickListener {
            when (it) {
                loginBtn -> processLogin()
                registerLink -> loadRegisterPage()
                resetPasswordLink -> loadPasswordResetPage()
            }
        }
    }

    private fun processLogin() {
        ComponentNavigator.hideSoftKeyBoard(this)
        userName = userNameEt.text.toString()
        userPassword = passwordEt.text.toString()
        if (userName == "user123" && userPassword == "user123") {
            startActivity(Intent(this@LoginActivity, UserHomeActivity::class.java))
        } else {
            status!!.text = "Invalid Credentials"
        }

    }

    private fun loadRegisterPage() {
        startActivity(Intent(this@LoginActivity, UserRegisterActivity::class.java))
    }

    private fun loadPasswordResetPage() {
        startActivity(Intent(this@LoginActivity, ResetPasswordActivity::class.java))
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
        Process.killProcess(Process.myPid())
        exitProcess(1)
    }

    companion object {
        var tmpname: String? = null
    }
}