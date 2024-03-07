package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expensetracker.db.StoreDatabaseHelper;

public class StoreListActivity extends AppCompatActivity {

    // Declare your variables
    private EditText storeNameEdit, storeAddressEdit, storeTypeEdit;
    private Button addStoreBtn, readStoreBtn;
    private StoreDatabaseHelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);

        // initializing all our variables.
        storeNameEdit = findViewById(R.id.editStoreName);
        storeAddressEdit = findViewById(R.id.editStoreAddress);
        storeTypeEdit = findViewById(R.id.editStoreType);
        addStoreBtn = findViewById(R.id.addStoreBtn);
        readStoreBtn = findViewById(R.id.readStoreBtn);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new StoreDatabaseHelper(StoreListActivity.this);

        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // below line is to add on click listener for our add store button.
        addStoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get data from all edit text fields.
                String storeName = storeNameEdit.getText().toString();
                String storeAddress = storeAddressEdit.getText().toString();
                String storeType = storeTypeEdit.getText().toString();

                // validating if the text fields are empty or not.
                if (storeName.isEmpty() || storeAddress.isEmpty() || storeType.isEmpty()) {
                    Toast.makeText(StoreListActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Call a method to add new store to SQLite data and pass all values to it.
                dbHandler.addNewStore(storeName, storeAddress, storeType);

                // After adding the data, display a toast message.
                Toast.makeText(StoreListActivity.this, "Store has been added.", Toast.LENGTH_SHORT).show();
                storeNameEdit.setText("");
                storeAddressEdit.setText("");
                storeTypeEdit.setText("");
            }
        });

        readStoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opening a new activity via an intent.
                Intent i = new Intent(StoreListActivity.this, ViewStores.class);
                startActivity(i);
            }
        });
    }
}
