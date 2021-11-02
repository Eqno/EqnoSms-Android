package cn.edu.ujn.EqnoSms;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    void switchActivity() {
        startActivity(new Intent(MainActivity.this, SmsActivity.class));
        finish();
    }

    final int idTime = 5;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = new CountDownTimer(idTime*1000, 1000) {

            int counter = 5;
            Button skip = findViewById(R.id.skip);

            @Override
            public void onTick(long l) {
                skip.setText(("跳过 "+counter));
                counter --;
            }

            @Override
            public void onFinish() {
                switchActivity();
            }
        }.start();
    }

    public void skip(View view) {
        switchActivity();
        timer.cancel();
    }
}