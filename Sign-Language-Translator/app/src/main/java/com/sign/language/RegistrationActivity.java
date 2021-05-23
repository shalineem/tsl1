package com.sign.language;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;


public class RegistrationActivity extends Activity {
    EditText fname, lname, email, username, password,mname,mob;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
    }

    private void init() {
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        email = (EditText) findViewById(R.id.email);
        mname = (EditText) findViewById(R.id.mname);
        mob = (EditText) findViewById(R.id.phone);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.register);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fname.getText().toString().length() == 0) {
                    Toast.makeText(RegistrationActivity.this, "Please enter first name...", Toast.LENGTH_SHORT).show();
                } else if (mname.getText().toString().length() == 0) {
                    Toast.makeText(RegistrationActivity.this, "Please enter Middle name...", Toast.LENGTH_SHORT).show();
                } else if (lname.getText().toString().length() == 0) {
                    Toast.makeText(RegistrationActivity.this, "Please enter last name...", Toast.LENGTH_SHORT).show();
                } else if(!isValidEmailId(email.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(), "Email Address is not valid.", Toast.LENGTH_SHORT).show();
                } else if (mob.getText().toString().length() == 0) {
                    Toast.makeText(RegistrationActivity.this, "Please enter phone number...", Toast.LENGTH_SHORT).show();
                } else if (username.getText().toString().length() == 0) {
                    Toast.makeText(RegistrationActivity.this, "Please enter user name...", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().length() < 6) {
                    Toast.makeText(RegistrationActivity.this, "Password should be more than 6 character", Toast.LENGTH_SHORT).show();
                } else {
                    Database database = new Database(RegistrationActivity.this);
                    try {
                        database.deletedata();
                        database.addData(username.getText().toString(), password.getText().toString());
                        Toast.makeText(RegistrationActivity.this, "Registration successfull please login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });
        // fname=(EditText)findViewById(R.id.fname);
    }
    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

}