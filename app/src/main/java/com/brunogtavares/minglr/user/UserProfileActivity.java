package com.brunogtavares.minglr.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.brunogtavares.minglr.FirebaseData.FirebaseContract;
import com.brunogtavares.minglr.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileActivity extends AppCompatActivity {

    private User mCurrentUser;
    private DatabaseReference mUsersDb;
    private String mCurrentUserId;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mUsersDb = FirebaseDatabase.getInstance().getReference().child(FirebaseContract.FirebaseEntry.TABLE_USERS);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUserId = mAuth.getCurrentUser().getUid();


    }
}
