package com.example.createcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class createcontact extends AppCompatActivity implements View.OnClickListener {

    ImageView ivhappy, ivsad,ivok;
    EditText etname ,etlocation,etnumber,etweb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createcontact);

        ivhappy = findViewById(R.id.ivhappy);
        ivhappy = findViewById(R.id.ivhappy);
        ivhappy = findViewById(R.id.ivhappy);

        etlocation = findViewById(R.id.etlocation);
        etweb = findViewById(R.id.etweb);
        etnumber = findViewById(R.id.etnumber);
        etname = findViewById(R.id.etname);

        ivhappy.setOnClickListener(this);
        ivsad.setOnClickListener(this);
        ivok.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (etname.getText().toString().isEmpty() || etnumber.getText().toString().isEmpty() ||
                etweb.getText().toString().isEmpty() || etlocation.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Fill All The Fields!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("name", etname.getText().toString().trim());
            intent.putExtra("number", etnumber.getText().toString().trim());
            intent.putExtra("web", etweb.getText().toString().trim());
            intent.putExtra("location", etlocation.getText().toString().trim());

            if(view.getId() == R.id.ivhappy) {
                intent.putExtra("mood", "Happy");
            } else if(view.getId()== R.id.ivok)
            {
                intent.putExtra("mood", "ok");
            }
            else{
                intent.putExtra("mood", "Sad");
            }

            setResult(RESULT_OK, intent);
            createcontact.this.finish();
        }
    }

}
