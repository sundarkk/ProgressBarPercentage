package com.sundarchaupal.progressbarpercentage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnStartProgress;

    private ProgressDialog _progressDialog;
    private int _progress = 0;
    private Handler _progressHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnStartProgress = (Button) findViewById(R.id.button1);
        btnStartProgress.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                showDialog(1);

                _progress = 0;
                _progressDialog.setProgress(0);
                _progressHandler.sendEmptyMessage(0);
            }
        });

        _progressHandler = new Handler()
        {

            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);
                if (_progress >= 100)
                {
                    _progressDialog.dismiss();
                } else
                {
                    _progress++;
                    _progressDialog.incrementProgressBy(1);
                    _progressHandler.sendEmptyMessageDelayed(0, 100);
                }
            }
        };
    }

    @Override
    protected Dialog onCreateDialog(int i)
    {
        _progressDialog = new ProgressDialog(this);
        _progressDialog.setIcon(R.drawable.ic_launcher_foreground);
        _progressDialog.setTitle("Downloading files...");
        _progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        _progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Hide", new
                DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog,
                                        int whichButton)
                    {
                        Toast.makeText(getBaseContext(),
                                "Hide clicked!", Toast.LENGTH_SHORT).show();
                    }
                });
        _progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new
                DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog,
                                        int whichButton)
                    {
                        Toast.makeText(getBaseContext(),
                                "Cancel clicked!", Toast.LENGTH_SHORT).show();
                    }
                });
        return _progressDialog;
    }
    
    }

