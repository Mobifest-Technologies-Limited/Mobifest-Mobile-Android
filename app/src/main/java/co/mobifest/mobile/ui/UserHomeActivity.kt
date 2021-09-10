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
import co.mobifest.mobile.ui.shopping.ShoppingUserHomeActivity
import co.mobifest.mobile.LoginActivity
import co.mobifest.mobile.R
import co.mobifest.mobile.ui.locomotive.LocomotiveHomeActivity
import co.mobifest.mobile.ui.rentals.RentalsHomeActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserHomeActivity : AppCompatActivity() {
    private val mAppBarConfiguration: AppBarConfiguration? = null
    private lateinit var shoppingLinkLayout: LinearLayout
    private lateinit var rentalsLinkLayout: LinearLayout
    private lateinit var locomotiveLayout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)
        val toolbar = findViewById<Toolbar>(R.id.user_home_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        shoppingLinkLayout = findViewById(R.id.shopping_link_layout)
        rentalsLinkLayout = findViewById(R.id.rentals_link_layout)
        locomotiveLayout = findViewById(R.id.locomotive_layout)
        shoppingLinkLayout.setOnClickListener(returnOnClickListener())
        rentalsLinkLayout.setOnClickListener(returnOnClickListener())
        locomotiveLayout.setOnClickListener(returnOnClickListener())
    }


    private fun returnOnClickListener(): View.OnClickListener? {
        return View.OnClickListener {
            when (it) {
                shoppingLinkLayout -> loadShoppingModule()
                rentalsLinkLayout -> loadRentalsModule()
                locomotiveLayout -> loadLocomotiveModule()

            }
        }
    }

    private fun loadLocomotiveModule() {
        val i = Intent(this@UserHomeActivity, LocomotiveHomeActivity::class.java)
        i.putExtra("NAME", "MosesKT")
        i.putExtra("PHONE", "+256789608541")
        i.putExtra("PASSWORD", "userPassword")
        i.putExtra("CALLINGACTIVITY", "LoginPage")
        startActivity(i)
    }

    private fun loadRentalsModule() {
        val i = Intent(this@UserHomeActivity, RentalsHomeActivity::class.java)
        i.putExtra("NAME", "MosesKT")
        i.putExtra("PHONE", "+256789608541")
        i.putExtra("PASSWORD", "userPassword")
        i.putExtra("CALLINGACTIVITY", "LoginPage")
        startActivity(i)
    }

    private fun loadShoppingModule() {
        val i = Intent(this@UserHomeActivity, ShoppingUserHomeActivity::class.java)
        i.putExtra("NAME", "MosesKT")
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
        startActivity(Intent(this@UserHomeActivity, LoginActivity::class.java))
    }

    companion object {
        var databaseOrders: DatabaseReference? = null
        val order: Unit
            get() {
                databaseOrders = FirebaseDatabase.getInstance().getReference("orders")
            }
    }
}