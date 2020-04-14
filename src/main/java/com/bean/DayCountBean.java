package com.bean;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DayCountBean implements Writable {

    private int day;
    private int sum;
    private int cured;
    private int dead;

    public DayCountBean(){
        super();
    }

    public DayCountBean(int day, int sum, int cured, int dead){
        super();
        this.day = day;
        this.sum = sum;
        this.cured = cured;
        this.dead = dead;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(day);
        dataOutput.writeInt(sum);
        dataOutput.writeInt(cured);
        dataOutput.writeInt(dead);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.day = dataInput.readInt();
        this.sum = dataInput.readInt();
        this.cured = dataInput.readInt();
        this.dead = dataInput.readInt();
    }

    @Override
    public String toString() {
        return day + "\t" + sum + "\t" + cured + "\t" + dead;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getCured() {
        return cured;
    }

    public void setCured(int cured) {
        this.cured = cured;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }
}
