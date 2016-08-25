package com.aaronxu.allofthem.FloatSphere;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aaronxu.allofthem.R;

public class FloatSphereActivity extends AppCompatActivity {

    private static final String TAG = "FloatSphereActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_sphere);
    }
    public void startService(View view){
        Log.d(TAG,"android的版本是"+Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d(TAG, Settings.canDrawOverlays(this) + "");
            if (!Settings.canDrawOverlays(this)) {
                dialog();
                //return;
            }else {
                Intent intent = new Intent(this, MyFloatService.class);
                startService(intent);
            }
        }else {
            Intent intent = new Intent(this, MyFloatService.class);
            startService(intent);
        }
    }
    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("允许在其他应用的上层显示方能使用此应用，去打开吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(intent);
               dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
