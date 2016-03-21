package com.example.anel.synword;

import java.io.Serializable;

/**
 * Created by Florian on 16.12.2015.
 */
public  class Points implements Serializable{

    double pointcounter = 0;
    int round = 0;

    public void setPointcounter(double pointcounter) {
        this.pointcounter = pointcounter;
    }

    public void setRound(int round) {
        this.round += round;
    }

    public double getPointcounter (){
        return this.pointcounter;
    }

    public int getRound (){
        return this.round;
    }
}