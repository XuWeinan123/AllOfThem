package com.aaronxu.allofthem.WolfEatSheep;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aaronxu.allofthem.R;

import java.util.ArrayList;
import java.util.List;

public class WolfEatSheepActivity extends AppCompatActivity {

    private static final String TAG = "WolfEatSheepActivity";
    private int i = 0;
    private int j = 0;
    Button buttonTemplate;
    BlankView mBlankView0_0;
    private List<List<BlankView>> mBlankViewLists;
    private List<BlankView> mBlankViewList01;
    private List<BlankView> mBlankViewList02;
    private List<BlankView> mBlankViewList03;
    private List<BlankView> mBlankViewList04;
    private List<BlankView> mBlankViewList05;
    private List<BlankView> mBlankViewList06;
    private List<BlankView> mBlankViewList07;
    private List<BlankView> mBlankViewList08;

    private List<Point> mWolfPoint;
    private List<Point> mSheepPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wolf_eat_sheep);

        mWolfPoint = new ArrayList<>();
        mSheepPoint = new ArrayList<>();
        //使用类集初始化BlankView
        initBlankView();
        initPoint();
        initDraw();

        buttonTemplate = (Button) findViewById(R.id.button_template);
        buttonTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move();
            }
        });
    }

    private void move() {
        Point temp;
        Point temp_up;
        Point temp_down;
        Point temp_left;
        Point temp_right;
        boolean flag = false;
        List<Point> randomList= new ArrayList<>();

        for(i=0;i<mWolfPoint.size();i++){
            temp = mWolfPoint.get(i);
            temp_left = new Point(temp.x-1,temp.y);
            temp_right = new Point(temp.x+1,temp.y);
            temp_up = new Point(temp.x,temp.y-1);
            temp_down = new Point(temp.x,temp.y+1);
            Log.d(TAG, "移动前"+i+"号"+"("+temp.x+","+temp.y+")");
            if(temp.x>0&&!mWolfPoint.contains(temp_left)){
                randomList.add(temp_left);
                flag = true;
            }
            if(temp.x<7&&!mWolfPoint.contains(temp_right)){
                randomList.add(temp_right);
                flag = true;
            }
            if(temp.y>0&&!mWolfPoint.contains(temp_up)){
                randomList.add(temp_up);
                flag = true;
            }
            if(temp.y<7&&!mWolfPoint.contains(temp_down)){
                randomList.add(temp_down);
                flag = true;
            }
            if(flag) {
                if(randomList.size()==1){
                    temp = randomList.get(0);
                }else {
                    temp = randomList.get((int) (Math.random()*randomList.size()));
                }
                Log.d(TAG, "移动后"+i+"号"+"("+temp.x+","+temp.y+")");
                mBlankViewLists.get(mWolfPoint.get(i).x).get(mWolfPoint.get(i).y).setmWolfOrSheep(BlankView.NOTHING);
                mBlankViewLists.get(temp.x).get(temp.y).setmWolfOrSheep(BlankView.WOLF);
                mWolfPoint.remove(i);
                mWolfPoint.add(temp);
            }
        }
    }

    private void initDraw() {
        Point temp;
        for (i=0;i<mWolfPoint.size();i++){
            temp = mWolfPoint.get(i);
            mBlankViewLists.get(temp.x).get(temp.y).setmWolfOrSheep(BlankView.WOLF);
        }
        for (i=0;i<mSheepPoint.size();i++){
            temp = mSheepPoint.get(i);
            mBlankViewLists.get(temp.x).get(temp.y).setmWolfOrSheep(BlankView.SHEEP);
        }
    }

    private void initPoint() {
        mWolfPoint.add(new Point(1,0));
        mWolfPoint.add(new Point(2,1));
        mWolfPoint.add(new Point(2,2));
        mSheepPoint.add(new Point(5,4));
        mSheepPoint.add(new Point(6,7));
        mSheepPoint.add(new Point(4,3));
//        Log.d(TAG, "initPoint: "+mWolfPoint.contains(new Point(1,0)));
    }

    protected void initBlankView() {
        mBlankViewLists = new ArrayList<>();
        mBlankViewList01 = new ArrayList<>();
        mBlankViewList02 = new ArrayList<>();
        mBlankViewList03 = new ArrayList<>();
        mBlankViewList04 = new ArrayList<>();
        mBlankViewList05 = new ArrayList<>();
        mBlankViewList06 = new ArrayList<>();
        mBlankViewList07 = new ArrayList<>();
        mBlankViewList08 = new ArrayList<>();

        for(i = 0 ;i<8;i++) {
            mBlankViewList01.add(new BlankView(this));
            mBlankViewList02.add(new BlankView(this));
            mBlankViewList03.add(new BlankView(this));
            mBlankViewList04.add(new BlankView(this));
            mBlankViewList05.add(new BlankView(this));
            mBlankViewList06.add(new BlankView(this));
            mBlankViewList07.add(new BlankView(this));
            mBlankViewList08.add(new BlankView(this));
        }

        mBlankViewLists.add(mBlankViewList01);
        mBlankViewLists.add(mBlankViewList02);
        mBlankViewLists.add(mBlankViewList03);
        mBlankViewLists.add(mBlankViewList04);
        mBlankViewLists.add(mBlankViewList05);
        mBlankViewLists.add(mBlankViewList06);
        mBlankViewLists.add(mBlankViewList07);
        mBlankViewLists.add(mBlankViewList08);

        Log.d(TAG, "onCreate: "+ mBlankViewList01.size());

        LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.mainLayout);

        LinearLayout line01 = new LinearLayout(this);
        line01.setOrientation(LinearLayout.HORIZONTAL);
        line01.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        for (i = 0;i<8;i++){
            line01.addView(mBlankViewLists.get(0).get(i));
        }
        LinearLayout line02 = new LinearLayout(this);
        line02.setOrientation(LinearLayout.HORIZONTAL);
        line02.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        for (i = 0;i<8;i++){
            line02.addView(mBlankViewLists.get(1).get(i));
        }
        LinearLayout line03 = new LinearLayout(this);
        line03.setOrientation(LinearLayout.HORIZONTAL);
        line03.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        for (i = 0;i<8;i++){
            line03.addView(mBlankViewLists.get(2).get(i));
        }
        LinearLayout line04 = new LinearLayout(this);
        line04.setOrientation(LinearLayout.HORIZONTAL);
        line04.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        for (i = 0;i<8;i++){
            line04.addView(mBlankViewLists.get(3).get(i));
        }
        LinearLayout line05 = new LinearLayout(this);
        line05.setOrientation(LinearLayout.HORIZONTAL);
        line05.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        for (i = 0;i<8;i++){
            line05.addView(mBlankViewLists.get(4).get(i));
        }
        LinearLayout line06 = new LinearLayout(this);
        line06.setOrientation(LinearLayout.HORIZONTAL);
        line06.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        for (i = 0;i<8;i++){
            line06.addView(mBlankViewLists.get(5).get(i));
        }
        LinearLayout line07 = new LinearLayout(this);
        line07.setOrientation(LinearLayout.HORIZONTAL);
        line07.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        for (i = 0;i<8;i++){
            line07.addView(mBlankViewLists.get(6).get(i));
        }
        LinearLayout line08 = new LinearLayout(this);
        line08.setOrientation(LinearLayout.HORIZONTAL);
        line08.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        for (i = 0;i<8;i++){
            line08.addView(mBlankViewLists.get(7).get(i));
        }

        mainLinearLayout.addView(line01);
        mainLinearLayout.addView(line02);
        mainLinearLayout.addView(line03);
        mainLinearLayout.addView(line04);
        mainLinearLayout.addView(line05);
        mainLinearLayout.addView(line06);
        mainLinearLayout.addView(line07);
        mainLinearLayout.addView(line08);

    }
}
