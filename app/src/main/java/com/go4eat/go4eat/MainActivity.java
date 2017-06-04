package com.go4eat.go4eat;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private TextView mOrderTextView;

    // Firebase Objects
    private FirebaseDatabase mFirebaseDatabaseClient;
    private DatabaseReference mDatabaseReferenceClient;
    private FirebaseDatabase mFirebaseDatabaseProvider;
    private DatabaseReference mDatabaseReferenceProvider;
    private FirebaseOptions optionsSecondaryFirebase = new FirebaseOptions.Builder()
            .setApplicationId("1:231228455236:android:f7463e4c292b0c9d") // Required for Analytics.
            .setApiKey("AIzaSyBmxr2j77P571cuMDbIJLytl5ts7jL7J4o") // Required for Auth.
            .setDatabaseUrl("https://go4eatprovider-5dac4.firebaseio.com/") // Required for RTDB.
            .build();

    private DatabaseReference mUserOrdersReference;
    private ChildEventListener mUserOrdersListener;
    private String userId = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This is the initialization for the app's database
        mFirebaseDatabaseClient = FirebaseDatabase.getInstance();
        mDatabaseReferenceClient =  mFirebaseDatabaseClient.getReference();

        // This is the initialization for the projects global database
        FirebaseApp.initializeApp(this /* Context */, optionsSecondaryFirebase, "secondary");
        FirebaseApp app = FirebaseApp.getInstance("secondary");
        mFirebaseDatabaseProvider = FirebaseDatabase.getInstance(app);
        mDatabaseReferenceProvider = mFirebaseDatabaseProvider.getReference();
        mUserOrdersReference = mDatabaseReferenceProvider
                .child("users")
                .child(userId)
                .child(userId+"_ready_orders")
                .child("03_06_2017");

        mUserOrdersListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(MainActivity.this, "An Order is ready!", Toast.LENGTH_SHORT).show();
                Order order = dataSnapshot.getValue(Order.class);
                mOrderTextView = (TextView) findViewById(R.id.order_text_view);
                mOrderTextView.append(order.toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mUserOrdersReference.addChildEventListener(mUserOrdersListener);

//        mDatabaseReferenceProvider.child("users_testing").child("24").setValue("bla bla");
//        mDatabaseReferenceProvider.child("users_testing").child("25").setValue("bla bla");
//        mDatabaseReferenceProvider.child("users_testing").child("24").child("bla bla").setValue("blue blue");

//        ValueEventListener orderListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Order object and use the values to update the UI
//                Order order = dataSnapshot.getValue(Order.class);
//                mOrderTextView = (TextView) findViewById(R.id.order_text_view);
//                mOrderTextView.append(order.toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Order failed, log a message
//                // TODO: Implement this...
////                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//                // ...
//            }
//        };
//        mUserOrdersReference.addValueEventListener(orderListener);

//        mOrderTextView = (TextView) findViewById(R.id.order_text_view);
//
//        for(int i=1; i<=100; i++){
//            mOrderTextView.append("Test"+i+"\n");
//        }

    }

}
