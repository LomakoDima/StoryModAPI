package com.dimalab.storymodapi.common.playerneeds;

public class PlayerNeedsBuilder {
    private int toiletLevel;
    private int thirstLevel;
    private int staminaLevel;

    public PlayerNeedsBuilder toiletLevel(int toiletLevel) {
        this.toiletLevel = toiletLevel;
        return this;
    }

    public PlayerNeedsBuilder thirstLevel(int thirstLevel) {
        this.thirstLevel = thirstLevel;
        return this;
    }

    public PlayerNeedsBuilder staminaLevel(int staminaLevel) {
        this.staminaLevel = staminaLevel;
        return this;
    }

    public PlayerNeeds build() {
        return new PlayerNeeds(toiletLevel, thirstLevel, staminaLevel);
    }
}
