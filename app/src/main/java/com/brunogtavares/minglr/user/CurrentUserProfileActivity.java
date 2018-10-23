package com.brunogtavares.minglr.user;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.brunogtavares.minglr.FirebaseData.FirebaseContract;
import com.brunogtavares.minglr.R;
import com.brunogtavares.minglr.cards.Card;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CurrentUserProfileActivity extends AppCompatActivity {
    private FirebaseUser mCurrentUser;
    private DatabaseReference mUsersDb;
    private String mCurrentUserId;
    private FirebaseAuth mAuth;

    private ArrayList<Card> currentUserBooks = new ArrayList<Card>();
    private ArrayList<String> currentUserBookNames = new ArrayList<String>();
    private ArrayAdapter<String> currentUserBooksAdapter;
    private ListView currentUserBookList;
    private TextView currentUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_user_profile);

        mUsersDb = FirebaseDatabase.getInstance().getReference().child(FirebaseContract.FirebaseEntry.TABLE_USERS);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUserId = mAuth.getCurrentUser().getUid();
        mCurrentUser = mAuth.getCurrentUser();

        currentUserName = findViewById(R.id.currentUserName);
        currentUserName.setText(mCurrentUser.getEmail());


        currentUserBookList = (ListView) findViewById(R.id.currentUserBooks);
        // Getting card list from database
        Log.d("test", "test");
        FirebaseDatabase.getInstance().getReference().child("Posts").child(mCurrentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Card currentCard = new Card();
                    currentCard.title = snapshot.toString();
                    currentCard.exchange = snapshot.child("exchange").toString();
                    currentCard.sell = snapshot.child("sell").toString();
                    currentUserBooks.add(currentCard);
                    currentUserBookNames.add(currentCard.title);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        for (String name : currentUserBookNames) {
            Log.d("title", name);
        }
        currentUserBooksAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, currentUserBookNames);
        currentUserBookList.setAdapter(currentUserBooksAdapter);
    }
}
