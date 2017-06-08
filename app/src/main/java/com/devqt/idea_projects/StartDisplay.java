package com.devqt.idea_projects;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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

public class StartDisplay extends Activity implements View.OnClickListener {

    private EditText email;
    private EditText pass;
    private ImageView check_b;
    private TextView reg;
    private ProgressDialog progress;
    private FirebaseAuth authefication;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        email = (EditText) findViewById(R.id.mail_login);
        pass = (EditText) findViewById(R.id.pass_login);
        check_b = (ImageView) findViewById(R.id.check);
        reg = (TextView) findViewById(R.id.register);

        check_b.setOnClickListener(this);
        reg.setOnClickListener(this);
        progress = new ProgressDialog(this);
        authefication = FirebaseAuth.getInstance();
        if (authefication.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), NavigationMenu.class));
        }

    }

    private void loginUser() {

        String mail = email.getText().toString().trim();

        String password = pass.getText().toString().trim();

        if (TextUtils.isEmpty(mail)) {

            Toast.makeText(this, "Please enter Your e-mail", Toast.LENGTH_SHORT).show();
            return;

        }

        if (TextUtils.isEmpty(password)) {

            Toast.makeText(this, "Please enter Your password", Toast.LENGTH_SHORT).show();
            return;
        }

        progress.setMessage("Please wait...");
        progress.show();

        authefication.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), NavigationMenu.class));
                        } else {
                            Toast.makeText(StartDisplay.this, "Please, try again", Toast.LENGTH_SHORT).show();
                        }
                        progress.dismiss();
                    }
                });
    }


    @Override
    public void onClick(View view) {

        if (view == check_b) {
            loginUser();
        }

        if (view == reg) {

            startActivity(new Intent(this, Register.class));
            finish();
        }
    }
}