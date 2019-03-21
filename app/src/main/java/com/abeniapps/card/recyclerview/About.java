package com.abeniapps.card.recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class About extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar =  findViewById(R.id.my_toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void openOtherApps(android.view.View v){
        Intent otherApps=new Intent(Intent.ACTION_VIEW);
        otherApps.setData(Uri.parse("https://play.google.com/store?hl=en"));
        startActivity(otherApps);
    }

    public void openRateUs(View v){
        Intent rateUs=new Intent(Intent.ACTION_VIEW);
        rateUs.setData(Uri.parse("https://play.google.com/store?hl=en"));
        startActivity(rateUs);
    }

}