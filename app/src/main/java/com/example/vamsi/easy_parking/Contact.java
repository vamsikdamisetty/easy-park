package com.example.vamsi.easy_parking;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Contact extends AppCompatActivity {

    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    public void contact(View view) {
        int id = view.getId();
        switch (id)
        {
            case R.id.contact_1:
                i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+((Button)findViewById(id)).getText().toString()));
                startActivity(i);

            case R.id.contact_2:
                i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+((Button)findViewById(id)).getText().toString()));
                startActivity(i);
            case R.id.contact_3:
                i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+((Button)findViewById(id)).getText().toString()));
                startActivity(i);

            case R.id.contact_4:
                i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+((Button)findViewById(id)).getText().toString()));
                startActivity(i);

            case R.id.contact_5:
                i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+((Button)findViewById(id)).getText().toString()));
                startActivity(i);
        }
    }
}
