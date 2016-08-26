package com.aaronxu.wolfeatsheep;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WolfEatSheepActivity extends AppCompatActivity {

    private static final String TAG = "WolfEatSheepActivity";
    private int i = 0;
    private int j = 0;
    private Button buttonTemplate;
    private BlankView mBlankView0_0;
    private TextView mWolfNumber;
    private TextView mSheepNumber;
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
    private List<SheepPoint> mSheepPoint;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wolf_eat_sheep);

        dialog();
        mWolfPoint = new ArrayList<>();
        mSheepPoint = new ArrayList<>();
        mWolfNumber = (TextView) findViewById(R.id.wolf_number);
        mSheepNumber = (TextView) findViewById(R.id.sheep_number);

        getSupportActionBar().hide();
        //使用类集初始化BlankView
        initBlankView();
        initPoint();
        initDraw();

        buttonTemplate = (Button) findViewById(R.id.button_template);
        buttonTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move();
                isGameOver();
            }
        });
    }

    private boolean isGameOver() {
        if (mSheepPoint.size() ==0){
            builder.show();
            return true;
        }
        return false;
    }
    protected void dialog() {
        builder = new AlertDialog.Builder(this);
        builder.setMessage("游戏结束，退出吗？");
        builder.setTitle("游戏结束");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
                }
            });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
                }
            });
        builder.create();
        }
    private void move() {
        moveWolf();
        moveSheep();
    }

    private void moveWolf() {

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
                for (j=0;j<randomList.size();j++)   Log.d(TAG, "randomList: "+randomList.get(j));
                if(randomList.size()==1){
                    temp = randomList.get(0);
                }else if(randomList.size()==0){
                    continue;
                }else {
                    Log.d(TAG, "moveWolf: " + randomList.size());
                    temp = randomList.get((int) (Math.random()*randomList.size()));
                }
                randomList.clear();
                Log.d(TAG, "移动后"+i+"号"+"("+temp.x+","+temp.y+")");
                mBlankViewLists.get(mWolfPoint.get(i).x).get(mWolfPoint.get(i).y).setmWolfOrSheep(BlankView.NOTHING);
                mBlankViewLists.get(temp.x).get(temp.y).setmWolfOrSheep(BlankView.WOLF);
                mWolfPoint.get(i).x=temp.x;
                mWolfPoint.get(i).y=temp.y;

                for (j=0;j<mWolfPoint.size();j++)   Log.d(TAG, "枚举: "+mWolfPoint.get(j));
            }
        }
        mWolfNumber.setText("现在还有"+mWolfPoint.size()+"只狼");
    }

    private void moveSheep() {
        SheepPoint tempIfNew;
        SheepPoint temp;
        SheepPoint temp_up;
        SheepPoint temp_down;
        SheepPoint temp_left;
        SheepPoint temp_right;

        boolean flag = false;
        List<SheepPoint> randomList= new ArrayList<>();

        for(i=0;i<mSheepPoint.size();i++){
            tempIfNew = mSheepPoint.get(i);
            temp = mSheepPoint.get(i);
            if(mWolfPoint.contains(new Point(temp.x,temp.y))){
                Log.d(TAG, "移除了: " + mSheepPoint.get(i));
                mSheepPoint.remove(i);
                continue;
            }

            temp_left = new SheepPoint(temp.x-1,temp.y);
            temp_right = new SheepPoint(temp.x+1,temp.y);
            temp_up = new SheepPoint(temp.x,temp.y-1);
            temp_down = new SheepPoint(temp.x,temp.y+1);

            Log.d(TAG, "羊：移动前"+i+"号"+"("+temp.x+","+temp.y+")");
            if(temp.x>0&&!mWolfPoint.contains(temp_left.toPoint())&&!mSheepPoint.contains(temp_left)){
                randomList.add(temp_left);
                flag = true;
            }
            if(temp.x<7&&!mWolfPoint.contains(temp_right.toPoint())&&!mSheepPoint.contains(temp_right)){
                randomList.add(temp_right);
                flag = true;
            }
            if(temp.y>0&&!mWolfPoint.contains(temp_up.toPoint())&&!mSheepPoint.contains(temp_up)){
                randomList.add(temp_up);
                flag = true;
            }
            if(temp.y<7&&!mWolfPoint.contains(temp_down.toPoint())&&!mSheepPoint.contains(temp_down)){
                randomList.add(temp_down);
                flag = true;
            }
            if(flag) {
                for (j=0;j<randomList.size();j++)   Log.d(TAG, "羊：randomList: "+randomList.get(j));
                if(randomList.size()==1) {
                    temp = randomList.get(0);
                }else if(randomList.size()==0){
                    continue;
                }else {
                    temp = randomList.get((int) (Math.random()*randomList.size()));
                }
                randomList.clear();
                Log.d(TAG, "羊：移动后"+i+"号"+"("+temp.x+","+temp.y+")");
                if (mSheepPoint.get(i).age == 2){
                    Log.d(TAG, "羊："+i+"号羊开始生娃,并将"+mSheepPoint.get(i) + "设置成了绿色.");
                    mBlankViewLists.get(mSheepPoint.get(i).x).get(mSheepPoint.get(i).y).setmWolfOrSheep(BlankView.SHEEP);
                    mSheepPoint.add(new SheepPoint(mSheepPoint.get(i).x,mSheepPoint.get(i).y));
                }else {
                    mBlankViewLists.get(mSheepPoint.get(i).x).get(mSheepPoint.get(i).y).setmWolfOrSheep(BlankView.NOTHING);
                }
                mBlankViewLists.get(temp.x).get(temp.y).setmWolfOrSheep(BlankView.SHEEP);
                mSheepPoint.get(i).x=temp.x;
                mSheepPoint.get(i).y=temp.y;

                for (j=0;j<mSheepPoint.size();j++)   Log.d(TAG, "羊：枚举: "+mSheepPoint.get(j));
            }

            mSheepPoint.get(i).growUp();
        }
        mSheepNumber.setText("现在还有"+mSheepPoint.size()+"只羊");

    }

    private void initDraw() {
        Point temp;
        SheepPoint sheepTemp;
        for (i=0;i<mWolfPoint.size();i++){
            temp = mWolfPoint.get(i);
            mBlankViewLists.get(temp.x).get(temp.y).setmWolfOrSheep(BlankView.WOLF);
        }
        for (i=0;i<mSheepPoint.size();i++){
            sheepTemp = mSheepPoint.get(i);
            mBlankViewLists.get(sheepTemp.x).get(sheepTemp.y).setmWolfOrSheep(BlankView.SHEEP);
        }
    }

    private void initPoint() {
        mWolfPoint.add(new Point(0,0));
        mWolfPoint.add(new Point(0,1));
        mWolfPoint.add(new Point(1,0));
        mWolfPoint.add(new Point(1,1));
        mWolfPoint.add(new Point(2,0));
        mWolfPoint.add(new Point(0,2));
        mWolfPoint.add(new Point(1,2));
        mWolfPoint.add(new Point(2,1));
        mWolfPoint.add(new Point(2,2));
        mWolfPoint.add(new Point(0,3));
        mWolfPoint.add(new Point(1,3));
        mWolfPoint.add(new Point(2,3));
        mWolfPoint.add(new Point(3,3));
        mWolfPoint.add(new Point(3,2));
        mWolfPoint.add(new Point(3,1));
        mWolfPoint.add(new Point(3,0));
//        mWolfPoint.add(new Point(0,0));
//        mWolfPoint.add(new Point(1,0));
//        mWolfPoint.add(new Point(2,0));
//        mWolfPoint.add(new Point(3,0));
//        mWolfPoint.add(new Point(4,0));
//        mWolfPoint.add(new Point(5,0));
//        mWolfPoint.add(new Point(6,0));
//        mWolfPoint.add(new Point(7,0));
//
//        mWolfPoint.add(new Point(7,1));
//        mWolfPoint.add(new Point(6,1));
//        mWolfPoint.add(new Point(5,1));
//        mWolfPoint.add(new Point(4,1));
//        mWolfPoint.add(new Point(3,1));
//        mWolfPoint.add(new Point(2,1));
//        mWolfPoint.add(new Point(1,1));
//        mWolfPoint.add(new Point(0,1));
//
//        mWolfPoint.add(new Point(7,2));
//        mWolfPoint.add(new Point(6,2));
//        mWolfPoint.add(new Point(5,2));
//        mWolfPoint.add(new Point(4,2));
//        mWolfPoint.add(new Point(3,2));
//        mWolfPoint.add(new Point(2,2));
//        mWolfPoint.add(new Point(1,2));
//        mWolfPoint.add(new Point(0,2));


        mSheepPoint.add(new SheepPoint(7,7));
//        mSheepPoint.add(new SheepPoint(6,7));
//        mSheepPoint.add(new SheepPoint(5,7));
//        mSheepPoint.add(new SheepPoint(4,7));
//        mSheepPoint.add(new SheepPoint(3,7));


//        Log.d(TAG, "initPoint: "+mSheepPoint.contains(new SheepPoint(5,4)));

        mWolfNumber.setText("现在还有"+mWolfPoint.size()+"只狼");
        mSheepNumber.setText("现在还有"+mSheepPoint.size()+"只羊");
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
