package com.robertkiszelirk.cornergrocer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //ITEMS IN XML UI
    AppCompatButton currentDataButton;
    AppCompatImageButton placeButton;
    AppCompatImageButton webButton;
    AppCompatImageButton emailButton;
    AppCompatImageButton phoneButton;
    //INT TO SELECT CURRENT CORNER GROCER DATA
    int cData = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SET JSON FILE DATA
        HandleJson jsonHandler = new HandleJson();
        jsonHandler.setJSON(MainActivity.this);
        //DEFINE RECYCLER VIEW
        RecyclerView rcView = (RecyclerView) findViewById(R.id.rcView);
        //DEFINE ADAPTER
        ProduceAdapter produceAdapter = new ProduceAdapter(this, jsonHandler.getProducesList());
        //SET ADAPTER TO RECYCLER VIEW FILL VIEW
        rcView.setAdapter(produceAdapter);
        rcView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        //DEFINE CURRENT DATA BUTTON - SELECT CURRENT FUNCTION
        currentDataButton = (AppCompatButton) findViewById(R.id.current_data);
        currentDataButton.setOnClickListener(this);
        //DEFINE PLACE BUTTON - TO SHOW LOCATION
        placeButton = (AppCompatImageButton) findViewById(R.id.place);
        placeButton.setOnClickListener(this);
        //DEFINE THE WEB BUTTON - TO NAVIGATE TO WEBSITE
        webButton = (AppCompatImageButton) findViewById(R.id.web);
        webButton.setOnClickListener(this);
        //DEFINE THE EMAIL BUTTON - TO SEND EMAIL
        emailButton = (AppCompatImageButton) findViewById(R.id.email);
        emailButton.setOnClickListener(this);
        //DEFINE PHONE BUTTON  - TO CALL UP NUMBER
        phoneButton = (AppCompatImageButton) findViewById(R.id.phone);
        phoneButton.setOnClickListener(this);
        //HANDLE ORIENTATION CHANGE
        if(savedInstanceState != null){
            cData = savedInstanceState.getInt("cData");
        }
        //SET CURRENT DATA
        switch(cData){
            case 1:currentDataButton.setText(R.string.adress);
                break;
            case 2:currentDataButton.setText(R.string.webpage);
                break;
            case 3:currentDataButton.setText(R.string.email);
                break;
            case 4:currentDataButton.setText(R.string.phone);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        //SELECT THE CORRECT FUNCTION
        switch(v.getId()){
            //SELECT THE CORRECT METHOD TO FUNCTION
            case R.id.current_data:
                switch(cData){
                    case 1:callPlace();
                        break;
                    case 2:callWeb();
                        break;
                    case 3:callMail();
                        break;
                    case 4:callPhone();
                        break;
                    default:
                    break;
                }
                break;
            case R.id.place:
                //SET FOR PLACE FUNCTION
                currentDataButton.setText(R.string.adress);
                cData = 1;
                break;
            case R.id.web:
                //SET FOR WEBSITE FUNCTION
                currentDataButton.setText(R.string.webpage);
                cData = 2;
                break;
            case R.id.email:
                //SET FOR EMAIL FUNCTION
                currentDataButton.setText(R.string.email);
                cData = 3;
                break;
            case R.id.phone:
                //SET FOR PHONE CALL FUNCTION
                currentDataButton.setText(R.string.phone);
                cData = 4;
                break;
            default:
                break;
        }


    }
    //METHOD FOR LOCATION SHOW
    private void callPlace() {
        Uri gmmIntentUri = Uri.parse(getString(R.string.call_location)+46.9152+","+19.6702);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        mapIntent.setPackage(getString(R.string.set_location_map));
        getApplicationContext().startActivity(mapIntent);
    }
    //METHOD FOR WEBSITE VISIT
    private void callWeb() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.call_web_page)));
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }
    //METHOD TO SEND EMAIL
    private void callMail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse(getString(R.string.call_email)));
        getApplicationContext().startActivity(intent);
    }
    //METHOD FOR PHONE CALL
    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse(getString(R.string.call_phone_number)));
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        getApplicationContext().startActivity(intent);
    }

    //HANDLE ORIENTATION CHANGE
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt("cData",cData);
        super.onSaveInstanceState(outState);

    }
}

