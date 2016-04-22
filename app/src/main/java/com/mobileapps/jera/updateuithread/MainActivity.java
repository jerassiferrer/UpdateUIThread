package com.mobileapps.jera.updateuithread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    private Handler handler = new Handler();
    private Handler uiHandler = new UIHandler();
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            textView = (TextView) findViewById(R.id.textview);
            textView.setText("hello Activity");

        thread = new Thread() {
            public void run() {
                try {
                    // simulate long running operation
                    
                    Thread.sleep(5000);

                    // create message which will be send to handler
                    Message msg = Message.obtain(uiHandler);
                    msg.obj = "UI THREAD";
                    uiHandler.sendMessage(msg);

                } catch (InterruptedException e) {
                    Log.e("TAG", "run in thread", e);
                }
            }
        };
        thread.start();
        }


    // HANDLER FOR UI MESSAGE
    class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            // a message is received; update UI text view
            textView.setText(msg.obj.toString());
            super.handleMessage(msg);
        }
    }



    }
