package com.example.expensetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.R;
import com.example.expensetracker.model.StoreModel;

import java.util.ArrayList;

public class StoreRVAdapter extends RecyclerView.Adapter<StoreRVAdapter.ViewHolder> {

    private ArrayList<StoreModel> storeModelArrayList;
    private Context context;

    public StoreRVAdapter(ArrayList<StoreModel> storeModalArrayList, Context context) {
        this.storeModelArrayList = storeModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StoreModel model = storeModelArrayList.get(position);
        holder.storeNameTV.setText(model.getName());
        holder.storeAddressTV.setText(model.getAddress());
        holder.storeTypeTV.setText(model.getType());
    }

    @Override
    public int getItemCount() {
        return storeModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView storeNameTV, storeAddressTV, storeTypeTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storeNameTV = itemView.findViewById(R.id.idTVStoreName);
            storeAddressTV = itemView.findViewById(R.id.idTVStoreAddress);
            storeTypeTV = itemView.findViewById(R.id.idTVStoreType);
        }
    }
}
