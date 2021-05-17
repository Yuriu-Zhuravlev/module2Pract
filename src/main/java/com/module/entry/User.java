package com.module.entry;

public class User {
    String name;
    int maxTotalPoints;

    public User(String name, int maxTotalPoints) {
        this.name = name;
        this.maxTotalPoints = maxTotalPoints;
    }

    public String getName() {
        return name;
    }

    public int getMaxTotalPoints() {
        return maxTotalPoints;
    }

    public void setMaxTotalPoints(int maxTotalPoints) {
        this.maxTotalPoints = maxTotalPoints;
    }
}
