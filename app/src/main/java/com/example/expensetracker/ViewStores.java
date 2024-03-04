package com.example.expensetracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.db.StoreDatabaseHelper;
import com.example.expensetracker.model.StoreModel;

import java.util.ArrayList;

public class ViewStores extends AppCompatActivity {

    // Variables for array list, database handler, adapter, and recycler view.
    private ArrayList<StoreModel> storeModelArrayList;
    private StoreDatabaseHelper dbHandler;
    private StoreRVAdapter storeRVAdapter;
    private RecyclerView storesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_view);

        // Initialize variables.
        storeModelArrayList = new ArrayList<>();
        dbHandler = new StoreDatabaseHelper(ViewStores.this);

        // Get store array list from database handler class.
        storeModelArrayList = dbHandler.readStores();

        // Pass the array list to the adapter class.
        storeRVAdapter = new StoreRVAdapter(storeModelArrayList, ViewStores.this);
        storesRV = findViewById(R.id.idRVStores);

        // Set layout manager for the recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewStores.this, RecyclerView.VERTICAL, false);
        storesRV.setLayoutManager(linearLayoutManager);

        // Set the adapter to the recycler view.
        storesRV.setAdapter(storeRVAdapter);
    }
}
