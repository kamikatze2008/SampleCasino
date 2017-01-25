package com.example.serhii.samplecasino.entities;

import java.io.Serializable;

public class Box implements Serializable {
    private int boxNumber;
    private double bet;
    private double winAmount;

    public Box(int boxNumber) {
        this.boxNumber = boxNumber;
    }

    public int getBoxNumber() {
        return boxNumber;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public double getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(double winAmount) {
        this.winAmount = winAmount;
    }

    @Override
    public String toString() {
        return "Box{" +
                "boxNumber=" + boxNumber +
                ", bet=" + bet +
                '}';
    }
}
