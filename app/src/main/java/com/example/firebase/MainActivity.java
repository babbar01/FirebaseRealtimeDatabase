package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference mref;
    TextView tvResult;
    Button btnRecycler;
    private static final String TAG = "MyActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult=findViewById(R.id.tvResult);
        btnRecycler=findViewById(R.id.btnRecycler);

        database=FirebaseDatabase.getInstance();
        mref=database.getReference();
        mref.child("products").child("-M0h3iFR88_l8ioZVy85").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                product product=dataSnapshot.getValue(com.example.firebase.product.class);
                Log.d(TAG, "product name: " + product.prod_name + ", price " + product.prod_price+",category: "+product.category
                +", descripption: "+product.description);
                tvResult.setText("product name: " + product.prod_name + ", price " + product.prod_price+",category: "+product.category
                        +", descripption: "+product.description);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        btnRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,com.example.firebase.list_activity.class));
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.seller)
        {
            startActivity(new Intent(MainActivity.this,com.example.firebase.seller_activity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
