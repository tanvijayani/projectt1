package com.example.projectt1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class about extends Fragment {

 TextView txtPhone,txtEmail;
    public about() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater,   @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =LayoutInflater.from(getActivity()).inflate(R.layout.fragment_about,container,false);
        View txtPhone =view.findViewById(R.id.txtphone);
        View txtEmail=view.findViewById(R.id.txtemail);

        txtPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent=new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:+919624541961"));
                startActivity(callIntent);
            }
        });

        txtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gmailIntent=new Intent(Intent.ACTION_SENDTO);
                gmailIntent.setData(Uri.parse("mailto:jayanitanvi@gmail.com"));
                startActivity(gmailIntent);
            }
        });
        return view;

    }
}
