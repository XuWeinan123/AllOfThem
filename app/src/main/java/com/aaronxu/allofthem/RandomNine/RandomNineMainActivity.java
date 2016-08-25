package com.aaronxu.allofthem.RandomNine;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aaronxu.allofthem.R;
import com.aaronxu.allofthem.RandomNine.view.CustomTitleView;

public class RandomNineMainActivity extends AppCompatActivity {
    private Button alert;
    //    MyCameraDialog dialogLoading;
    CustomTitleView randomText1;
    CustomTitleView randomText2;
    CustomTitleView randomText3;
    CustomTitleView randomText4;
    CustomTitleView randomText5;
    CustomTitleView randomText6;
    CustomTitleView randomText7;
    CustomTitleView randomText8;
    CustomTitleView randomText9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.randomnine_activity_main);
        alert = (Button) findViewById(R.id.alert);
        randomText1 = (CustomTitleView) findViewById(R.id.randomText1);
        randomText2 = (CustomTitleView) findViewById(R.id.randomText2);
        randomText3 = (CustomTitleView) findViewById(R.id.randomText3);
        randomText4 = (CustomTitleView) findViewById(R.id.randomText4);
        randomText5 = (CustomTitleView) findViewById(R.id.randomText5);
        randomText6 = (CustomTitleView) findViewById(R.id.randomText6);
        randomText7 = (CustomTitleView) findViewById(R.id.randomText7);
        randomText8 = (CustomTitleView) findViewById(R.id.randomText8);
        randomText9 = (CustomTitleView) findViewById(R.id.randomText9);
//        dialogLoading = new MyCameraDialog(this);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialogLoading.show();
                randomText1.changeNumber();
                randomText2.changeNumber();
                randomText3.changeNumber();
                randomText4.changeNumber();
                randomText5.changeNumber();
                randomText6.changeNumber();
                randomText7.changeNumber();
                randomText8.changeNumber();
                randomText9.changeNumber();
            }
        });
    }
}
