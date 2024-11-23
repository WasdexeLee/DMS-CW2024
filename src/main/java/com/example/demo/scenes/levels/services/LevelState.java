package com.example.demo.scenes.levels.services;

public class LevelState {
    
    private static LevelState instance;

    private int numberOfKills;
	private int currentNumberOfEnemies;
    private final double screenWidth;
    private final double screenHeight;

    private LevelState(double screenWidth, double screenHeight) {
        this.numberOfKills = 0;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public static LevelState getInstance(double screenWidth, double screenHeight) {
        if (instance == null) {
            instance = new LevelState(screenWidth, screenHeight);
        }

        return instance;
    }

    public int getNumberOfKills() {
        return numberOfKills;
    }

    public void setNumberOfKills(int numberOfKills) { 
        this.numberOfKills = numberOfKills;
    }

    public void incrementNumberOfKills() { 
        this.numberOfKills++;
    }

    public int getCurrentNumberOfEnemies() {
        return currentNumberOfEnemies;
    }

    public void setCurrentNumberOfEnemies(int currentNumberOfEnemies){
        this.currentNumberOfEnemies = currentNumberOfEnemies;
    }

    public double getScreenWidth() {
        return screenWidth;
    }

    public double getScreenHeight() {
        return screenHeight;
    }
}