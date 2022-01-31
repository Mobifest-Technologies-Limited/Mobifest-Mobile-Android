package co.mobifest.mobile.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import co.mobifest.mobile.ui.shopping.ShoppingActivity
import co.mobifest.mobile.ui.LoginActivity
import co.mobifest.mobile.R
import co.mobifest.mobile.ui.locomotive.LocomotiveHomeActivity
import co.mobifest.mobile.ui.rentals.RentalsActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserHomeActivity : AppCompatActivity() {
    private lateinit var shoppingLinkLayout: LinearLayout
    private lateinit var rentalsLinkLayout: LinearLayout
    private lateinit var locomotiveLayout: LinearLayout
    private lateinit var toolbar: Toolbar

    private lateinit var drawer: DrawerLayout
    private lateinit var navigationView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)
        toolbar = findViewById(R.id.user_home_toolbar)
        drawer = findViewById(R.id.user_home_drawer_layout)
        navigationView = findViewById(R.id.home_nav_view)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        shoppingLinkLayout = findViewById(R.id.shopping_link_layout)
        rentalsLinkLayout = findViewById(R.id.rentals_link_layout)
        locomotiveLayout = findViewById(R.id.locomotive_layout)
        shoppingLinkLayout.setOnClickListener(returnOnClickListener())
        rentalsLinkLayout.setOnClickListener(returnOnClickListener())
        locomotiveLayout.setOnClickListener(returnOnClickListener())
        initializeNavigationDrawer()
    }

    private fun initializeNavigationDrawer() {
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.open_nav_drawer,
            R.string.close_nav_drawer
        )
        toolbar.setNavigationOnClickListener {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START)
            } else {
                drawer.openDrawer(GravityCompat.START)
            }
        }
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.itemIconTintList = null
        navigationView.setNavigationItemSelectedListener{
            Log.d("TESTING", "HERE")

            return@setNavigationItemSelectedListener true

        }
//        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }


    private fun returnOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            when (it) {
                shoppingLinkLayout -> loadShoppingModule(it)
                rentalsLinkLayout -> loadRentalsModule(it)
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

    fun loadRentalsModule(view: View) {
        val i = Intent(this@UserHomeActivity, RentalsActivity::class.java)
        i.putExtra("NAME", "MosesKT")
        i.putExtra("PHONE", "+256789608541")
        i.putExtra("PASSWORD", "userPassword")
        i.putExtra("CALLINGACTIVITY", "LoginPage")
        startActivity(i)
    }

    fun loadShoppingModule(view: View) {
        val i = Intent(this@UserHomeActivity, ShoppingActivity::class.java)
        i.putExtra("NAME", "MosesKT")
        i.putExtra("PHONE", "+256789608541")
        i.putExtra("PASSWORD", "userPassword")
        i.putExtra("CALLINGACTIVITY", "LoginPage")
        startActivity(i)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_nav_menu, menu)
        return true
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