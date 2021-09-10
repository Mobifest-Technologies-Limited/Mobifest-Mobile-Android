package co.mobifest.mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import co.mobifest.mobile.ui.shopping.ShoppingUserHomeActivity;


public class Books extends AppCompatActivity {

    private RecyclerView recyclerView;
    private int[] images={R.drawable.meditation,R.drawable.maths,R.drawable.literature, R.drawable.horrorstorybook,R.drawable.firstyearengg,R.drawable.encyclopedia,R.drawable.dsa,R.drawable.music};
    private String[] details={"Meditation","Maths","Literature","Horror Story Book","First Year Engineering Bookset","Encyclopedia","DSA","Music"};
    private int[] prices={399,699,899,459,2299,1299,649,799};
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        recyclerView=findViewById(R.id.rvBooks);
        final String sna=getIntent().getStringExtra("NAME");
        final String sph=getIntent().getStringExtra("PHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        layoutManager=new GridLayoutManager(this,1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerAdapter(images,details,prices,this,sna,sph,spa,"Books");
        recyclerView.setAdapter(adapter);

    }
    public void onBackPressed(){
        final String sna=getIntent().getStringExtra("NAME");
        final String sph=getIntent().getStringExtra("PHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        Intent intent=new Intent(Books.this, ShoppingUserHomeActivity.class);
        intent.putExtra("NAME",sna);
        intent.putExtra("PHONE",sph);
        intent.putExtra("PASSWORD",spa);
        intent.putExtra("CALLINGACTIVITY","Division");
        startActivity(intent);
    }


}
