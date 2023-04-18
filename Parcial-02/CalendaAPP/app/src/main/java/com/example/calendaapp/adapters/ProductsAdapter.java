package com.example.calendaapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.calendaapp.R;
import com.example.calendaapp.activities.DetailActivity;
import com.example.calendaapp.model.Empleado;

import java.util.List;

public class ProductsAdapter extends BaseAdapter {

    List<Empleado> products;

    Context context;
    TextView nameText;
    Button viewButton;

    public ProductsAdapter(List<Empleado> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return products.get(i).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.product_list, viewGroup, false);
        }
        nameText = view.findViewById(R.id.name);
        nameText.setText(products.get(position).getName());
        viewButton = view.findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDetail(products.get(position).getId());
            }
        });
        return view;
    }

    private void callDetail(int id) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("id", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
