package com.aaronxu.allofthem.FloatSphere;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aaronxu.allofthem.R;

/**
 * Created by woshi on 2016-08-23.
 */
public class DialogView extends LinearLayout {
    Button button;
    public DialogView(Context context){
        super(context);
        final View root = View.inflate(getContext(), R.layout.dialog_mydialog,null);
        button = (Button) root.findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"点击了按钮",Toast.LENGTH_SHORT).show();
            }
        });
        addView(root);
    }
}
