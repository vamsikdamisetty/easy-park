package com.example.vamsi.easy_parking;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book_Slot extends AppCompatActivity {

    String[] tableStatus;
    String city,place,vehicle,time;
    ProfileOpenHelper profileOpenHelper;
    String curDate;
    GridLayout gridLayout;
    View card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__slot);

        gridLayout = findViewById(R.id.bookslot_gl);
        Button back = findViewById(R.id.go_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Book_Slot.this,FirstPage.class);
                startActivity(intent);
            }
        });



        profileOpenHelper = new ProfileOpenHelper(this);
        Intent intent= getIntent();
        city = intent.getStringExtra("city");
        place = intent.getStringExtra("place");
        vehicle = intent.getStringExtra("vehicle");
        time = intent.getStringExtra("time");


        setTableView();
        Toast.makeText(getApplicationContext(),city + place,Toast.LENGTH_SHORT).show();

    }

    private void enterData() {

        profileOpenHelper.insertItem2(city,place,vehicle,time);

        Cursor cursor = profileOpenHelper.getData();
        String x=(cursor.getString(cursor.getColumnIndexOrThrow(profileOpenHelper.city)));
        Toast.makeText(this,x,Toast.LENGTH_SHORT).show();


    }

    public void setTableView()
    {
        SharedPreferences sp = getSharedPreferences("Booked Table",MODE_PRIVATE);
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY");
        Date curDatee = new Date();
        curDate = dateFormat.format(curDatee);

        tableStatus = sp.getString(place+curDate+time,"0 0 0 0 0 0 0 0 0 0 0 0").split(" ");
        Toast.makeText(getApplicationContext(),time,Toast.LENGTH_SHORT).show();

        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ViewGroup v = (ViewGroup) gridLayout.getChildAt(i);
            if(tableStatus[i].equals("1")){
                ((CardView)v).setBackgroundColor(Color.parseColor("#eda1a6a6"));
                v.setEnabled(false);
            }
        }
    }

    public void selectSlot(View v){
        ((CardView)v).setBackgroundColor(Color.parseColor("#ed6bef53"));
        card = v;
    }

    public void bookSlot(View v){
        String s="";
        int x = Integer.parseInt(card.getTag().toString());
        tableStatus[x] = "1";
        for (int i=0;i<12;i++)
            s += tableStatus[i]+" ";
        SharedPreferences sp = getSharedPreferences("Booked Table",MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        editor.putString(place+curDate+time,s);
        editor.commit();

        View v2=getLayoutInflater().inflate(R.layout.dialog_booking_money,(ViewGroup)findViewById(R.id.dialog_payment));
        final Dialog dialog = new Dialog(Book_Slot.this);
        dialog.setContentView(v2);


        SharedPreferences user = getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        final String uname = user.getString("CurUser","");
        SharedPreferences sp_wallet = getSharedPreferences("Wallet Amount", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor2 = sp_wallet.edit();
        final int uamount = sp_wallet.getInt(uname,0);
        ((TextView)v2.findViewById(R.id.tv_Book_e_money)).setText("Amount in you wallet is: "+uamount);
        Button b=v2.findViewById(R.id.makepayment);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor2.putInt(uname,uamount-40);

                editor2.commit();
                dialog.dismiss();

                Toast.makeText(getApplicationContext(),"Payment done",Toast.LENGTH_SHORT).show();

           }
        });






        dialog.show();
       // enterData();

    }
}
