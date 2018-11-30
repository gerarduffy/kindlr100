package com.brunogtavares.minglr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.brunogtavares.minglr.cards.Card;
import com.google.firebase.auth.FirebaseAuth;
import com.brunogtavares.minglr.FirebaseData.FirebaseContract.FirebaseEntry;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class creation_post extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    // this is me adding random shit so I can re commit

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_post);

        Button button = (Button) findViewById(R.id.PostBookButton);
        button.setOnClickListener(this);

        Button photoButton = (Button) findViewById(R.id.SelectPhotoButton);
        photoButton.setOnClickListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.SpinnerFeedback);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView title = (TextView) findViewById(R.id.priceTitle);
                EditText price = (EditText) findViewById(R.id.price);

                switch (i) {
                    case 0:
                        title.setVisibility(view.VISIBLE);
                        price.setVisibility(view.VISIBLE);
                        break;

                    case 1:
                        title.setVisibility(view.GONE);
                        price.setVisibility(view.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Log.d("CREATION", "page is created");
        mAuth = FirebaseAuth.getInstance();

        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.tags);
        String [] tagOptions = getResources().getStringArray(R.array.tagSuggestions);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tagOptions);
        textView.setAdapter(adapter);

    }

    public void sendFeedback() {

        final EditText titleField = (EditText) findViewById(R.id.EditTextName);
        String name = titleField.getText().toString();

        final Spinner SpinnerFeedback = (Spinner) findViewById(R.id.SpinnerFeedback);
        String postType = SpinnerFeedback.getSelectedItem().toString();

        final EditText price = (EditText) findViewById(R.id.price);

        String stringPrice = price.getText().toString();
        Long postPrice;

        if (!stringPrice.equals("")) {
            postPrice = Long.parseLong(stringPrice);
        } else {
            postPrice = Long.parseLong("-1");
        }

        // Add tags to database

        final EditText tagsField = (EditText) findViewById(R.id.tags);
        String fullTags = tagsField.getText().toString();
        String tagsNoWhiteSpace = fullTags.replaceAll("\\s","");

        String [] separateTags = tagsNoWhiteSpace.split(",");
        List<String> actualTags = Arrays.asList(separateTags);

        String title = name;
        String sell;
        String exchange;

        final EditText imageUrlField = (EditText) findViewById(R.id.imageUrl);
        String imageUrl = imageUrlField.getText().toString();

        //String owner = mAuth.getCurrentUser().getDisplayName();
        String id = mAuth.getCurrentUser().getUid();
        Log.d("posttype", postType);
        if (postType.equals("Sale")) {
            sell = "true";
            exchange = "false";
        } else {
            sell = "false";
            exchange = "true";
        }

        mAuth = FirebaseAuth.getInstance();

        DatabaseReference currentTablePosts = FirebaseDatabase.getInstance()
                .getReference().child(FirebaseEntry.TABLE_POSTS).child(id);

        Log.d("FIREBASE", "accessing database");

        Card newCard = new Card(mAuth.getUid(), title, exchange, sell, postPrice, actualTags, imageUrl);
        Log.d("FIREBASE", "made card");

        Map userInfo = new HashMap<String, Card>();
        userInfo.put(title, newCard);
        Log.d("FIREBASE", "added card to map");

        currentTablePosts.updateChildren(userInfo);
        Log.d("FIREBASE", "add to database");


        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        Log.d("PROFILE", "moving to profile page");
        startActivity(i);

    }


    private void selectImage() {

        Log.d("CREATION", "image is being selected");
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(creation_post.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            private DialogInterface dialog;
            private int item;

            @Override
            public void onClick(DialogInterface dialog, int item) {
                this.dialog = dialog;
                this.item = item;

                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);

                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent (
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Log.d("SELECT", "select from gallery");

        Bitmap bm=null;
        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);

        if (data != null) {
            Log.d("SELECT", "Data is not null");

            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                Log.d("SELECT", "bm is set");

            } catch (IOException e) {
                e.printStackTrace();
            }

            ivImage.setImageBitmap(bm);
            Log.d("SELECT", "image is set");

        }

        Log.d("SELECT", "done");

    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ivImage.setImageBitmap(thumbnail);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.PostBookButton:
                sendFeedback();
                break;
            case R.id.SelectPhotoButton:
                selectImage();
                break;
        }
        System.out.println("Clicked success");
    }
}

