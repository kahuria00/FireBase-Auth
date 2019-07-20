package com.example.firebasedemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button btnReg;
private EditText etEmail;
private EditText etPassword;
private TextView tvRegistered;
private ProgressDialog progressDialog;
private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       firebaseAuth = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        btnReg= findViewById(R.id.btnReg);

        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);

        tvRegistered=findViewById(R.id.tvRegistered);
        btnReg.setOnClickListener(this);
        tvRegistered.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void registerUser(){
     String email=etEmail.getText().toString().trim();
     String password=etPassword.getText().toString().trim();
     if (TextUtils.isEmpty(email)){
         //email is empty
         Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
         return;// stop the function execution further
     }

        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;// stopping the function execution further
        }
      //if validations are true
        // use progress dialog to show

        progressDialog.setMessage("Registering User....");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()){
                         //user is successfully registered and logged in
                         //start profile activity
                         //display toast
                         Toast.makeText(MainActivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                     }
                     else{
                         Toast.makeText(MainActivity.this," Failed Register, please try again ",Toast.LENGTH_SHORT).show();
                     }
                    }
                });
    }
    @Override
    public void onClick(View view) {
        if(view==btnReg){
            registerUser();
        }
        if (view==tvRegistered){

        }
    }
}
