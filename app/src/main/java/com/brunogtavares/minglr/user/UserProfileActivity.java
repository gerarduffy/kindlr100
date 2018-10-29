package com.brunogtavares.minglr.user;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class UserProfileActivity extends AppCompatActivity {

    private DatabaseReference mUsersDb;
    private String userClickedOnId;
    private String userClickedOnEmail;
    private FirebaseAuth mAuth;
    private ArrayList<Card> userClickedOnBooks = new ArrayList<Card>();
    private ArrayList<String> userClickedOnBookNames = new ArrayList<String>();
    private ArrayAdapter<String> userClickedOnBooksAdapter;
    private ListView userClickedOnBookList;
    private TextView userClickedOnName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mUsersDb = FirebaseDatabase.getInstance().getReference().child(FirebaseContract.FirebaseEntry.TABLE_USERS);
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        userClickedOnId = (String) intent.getSerializableExtra("userClickedOnId");

        userClickedOnName = findViewById(R.id.userClickedOnName);
        FirebaseDatabase.getInstance().getReference().child("Users").child(userClickedOnId).child("email").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userClickedOnEmail = (String) dataSnapshot.getValue();
                userClickedOnName.setText(userClickedOnEmail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userClickedOnBookList = findViewById(R.id.userClickedOnBooks);

        // Getting card list from database
        FirebaseDatabase.getInstance().getReference().child("Posts").child(userClickedOnId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Card currentCard = snapshot.getValue(Card.class);
                    userClickedOnBooks.add(currentCard);
                    userClickedOnBookNames.add(currentCard.title);
                }
                userClickedOnBooksAdapter = new ArrayAdapter<String>(UserProfileActivity.this, android.R.layout.simple_dropdown_item_1line, userClickedOnBookNames);
                userClickedOnBookList.setAdapter(userClickedOnBooksAdapter);
                userClickedOnBookList.setClickable(true);
                userClickedOnBookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        // Open options to delete book from list.
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        userClickedOnBooksAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, userClickedOnBookNames);
        userClickedOnBookList.setAdapter(userClickedOnBooksAdapter);
    }
}
