package com.nbs.lutfi.trymvpbutterdagger.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nbs.lutfi.trymvpbutterdagger.R;
import com.nbs.lutfi.trymvpbutterdagger.mainscreen.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_email) EditText userEmail;
    @BindView(R.id.login_pass) EditText userPass;
    @BindView(R.id.login_progressBar) ProgressBar loginProg;
    @BindView(R.id.btn_login) Button btnLogin;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        auth = FirebaseAuth.getInstance();
//        if(auth.getCurrentUser() != null){
//            Intent i = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(i);
//            finish();
//        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();
        }
    @OnClick(R.id.btn_login)
    public void login(Button b){
        String email = userEmail.getText().toString();
        final String pass = userPass.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Masukan email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(getApplicationContext(), "Masukan password!", Toast.LENGTH_SHORT).show();
            return;
        }

        loginProg.setVisibility(View.VISIBLE);

        //auth
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                loginProg.setVisibility(View.GONE);
                if(!task.isSuccessful()){
                    if(pass.length()<6){
                        userPass.setError(getString(R.string.minimum_password));
                    }else{
                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                    }
                }else{
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
