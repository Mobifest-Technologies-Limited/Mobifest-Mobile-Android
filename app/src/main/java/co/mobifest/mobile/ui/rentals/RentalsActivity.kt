package co.mobifest.mobile.ui.rentals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import co.mobifest.mobile.R
import co.mobifest.mobile.ui.home.UserHomeActivity

class RentalsActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rentals)
        val toolbar = findViewById<Toolbar>(R.id.user_home_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        fragmentManager = supportFragmentManager

        loadFragment(RentalsHomeFragment())
    }

    fun loadFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.rentals_activity_container_frame_layout, fragment)
        fragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                when {
                    getVisibleFragment() is RentalsHomeFragment-> {
                        val intent = Intent(this, UserHomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    getVisibleFragment() is RentalDetailFragment-> {
                        loadFragment(RentalsHomeFragment())
                    }

                    getVisibleFragment() is RentalRequestFragment-> {
                        loadFragment(RentalDetailFragment())
                    }
                    else -> {

                    }
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun getVisibleFragment(): Fragment? {
        val fragments = fragmentManager.fragments
        for (fragment in fragments) {
            if (fragment != null && fragment.isVisible) return fragment
        }
        return null
    }
}