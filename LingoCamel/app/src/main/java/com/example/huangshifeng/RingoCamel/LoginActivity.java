package com.example.huangshifeng.RingoCamel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText username, userpass;
    private Button register, login, exit, reset;
    String name, password;
    private TextView textView;
    int i=1;//user fail count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        init();

        ButtonListener();
        System.out.println(">>On Create");
    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println(">>onStart");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        System.out.println(">>onRestart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        System.out.println(">>onResume");
    }

    @Override
    protected void onStop(){
        super.onStop();
        System.out.println(">>onStop");
    }

    public void ButtonListener(){
        username.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                textView.setText("Your username is: " + username.getText().toString());
                return false;
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                name = username.getText().toString();
                password = userpass.getText().toString();
                if (i <= 3) {
                    if (name.equals("user") && password.equals("123")) {
                        Intent intent = new Intent();
                        intent.putExtra("name", name);
                        intent.putExtra("password", password);
                        intent.setClass(LoginActivity.this, ShowActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "wrong user name or password", Toast.LENGTH_LONG).show();
                    }
                    i++;
                } else {
                    Toast.makeText(LoginActivity.this, "User not found, please click register", Toast.LENGTH_LONG).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                username.setText("");
                userpass.setText("");
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void init(){
        reset = (Button)findViewById(R.id.button3);
        exit = (Button)findViewById(R.id.button4);
        username = (EditText)findViewById(R.id.editText);
        userpass = (EditText)findViewById(R.id.editText5);
        login = (Button)findViewById(R.id.button1);
        register = (Button)findViewById(R.id.button2);
        textView = (TextView)findViewById(R.id.logintextview1);
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

}
