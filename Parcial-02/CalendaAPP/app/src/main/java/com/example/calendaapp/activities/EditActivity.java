package com.example.calendaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calendaapp.MainActivity;
import com.example.calendaapp.R;
import com.example.calendaapp.dto.EmpleadoDto;
import com.example.calendaapp.interfaces.CRUDInterface;
import com.example.calendaapp.model.Empleado;
import com.example.calendaapp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditActivity extends AppCompatActivity {

    Empleado product; // rename

    // remove above

    EditText user;

    EditText password;

    EditText email;

    Button editButton;
    
    CRUDInterface crudInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent detailIntent = getIntent();
        product = (Empleado) detailIntent.getSerializableExtra("Empleado");

        Log.i("emple: ", product.toString()); // rename

        user = findViewById(R.id.nameEdit);
        password = findViewById(R.id.passwordEdit);
        email = findViewById(R.id.emailEdit);

        user.setText(product.getName());
        password.setText(product.getPassword());
        email.setText(product.getEmail());

        editButton = findViewById(R.id.editButton);

        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editButton.setEnabled(buttonEnabled());
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmpleadoDto dto = new EmpleadoDto(user.getText().toString(), password.getText().toString(), email.getText().toString());
                edit(dto);
            }
        });
    }

    private void edit(EmpleadoDto dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudInterface = retrofit.create(CRUDInterface.class);
        int id = product.getId();
        Call<Empleado> call = crudInterface.edit(id, dto);
        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                Empleado product = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), product.getName() + " edited!!", Toast.LENGTH_LONG);
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

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private boolean buttonEnabled() {
        return user.getText().toString().trim().length() > 0 && password.getText().toString().trim().length() > 0 && email.getText().toString().trim().length() > 0 ;
    }
}