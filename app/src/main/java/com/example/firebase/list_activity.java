package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class list_activity extends AppCompatActivity {
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference mref=database.getReference("products");
    private static final String TAG = "MyActivity";
    ArrayList<product> list;
    RecyclerView recycler;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_activity);

        recycler=findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        list=new ArrayList<product>();
        final ArrayList<String> keys=new ArrayList<String>();

//        mref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot:dataSnapshot.getChildren())
//                {
//                    product product=(product) snapshot.getValue(com.example.firebase.product.class);
//                    Log.d(TAG, "onDataChange: product name: " + product.prod_name + ", price " + product.prod_price+",category: "+product.category
//                            +", description: "+product.description);
//                    list.add(product);
//                    adapter.notifyDataSetChanged();   // this addition is important otherwise it will not reflect in the recycler view
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(list_activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
        mref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                product product=dataSnapshot.getValue(com.example.firebase.product.class);
                list.add(product);
                keys.add(dataSnapshot.getKey());
                Log.d(TAG, "onChildAdded: product name: " + product.prod_name + ", price " + product.prod_price+",category: "+product.category
                         +", description: "+product.description);
                adapter.notifyDataSetChanged();
//                 child_added is triggered once for each existing child and then again every time a new child is added to the specified path.
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               String keytofound=dataSnapshot.getKey();
               int index=keys.indexOf(keytofound);
               product product=dataSnapshot.getValue(product.class);
               list.set(index,product);
                Log.d(TAG, "onChildchanged: product name: " + product.prod_name + ", price " + product.prod_price+",category: "+product.category
                        +", description: "+product.description);
               adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        ArrayList<product> lis2=new ArrayList<product>();
//        lis2.add(new product("aman","bjhabfh","kjbhjvs","kbshj"));
//        lis2.add(new product("sfsdf","gsgsg","gsgsgs","gsgsg"));
        adapter=new adapter_class(list_activity.this,list);
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(layoutManager);
    }
}
