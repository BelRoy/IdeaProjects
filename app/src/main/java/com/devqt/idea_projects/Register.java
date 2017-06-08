package com.devqt.idea_projects;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends Activity implements View.OnClickListener {

    private ImageView check_but;

    private EditText mail_field;
    private EditText password_field;
    private TextView sign_in_check;

    private ProgressDialog progress;
    private FirebaseAuth authefication;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        authefication = FirebaseAuth.getInstance();

        progress = new ProgressDialog(this);

        check_but = (ImageView) findViewById(R.id.check);

        mail_field = (EditText) findViewById(R.id.mail);
        password_field = (EditText) findViewById(R.id.password);
        sign_in_check = (TextView) findViewById(R.id.sign_in);


        check_but.setOnClickListener(this);

        mail_field.setOnClickListener(this);
        password_field.setOnClickListener(this);
        sign_in_check.setOnClickListener(this);

    }


    private void registerUser(){

        String mail = mail_field.getText().toString().trim();

        String password = password_field.getText().toString().trim();

        if (TextUtils.isEmpty(mail)){

            Toast.makeText(this, "Please enter Your e-mail", Toast.LENGTH_SHORT).show();
            return;

        }

        if (TextUtils.isEmpty(password)){

            Toast.makeText(this, "Please enter Your password", Toast.LENGTH_SHORT).show();
            return;
        }

        progress.setMessage("Registering User");
        progress.show();

        authefication.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(Register.this, "Registered Successfull", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Register.this, "Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {

        if (view == check_but){
            registerUser();
        }

        if (view == sign_in_check);

    }
}
