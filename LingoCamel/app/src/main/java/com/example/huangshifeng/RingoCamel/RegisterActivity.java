package com.example.huangshifeng.RingoCamel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by huangshifeng on 11/06/2017.
 */
public class RegisterActivity extends Activity {
    private EditText username, userpass, repass;
    private Button login, register, exit, reset;
    private Spinner spinner_gen, spinner_lan;
    private String name, password;
    private String usermail = "\\w{0,}\\@\\w{2,}\\.\\w{0,}";
    private TextView Title, UserText, PassText, Gender, Language;

    @Override
    protected void onCreate(Bundle SaveInstanceState){
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.registerlayout);
        init();
        buttonListener();
        SpinnerListener();
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
        /*
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (email.getText().toString().matches(usermail)) {
                    Toast.makeText(RegisterActivity.this, "Email format incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });
        */
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getText().toString().trim();
                password = userpass.getText().toString().trim();
                String repassword = repass.getText().toString().trim();
                //String emails = email.getText().toString().trim();
                if (!password.equals(repass)) {
                    Toast.makeText(RegisterActivity.this, "two passwords are different", Toast.LENGTH_LONG).show();
                    userpass.setText("");
                    repass.setText("");
                }
                /*
                if (!email.getText().toString().matches(usermail)) {
                    Toast.makeText(RegisterActivity.this, "email format incorrect", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Congraduation!!, " +
                            "Register successful, \n\n\nusername:" + name +
                            "\npassword:" + password, Toast.LENGTH_LONG).show();
                }*/
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
                //email.setText("");
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void SpinnerListener()
    {
        ArrayAdapter<CharSequence> genderList = ArrayAdapter.createFromResource(this, R.array.gender_sel, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> languageList = ArrayAdapter.createFromResource(this, R.array.language_sel, android.R.layout.simple_spinner_dropdown_item);

        spinner_gen.setAdapter(genderList);
        spinner_lan.setAdapter(languageList);

    }
    private void init(){
        username = (EditText)this.findViewById(R.id.editText_username);
        userpass = (EditText)this.findViewById(R.id.editText_password);
        repass = (EditText)this.findViewById(R.id.editText_confirmpassword);
        register = (Button)this.findViewById(R.id.button_register);
        login = (Button)this.findViewById(R.id.button_login);
        reset = (Button)this.findViewById(R.id.button_reset);
        exit = (Button)this.findViewById(R.id.button_exit);
        spinner_gen = (Spinner)findViewById(R.id.spinner_gender);
        spinner_lan = (Spinner)findViewById(R.id.spinner_language);

        Title = (TextView)findViewById(R.id.textView_title);
        UserText = (TextView)findViewById(R.id.textView_username);
        PassText = (TextView)findViewById(R.id.textView_password);
        Gender = (TextView)findViewById(R.id.textView_confirm_password);
        Language = (TextView)findViewById(R.id.textView_language);

        Title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/centry_gothic.ttf"));
        UserText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/centry_gothic.ttf"));
        PassText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/centry_gothic.ttf"));
        Gender.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/centry_gothic.ttf"));
        Language.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/centry_gothic.ttf"));

    }
/*
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
    }*/
}
