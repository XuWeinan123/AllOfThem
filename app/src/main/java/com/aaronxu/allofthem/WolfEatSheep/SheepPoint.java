package com.aaronxu.allofthem.WolfEatSheep;

import android.graphics.Point;

/**
 * Created by woshi on 2016-08-26.
 */
public class SheepPoint  {
    public int x;
    public int y;
    public int age;
    public SheepPoint(){
        this(-1,-1);
    }
    public SheepPoint(int x,int y){
        this(x,y,0);
    }
    public SheepPoint(int x,int y,int age){
        this.x = x;
        this.y = y;
        this.age = age;
    }
    public SheepPoint(SheepPoint sheepPoint){
        this.x = sheepPoint.x;
        this.y = sheepPoint.y;
        this.age = sheepPoint.age;
    }
    public void growUp(){
        this.age = (this.age+1)%3;
    }

    @Override
    public String toString() {
        return "SheepPoint("+this.x+","+this.y+")";
    }
    public Point toPoint(){
        return new Point(this.x,this.y);
    }

    @Override
    public boolean equals(Object obj) {
        SheepPoint temp = (SheepPoint) obj;
        if (temp.x == this.x && temp.y == this.y) return true;
        else return false;
    }
}
