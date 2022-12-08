package com.example.testota;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements UpdateHelper.OnUpdateCheckListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UpdateHelper.with(this).onUpdateCheck(this).check();
    }

    @Override
    public void onUpdateCheckListener(String urlApp) {
        //Dialog
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("New Version Available").setMessage("Update to newer Version").setPositiveButton("UPDATE", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Download here using downloadUrl

                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse("https://images.unsplash.com/photo-1670477250773-a7e1c2354d25?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=654&q=80"));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.
                        Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
             //   request.setDestinationInExternalPublicDir("", "Happy.jpg");
                request.setDestinationInExternalFilesDir(getBaseContext(),"/storage/self/primary/Download","Happy.pdf");
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File",
                        Toast.LENGTH_LONG).show();
              //  Toast.makeText(MainActivity.this,""+urlApp,Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("CANCEL",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
dialogInterface.dismiss();            }
        }).create();
        alertDialog.show();
    }
}