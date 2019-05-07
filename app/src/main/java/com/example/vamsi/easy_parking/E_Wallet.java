package com.example.vamsi.easy_parking;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class E_Wallet extends Fragment {

    String uname;
    int money;
    EditText et1;
    TextView wallet_amount;
    SharedPreferences sp1,sp2;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_e__wallet, container, false);
        wallet_amount = v.findViewById(R.id.tv_E_money);

         sp1 = getActivity().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
         uname = sp1.getString("CurUser","");
         sp2 = getActivity().getSharedPreferences("Wallet Amount",Context.MODE_PRIVATE);
         Initialize_amount();



        Button add = v.findViewById(R.id.btn_addmoney);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View v2 = getLayoutInflater().inflate(R.layout.wallet_layout,(ViewGroup)v.findViewById(R.id.wallet_layout));
                final Dialog dialog = new Dialog(v2.getContext());
                dialog.setContentView(R.layout.wallet_layout);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                et1 = dialog.findViewById(R.id.et_money);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                             

                        money=Integer.parseInt(et1.getText().toString());
                        amount(money);
                        wallet_amount.setText("Money in your wallet is : "+money+".0");
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

        return v;

    }
    
    public void amount(int money_entered)
    {
        SharedPreferences sp = getActivity().getSharedPreferences("Wallet Amount",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int uamount = sp2.getInt(uname,0);
        money = uamount + money_entered;
        editor.putInt(uname,money);
        editor.commit();
    }
    
    public void Initialize_amount(){
        int uamount = sp2.getInt(uname,0);
        wallet_amount.setText("Money in your wallet is :"+uamount+".0");
    }
}
