package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class seller_activity extends AppCompatActivity {
    EditText etName,etDescription,etPrice,etCategory;
    Button btnAdd;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
   final DatabaseReference prodRef=ref.child("products");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_activity);
        etName=findViewById(R.id.etName);
        etDescription=findViewById(R.id.etDescription);
        etPrice=findViewById(R.id.etPrice);
        etCategory=findViewById(R.id.etCategory);
        btnAdd=findViewById(R.id.btnAdd);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etName.getText().toString().trim();
                String price=etPrice.getText().toString().trim();
                String category=etCategory.getText().toString().trim();
                String description=etDescription.getText().toString().trim();
               /* Map<String,product> products=new HashMap<>();
                products.put("prod1",new product("laptop","35000","tech","\n" +
                        "    2.3 GhzGHz Intel Pentium Gold 4417U processor\n" +
                        "    4GB DDR4 RAM\n" +
                        "    1TB 5400rpm hard drive\n" +
                        "    15.6-inch screen, Intel UHD Graphics 620 Graphics\n" +
                        "    Windows 10, Home operating system\n" +
                        "    1.77kg laptop\n"));
                products.put("prod2",new product(name,price,category,description));
                prodRef.setValue(products);
                */
               product newprod=new product(name, price, category, description);
               prodRef.push().setValue(newprod, new DatabaseReference.CompletionListener() {
                   @Override
                   public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                       if (databaseError!=null)
                       {
                           System.out.println(databaseError.getMessage());
                           Toast.makeText(seller_activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                       else
                       {
                           Toast.makeText(seller_activity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                       }
                   }
               });




            }
        });
    }
}
