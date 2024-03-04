package com.example.expensetracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.expensetracker.db.StoreDatabaseHelper;

public class SettingsActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText addressEditText;
    private EditText typeEditText;
    private StoreDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        nameEditText = findViewById(R.id.storeNameEditText);
        addressEditText = findViewById(R.id.storeAddressEditText);
        typeEditText = findViewById(R.id.storeTypeEditText);
        Button saveButton = findViewById(R.id.saveBtn);

        dbHelper = new StoreDatabaseHelper(this);

        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveButton.setOnClickListener(view -> saveFormDataToDatabase());
    }

    private void saveFormDataToDatabase() {
        String name = nameEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String type = typeEditText.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StoreDatabaseHelper.COLUMN_NAME, name);
        values.put(StoreDatabaseHelper.COLUMN_ADDRESS, address);
        values.put(StoreDatabaseHelper.COLUMN_TYPE, type);

        long newRowId = db.insert(StoreDatabaseHelper.TABLE_NAME, null, values);

        if (newRowId != -1) {
            Toast.makeText(SettingsActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SettingsActivity.this, "Error saving data", Toast.LENGTH_SHORT).show();
        }
    }
}
