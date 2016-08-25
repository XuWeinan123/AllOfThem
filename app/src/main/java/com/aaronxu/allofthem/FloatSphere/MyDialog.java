package com.aaronxu.allofthem.FloatSphere;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aaronxu.allofthem.R;

/**
 * Created by woshi on 2016-08-23.
 */
public class MyDialog extends Dialog {
    Button button;
    private static int mTheme = R.style.CustomDialog;
    public MyDialog(Context context) {
        this(context,mTheme);
    }

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_mydialog);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"点击了按钮",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
