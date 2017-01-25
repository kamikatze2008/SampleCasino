package com.example.serhii.samplecasino.entities;

public class Box {
    private int boxNumber;
    private double bet;

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

    @Override
    public String toString() {
        return "Box{" +
                "boxNumber=" + boxNumber +
                ", bet=" + bet +
                '}';
    }
}
