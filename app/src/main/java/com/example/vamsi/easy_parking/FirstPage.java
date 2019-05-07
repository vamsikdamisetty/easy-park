package com.example.vamsi.easy_parking;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class FirstPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String city,place;
    ListView lv;
    String Vehicle,time;
    TextView navHeader1,navHeader2;
    Button z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Easy Parking");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.btm_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.btm_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame,new Frag_place());
        ft.commit();

        View view = getLayoutInflater().inflate(R.layout.nav_header_first_page,(ViewGroup)findViewById(R.id.nav_view));
        inflateNavigationHeader(view);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
            switch (item.getItemId()) {
                case R.id.btm_home:
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.frame,new Frag_place());
                    ft.commit();
                    Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btm_bookings:
                    Toast.makeText(getApplicationContext(),"Bookings",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btm_profile:
                    Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_SHORT).show();
                    fragmentManager = getSupportFragmentManager();
                    ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.frame,new My_profile());
                    ft.commit();
                    break;

            }
            return true;
        }
    };



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finishAffinity();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG).show();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.frame,new Frag_place());
            ft.commit();
        } else if (id == R.id.profile) {
            Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_SHORT).show();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.frame,new My_profile());
            ft.commit();
        } else if (id == R.id.bookings) {


        } else if (id == R.id.wallet) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.frame,new E_Wallet());
            ft.commit();
        } else if(id == R.id.about){
            Intent intent = new Intent(getApplicationContext(),About.class);
            startActivity(intent);
        } else if(id == R.id.contact){
            Intent intent = new Intent(getApplicationContext(),Contact.class);
            startActivity(intent);
        }
        else if (id == R.id.logout){
            try{

                FileOutputStream fos = openFileOutput(R.string.login_status+"",MODE_PRIVATE );
                fos.write(0);
                fos.close();

            }catch (Exception e){

                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

            }
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void inflateNavigationHeader(View navView){
        navHeader1 = navView.findViewById(R.id.nav_txtv);
        navHeader2 = navView.findViewById(R.id.textView);
        SharedPreferences sp = getSharedPreferences("CurrentUser",MODE_PRIVATE );
        ProfileOpenHelper profileOpenHelper = new ProfileOpenHelper(this);
        Cursor cursor = profileOpenHelper.getData();
        while(cursor.moveToNext()){
            if((cursor.getString(cursor.getColumnIndexOrThrow(profileOpenHelper.username)).trim()).equals(sp.getString("CurUser","not found" ))){

                navHeader1.setText(cursor.getString(cursor.getColumnIndexOrThrow(profileOpenHelper.profile_name)));
                navHeader2.setText(cursor.getString(cursor.getColumnIndexOrThrow(profileOpenHelper.username)));
                break;
            }

        }
    }
    public void openWallet(View view)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame,new E_Wallet());
        ft.commit();
    }
    public void logout(View v)
    {
        try{

            FileOutputStream fos = openFileOutput(R.string.login_status+"",MODE_PRIVATE );
            fos.write(0);
            fos.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }



    public void gotoCity(View view)
    {
        TextView tv,tv2;
        int[] id= {R.id.txcd1,R.id.txcd2,R.id.txcd3,R.id.txcd4,R.id.txcd5,R.id.txcd6,R.id.txcd7};
        int[] id2 = {R.id.txcd11,R.id.txcd12,R.id.txcd13,R.id.txcd14,R.id.txcd15,R.id.txcd16,R.id.txcd17};
        String[] hyderabad={"CHARMINAR","SALAR JUNG","BIRLA MANDIR","MECCA MASJID","BALAJI TEMPLE","HUSSAIN SAGAR","RAMOJI FILM"};
        String[] MUMBAI ={"MARINE DRIVE","ELEPHANTA C.","GATEWAY ","ELEPHANTA I.","KANHERI CAVES","ESSEL WORLD","DHOBI GHAT"};
        String[] BANGLORE={"ISKON TEMPLE","B.PALACE","BANNER. PARK","BOT. GARDEN","V.SOUDHA","NAINDI TEMPLE","VISWESWARAYA"};
        String[] NEWDEL={"RED FORT","INDIA GATE","QUTUB MINAR","JAMA MASJID","LOTUS TEMPLE","AKSHARDAM","GANDHI SMRITI"};
        String[] CHANDIGARH = {"ROCK GARDEN","SUKHNA LAKE","ROSE GARDEN","CHATTBIR ZOO","ELANTE MALL","LE CORBUSIER","GURUDWARA"};
        switch (view.getId())
        {
            case R.id.city_hydera:
                for(int i=0;i<7;i++) {
                    tv =findViewById(id[i]) ;
                    tv.setText(hyderabad[i]);
                    tv2 =findViewById(id2[i]) ;
                    tv2.setText("("+"Hyderabad"+")");
                }
                break;

            case R.id.city_mumbai:
            for(int i=0;i<7;i++) {
                tv =findViewById(id[i]) ;
                tv.setText(MUMBAI[i]);
                tv2 =findViewById(id2[i]) ;
                tv2.setText("("+"Mumbai"+")");
            }
            break;

            case R.id.city_bangalore:
                for(int i=0;i<7;i++) {
                    tv =findViewById(id[i]) ;
                    tv.setText(BANGLORE[i]);
                    tv2 =findViewById(id2[i]) ;
                    tv2.setText("("+"Bangalore"+")");
                }
                break;

            case R.id.city_delhi:
                for(int i=0;i<7;i++) {
                    tv =findViewById(id[i]) ;
                    tv.setText(NEWDEL[i]);
                    tv2 =findViewById(id2[i]) ;
                    tv2.setText("("+"New Delhi"+")");
                }
                break;

            case R.id.city_chandi:
                for(int i=0;i<7;i++) {
                    tv =((TextView)findViewById(id[i])) ;
                    tv.setText(CHANDIGARH[i]);
                    tv2 =findViewById(id2[i]) ;
                    tv2.setText("("+"Chandigarh"+")");
                }
                break;
        }

    }

    public void chooseOrdertype(View v)
    {


        RadioGroup radio=null;
        Spinner sp1=null,sp2=null;

        final View v2 = (ViewGroup)v;
        final View v3 = getLayoutInflater().inflate(R.layout.dialog_places,(ViewGroup)findViewById(R.id.places_dialog));
        final Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(v3);
        dialog.setTitle("Payment Pending....");
        radio = v3.findViewById(R.id.radio);
        z = v3.findViewById(R.id.b1);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                z.setEnabled(true);
                if(checkedId == R.id.r1)
                    Vehicle = "Two wheeler";
                else
                    Vehicle = "Four Wheeler";
            }
        });
        sp1 = v3.findViewById(R.id.spnr_time);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                time=(String)parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        for(int i=0;i<((ViewGroup)v2).getChildCount();i++)
        {
            ViewGroup t =   (ViewGroup) ((ViewGroup) v2).getChildAt(i);
            for(int j=0;j<t.getChildCount();j++)
            {
                if(j==2)
                    place = ((TextView)t.getChildAt(j)).getText().toString();
                if(j == 3) {
                    city = ((TextView) t.getChildAt(j)).getText().toString();
                    city = city.substring(1, city.length() - 1);
                }
            }

        }
        dialog.findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {

                Toast.makeText(getApplicationContext(),time,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v2.getContext(),Book_Slot.class);
                intent.putExtra("city",city);
                intent.putExtra("place",place);
                intent.putExtra("vehicle",Vehicle);
                intent.putExtra("time",time);
                startActivity(intent);
            }
        });


          Spinner spnr_from=dialog.findViewById(R.id.spnr_time);
          String[] time = new String[]{"10AM to 12PM","12PM to 2PM","2PM to 4PM","4PM to 6PM","6PM to 8PM","8PM to 10PM"};

        ArrayAdapter<String> time_adapter = new ArrayAdapter<String>(dialog.getContext(),R.layout.support_simple_spinner_dropdown_item,time);
        time_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnr_from.setAdapter(time_adapter);


        dialog.show();
    }
    
    public void onProfile(View v)
    {
        Intent intent = null;
        int id=v.getId();
        if(id == R.id.profile_contact)
        {
            intent = new Intent(v.getContext(),Contact.class);
            startActivity(intent);
        }
        else if(id == R.id.profile_about)
            intent = new Intent(v.getContext(),About.class);
            startActivity(intent);
    }

}
