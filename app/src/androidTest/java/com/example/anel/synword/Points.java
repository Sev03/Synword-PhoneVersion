package com.example.anel.synword;

import java.io.Serializable;

/**
 * Created by Florian on 16.12.2015.
 */
public  class Points implements Serializable{

    int pointcounter = 0;
    int round = 0;

    public void setPointcounter(int pointcounter) {
        this.pointcounter = pointcounter;
    }

    public void setRound(int round) {
        this.round += round;
    }

    public int getPointcounter (){
        return this.pointcounter;
    }

    public int getRound (){
        return this.round;
    }
}