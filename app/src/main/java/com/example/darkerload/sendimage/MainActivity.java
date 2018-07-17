package com.example.darkerload.sendimage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.darkerload.sendimage.SendPackage.FileSender;

public class MainActivity extends AppCompatActivity {

    private Button btnPickImage;
    private ImageView imgPick;
    private  Button btnSend;
    private Uri selectedimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPickImage = (Button)findViewById(R.id.btnPickImage);
        imgPick = (ImageView)findViewById(R.id.imgPick);
        btnSend = (Button)findViewById(R.id.btnSend);

        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Choose Picture"), 21);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    public void run() {
                        // a potentially  time consuming task
                        FileSender fileSender =  new FileSender();
                        fileSender.SendData(selectedimg);
                    }
                }).start();


            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode==RESULT_CANCELED)
        {
            // action cancelled
        }
        if(resultCode==RESULT_OK)
        {
            if (requestCode == 21){
                selectedimg = data.getData();
                imgPick.setImageURI(selectedimg);
            }

        }
    }


}
