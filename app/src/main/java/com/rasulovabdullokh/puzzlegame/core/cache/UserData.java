package com.rasulovabdullokh.puzzlegame.core.cache;

public class UserData {
    private String name;
    private int step;
    private int time;

    public UserData(String name, int step, int time) {
        this.name = name;
        this.step = step;
        this.time = time;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", step=" + step +
                ", time=" + time +
                '}';
    }
}
