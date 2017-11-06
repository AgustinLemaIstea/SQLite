package com.example.educacionit.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.Usuario;
import database.DBHelper;

public class ElementEditActivity extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtApellido;
    private EditText edtEmail;
    private Button btnSubmit;
    Usuario usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_edit);
        setupUI();
        setupButton();
        loadUsuario();
    }



    private void setupUI() {
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtApellido = (EditText) findViewById(R.id.edtApellido);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
    }

    private void setupButton() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("superlog", "onClick: Tengo click");
                if (validateFields()){
                    Log.d("superlog", "onClick: es valido");
                    saveToDB();
                    Toast.makeText(ElementEditActivity.this, "Usuario guardado", Toast.LENGTH_SHORT).show();
                    //Volver a pantalla anterior.
                    finish();
                }
            }
        });
    }

    //Todo: Show error messages
    private boolean validateFields() {
        boolean isValid=true;
        if (edtNombre.getText().length()<=0)
            isValid=false;
        return isValid;
    }

    private void saveToDB() {

        if (usuario == null){
            usuario = new Usuario(0,"","","");
        }
        usuario.setNombre(edtNombre.getText().toString());
        usuario.setApellido(edtApellido.getText().toString());
        usuario.setEmail(edtEmail.getText().toString());
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.guardarUsuario(usuario);
    }

    private void loadUsuario() {
        //Load usuario from getextra
    }
}
