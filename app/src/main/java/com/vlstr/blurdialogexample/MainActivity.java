package com.vlstr.blurdialogexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vlstr.blurdialog.BlurDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BlurDialog blurDialog = (BlurDialog) findViewById(R.id.blurView);

        Button button = (Button) findViewById(R.id.btn_show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blurDialog.create(getWindow().getDecorView(), 20);
                blurDialog.show();
            }
        });
    }
}
