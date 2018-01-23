package com.example.sapir.shapeit;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * this department is the department that activates the sign-in screen in the app.
 * this class displays an entry screen that loads progress bar and after it finishes loading it goes to the Login screen.
 */
public class splash_screen extends AppCompatActivity {
    /**
     * attributes
     */
    ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    /**
     * this function activates the splash screen.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        showProgressBar();
    }

    /**
     * this function activates the progress bar and passes to the Login screen when the progress bar is finished loading.
     */
    public void showProgressBar() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    progressStatus += 20;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            if (progressStatus == progressBar.getMax()) {
                                finish();
                                Intent intent = new Intent(splash_screen.this, MainActivity.class);
                                startActivity(intent);

                            }
                        }
                    });
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.toString();
                    }
                }
            }
        }).start();
    }
}

