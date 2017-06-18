package com.example.huangshifeng.RingoCamel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by huangshifeng on 11/06/2017.
 */
public class RegisterActivity extends Activity {
    private EditText username, userpass, repass, email;
    private Button login, register, exit, reset;
    private RadioGroup radioGroup;
    private RadioButton boy, girl;
    private Spinner profession;
    private String name, password, sex, job;
    private String usermail = "\\w{0,}\\@\\w{2,}\\.\\w{0,}";
    String professions[] = {"teacher", "Android Developer", "Student", "Kitchen Hand", "Bartender"};

    @Override
    protected void onCreate(Bundle SaveInstanceState){
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.registerlayout);
        init();
        buttonListener();
    }

    public void buttonListener() {
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if("".equals(username.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "username cannot be empty", Toast.LENGTH_LONG).show();
                }
            }
        });
        userpass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if("".equals(userpass.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "user password cannot be empty", Toast.LENGTH_LONG).show();
                }
            }
        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(email.getText().toString().matches(usermail)){
                    Toast.makeText(RegisterActivity.this, "Email format incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == boy.getId()) {
                    sex = boy.getText().toString();
                    Toast.makeText(getApplicationContext(), "You choosed" + boy.getText().toString(), Toast.LENGTH_LONG).show();
                }
                if (checkedId == girl.getId()) {
                    sex = girl.getText().toString();
                    Toast.makeText(getApplicationContext(), "You choosed" + girl.getText().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
        profession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                job = professions[position];
                System.out.println("Job:" + job);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getText().toString().trim();
                password = userpass.getText().toString().trim();
                String repassword = repass.getText().toString().trim();
                String emails = email.getText().toString().trim();
                if (!password.equals(repass)) {
                    Toast.makeText(RegisterActivity.this, "two passwords are different", Toast.LENGTH_LONG).show();
                    userpass.setText("");
                    repass.setText("");
                }
                if (!email.getText().toString().matches(usermail)) {
                    Toast.makeText(RegisterActivity.this, "email format incorrect", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Congraduation!!, " +
                            "Register successful, \n\n\nusername:" + name +
                            "\npassword:" + password + "\nSex:" + sex + "\nJob:" + job, Toast.LENGTH_LONG).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getText().toString();
                password = userpass.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("name", name);
                intent.putExtra("password", password);
                intent.setClass(RegisterActivity.this, LoginActivity.class);
                setResult(0, intent);
                finish();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
                userpass.setText("");
                repass.setText("");
                email.setText("");
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void init(){
        username = (EditText)this.findViewById(R.id.editText1);
        userpass = (EditText)this.findViewById(R.id.editText2);
        repass = (EditText)this.findViewById(R.id.editText3);
        email = (EditText)this.findViewById(R.id.editText4);
        register = (Button)this.findViewById(R.id.button1);
        login = (Button)this.findViewById(R.id.button2);
        reset = (Button)this.findViewById(R.id.button3);
        exit = (Button)this.findViewById(R.id.button4);
        radioGroup = (RadioGroup)this.findViewById(R.id.sex);
        boy = (RadioButton)this.findViewById(R.id.radioButton);
        girl = (RadioButton)this.findViewById(R.id.radioButton2);
        profession = (Spinner)this.findViewById(R.id.spinner);
        profession.setPrompt("Please Choose your job");
        profession.setAdapter(new Profession(this));
    }

    class Profession extends BaseAdapter {
        Context context;
        Profession(Context context){
            this.context = context;
        }
        public int getCount(){
            return professions.length;
        }

        public Object getItem(int position){
            return position;
        }

        public long getItemId(int position){
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            TextView textview = new TextView(context);
            textview.setText(professions[position]);
            textview.setTextSize(20);
            textview.setTextColor(0xff000000);

            return textview;
        }
    }
}
