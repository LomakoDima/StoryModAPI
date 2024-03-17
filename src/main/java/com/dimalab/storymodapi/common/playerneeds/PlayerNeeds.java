package com.dimalab.storymodapi.common.playerneeds;

public class PlayerNeeds {
    private int toiletLevel;
    private int thirstLevel;
    private int staminaLevel;

    public PlayerNeeds(int toiletLevel, int thirstLevel, int staminaLevel) {
        this.toiletLevel = toiletLevel;
        this.thirstLevel = thirstLevel;
        this.staminaLevel = staminaLevel;
    }

    public int getToiletLevel() {
        return toiletLevel;
    }

    public void setToiletLevel(int toiletLevel) {
        this.toiletLevel = toiletLevel;
    }

    public int getThirstLevel() {
        return thirstLevel;
    }

    public void setThirstLevel(int thirstLevel) {
        this.thirstLevel = thirstLevel;
    }

    public int getStaminaLevel() {
        return staminaLevel;
    }

    public void setStaminaLevel(int staminaLevel) {
        this.staminaLevel = staminaLevel;
    }
}
