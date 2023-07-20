package com.example.createcontact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ImageView ivLocation, ivWeb, ivPhone, ivHappy;
    Button btnCreate;
     final int CREATE_CONTACT_REQUEST_CODE = 1;
     String name="",web="",location="",number="",mood="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivHappy = findViewById(R.id.ivHappy);
        ivLocation = findViewById(R.id.ivLocation);
        ivWeb = findViewById(R.id.ivWeb);
        ivPhone = findViewById(R.id.ivPhone);
        btnCreate = findViewById(R.id.btnCreate);

        ivLocation.setVisibility(View.GONE);
        ivPhone.setVisibility(View.GONE);
        ivWeb.setVisibility(View.GONE);
        ivHappy.setVisibility(View.GONE);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.createcontact.createcontact.class);
                startActivityForResult(intent, CREATE_CONTACT_REQUEST_CODE);
            }
        });



        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ number));
            startActivity(intent);
            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent= new Intent(Intent.ACTION_VIEW,Uri.parse("geo: 0,0?q="+location));
            startActivity(intent);
            }
        });

        ivWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://"+web));
            startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CREATE_CONTACT_REQUEST_CODE)
        {
            if(resultCode==RESULT_OK)
            {
                ivPhone.setVisibility(View.VISIBLE);
                ivLocation.setVisibility(View.VISIBLE);
                ivWeb.setVisibility(View.VISIBLE);
                ivHappy.setVisibility(View.VISIBLE);

                name = data.getStringExtra("name");
                number =data.getStringExtra("number");
                web=data.getStringExtra("web");
                location=data.getStringExtra("location");
                mood=data.getStringExtra("mood");

                if(mood.equals("happy"))
                {
                    ivHappy.setImageResource(R.drawable.happy);
                } else if (mood.equals("ok")) {
                    ivHappy.setImageResource(R.drawable.ok);
                }else {
                    ivHappy.setImageResource(R.drawable.sad);
                }

            }
            else
            {
                Toast.makeText(this, "No data passed through!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
