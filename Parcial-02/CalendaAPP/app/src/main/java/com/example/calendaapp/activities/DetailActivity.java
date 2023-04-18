package com.example.calendaapp.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.calendaapp.MainActivity;
import com.example.calendaapp.R;
import com.example.calendaapp.fragments.DeleteFragment;
import com.example.calendaapp.interfaces.CRUDInterface;
import com.example.calendaapp.interfaces.DeleteInteface;
import com.example.calendaapp.utils.Constants;
import com.example.calendaapp.model.Empleado;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity implements DeleteInteface {

    TextView idDetail;
    TextView userDetail;
    TextView emailDetail;

    String responseBody;
    Button editButton;
    Button deleteButton;

    CRUDInterface crudInterface;

    Empleado empleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        idDetail = findViewById(R.id.idDetail);
        userDetail = findViewById(R.id.nameDetail);
        emailDetail = findViewById(R.id.emailDetail);

        int id = getIntent().getExtras().getInt("id");

        editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEdit();
            }
        });
        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(empleado.getId());
            }
        });
        getOne(id);
    }

    private void getOne(int id) {
        crudInterface = getCrudInterface();
        Call<Empleado> call = crudInterface.getOne(id);
        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                empleado = response.body();

                responseBody = String.valueOf(response.body());

                idDetail.setText(String.valueOf(empleado.getId()));
                userDetail.setText(empleado.getName());
                emailDetail.setText(String.valueOf(empleado.getEmail()));
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callEdit() {
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
        intent.putExtra("Empleado", empleado);
        startActivity(intent);
    }

    @Override
    public void showDeleteDialog(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DeleteFragment deleteFragment = new DeleteFragment("Eliminar Empleado ", empleado.getId(), this);
        deleteFragment.show(fragmentManager, "Alert");
    }

    @Override
    public void delete(int id) {
        crudInterface = getCrudInterface();
        Call<Empleado> call = crudInterface.delete(id);
        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {

                // fix this !! TODO
                 if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err on delete: ", response.message());
                    return;
                    }
                empleado = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), empleado.getName() + " deleted!!", Toast.LENGTH_LONG);
                toast.show();
                callMain();
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private CRUDInterface getCrudInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudInterface = retrofit.create(CRUDInterface.class);
        return crudInterface;
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}