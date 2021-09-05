package co.mobifest.mobile.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import co.mobifest.mobile.LoginPage;
import co.mobifest.mobile.R;

public class UserHomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    static DatabaseReference databaseOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.user_home_nav_view);
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home)
//                .setDrawerLayout(drawer)
//                .build();


        final String sna=getIntent().getStringExtra("NAME");
        final String sph=getIntent().getStringExtra("PHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        final String ca=getIntent().getStringExtra("CALLINGACTIVITY");
        if(ca.equals("LoginPage"))
            Toast.makeText(this,"Hello ,"+sna+"!",Toast.LENGTH_SHORT).show();
        else if(ca.equals("PlaceOrder"))
            Toast.makeText(this,"Order Placed Successfully!",Toast.LENGTH_LONG).show();
        databaseOrders=FirebaseDatabase.getInstance().getReference("orders");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void onBackPressed(){
        startActivity(new Intent(UserHomeActivity.this, LoginPage.class));
    }
    public static void getOrder(){
        databaseOrders=FirebaseDatabase.getInstance().getReference("orders");
    }
}
