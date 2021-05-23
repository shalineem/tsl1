package com.sign.language;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{


    // UI references.
    TextView tvRegister;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        tvRegister=(TextView)findViewById(R.id.registerFirst);
        tvRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });
        Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEmailView.getText().toString().length() == 0) {
                    Toast.makeText(LoginActivity.this, "Please enter user name...", Toast.LENGTH_SHORT).show();
                } else if (mPasswordView.getText().toString().length() == 0) {
                    Toast.makeText(LoginActivity.this, "Please enter password..", Toast.LENGTH_SHORT).show();
                } else {
                    Database database = new Database(LoginActivity.this);
                    ArrayList<String> arrayList = database.getData();
                    if (arrayList.size() > 0 && arrayList != null) {
                        String username=mEmailView.getText().toString();
                        if (!arrayList.get(0).toString().equals(username)) {
                            Toast.makeText(LoginActivity.this, "Please enter correct user name", Toast.LENGTH_SHORT).show();
                        } else if (!arrayList.get(1).toString().equals(mPasswordView.getText().toString())) {
                            Toast.makeText(LoginActivity.this, "please enter correct password", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, SelectActivity.class);
                            startActivity(intent);
                            finish();
                            //  pDialog.dismiss();
                        }
                    }else {
                        Toast.makeText(LoginActivity.this, "Please register first", Toast.LENGTH_SHORT).show();
                        //pDialog.dismiss();
                    }

                   // send(mEmailView.getText().toString(), mPasswordView.getText().toString());
                }
            }
        });
        Button button1 = (Button) findViewById(R.id.register);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
    class MyProgressBar extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            pDialog.dismiss();

        }

    }

}

