package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.expensetracker.db.StoreDatabaseHelper;
import com.example.expensetracker.model.StoreModel;

import java.util.ArrayList;

public class ExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense);

        ArrayList<String> storeNames = new ArrayList<>();

        // Fetch store names from your SQLite database
        StoreDatabaseHelper dbHelper = new StoreDatabaseHelper(this);
        ArrayList<StoreModel> stores = dbHelper.readStores();
        for (StoreModel store : stores) {
            storeNames.add(store.getName());
        }

        Spinner spinner = findViewById(R.id.storeSpinner);

        // Create a custom ArrayAdapter to set text color
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, storeNames) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK); // Set text color to black
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView text = view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK); // Set text color to black
                return view;
            }
        };

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view -> finish());
    }
}

