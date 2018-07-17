package com.example.darkerload.sendimage.SendPackage;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by darkerload on 13.07.2018.
 */

public class FileSender {
    private String toIpAdress = "10.0.2.2";
    private int portNumber = 8987;


    public void  SendData(Uri uri) {
        try {
            File file = new File(uri.getPath());

            Socket socket = new Socket(toIpAdress, portNumber);
            OutputStream outputStream = socket.getOutputStream();
            byte [] fileByteArray  = new byte [(int)file.length()];
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedInputStream.read(fileByteArray,0,fileByteArray.length);

            outputStream.write(fileByteArray,0,fileByteArray.length);
            outputStream.flush();
        } catch (Exception e) {
            Log.e("Socket Exception :", e.toString() + " - Stack Trace : " + e.getStackTrace());
        }
    }


}
