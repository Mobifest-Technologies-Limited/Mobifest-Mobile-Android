package co.mobifest.mobile.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import co.mobifest.mobile.ui.shopping.HomePageActivity
import co.mobifest.mobile.UserLoginActivity
import co.mobifest.mobile.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserHomeActivity : AppCompatActivity() {
    private val mAppBarConfiguration: AppBarConfiguration? = null
    private lateinit var shoppingLinkLayout: LinearLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)
        val toolbar = findViewById<Toolbar>(R.id.user_home_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        shoppingLinkLayout = findViewById(R.id.shopping_link_layout)
        shoppingLinkLayout.setOnClickListener(returnOnClickListener())
    }


    private fun returnOnClickListener(): View.OnClickListener? {
        return View.OnClickListener {
            when (it) {
                shoppingLinkLayout -> loadShoppingModule()

            }
        }
    }

    private fun loadShoppingModule() {
            val i = Intent(this@UserHomeActivity, HomePageActivity::class.java)
            i.putExtra("NAME","MosesKT")
            i.putExtra("PHONE", "+256789608541")
            i.putExtra("PASSWORD", "userPassword")
            i.putExtra("CALLINGACTIVITY", "LoginPage")
            startActivity(i)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_page, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return (NavigationUI.navigateUp(navController, mAppBarConfiguration!!)
                || super.onSupportNavigateUp())
    }

    override fun onBackPressed() {
        startActivity(Intent(this@UserHomeActivity, UserLoginActivity::class.java))
    }

    companion object {
        var databaseOrders: DatabaseReference? = null
        val order: Unit
            get() {
                databaseOrders = FirebaseDatabase.getInstance().getReference("orders")
            }
    }
}