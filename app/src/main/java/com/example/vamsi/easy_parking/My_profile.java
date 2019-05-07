package com.example.vamsi.easy_parking;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class My_profile extends Fragment {

    Button b1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =inflater.inflate(R.layout.fragment_my_profile, container, false);

        b1 = v.findViewById(R.id.profile_wallet);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View vw) {
//                FirstPage f = new FirstPage();
//                f.openWallet();
//               // Toast.makeText(v.getContext(),"In btn",Toast.LENGTH_SHORT).show();
//            }
//        });


        return v;
    }
//    public void profilefrag(View v)
//    {
//        switch (v.getId())
//        {
//            case R.id.profile_details:
//                break;
//            case R.id.profile_favs:
//                break;
//            case R.id.profile_wallet:
//                FirstPage f = new FirstPage();
//                f.openWallet();
//                break;
//            case R.id.profile_about:
//                break;
//            case R.id.profile_logout:
//                try{
//
//                    FileOutputStream fos = new  FileOutputStream(R.string.login_status+"");
//                    fos.write(0);
//                    fos.close();
//
//                }catch (Exception e){
//                    Toast.makeText(v.getContext(), "error", Toast.LENGTH_SHORT).show();
//                }
//                Intent intent = new Intent(v.getContext(),MainActivity.class);
//                startActivity(intent);
//                break;
//        }
//    }

}
