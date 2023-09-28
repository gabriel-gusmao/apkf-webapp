package com.kungfu.apkfwebapp.domain;

public enum Phase {
    Beginner,
    First,
    Second,
    Third,
    Fourth,
    Fifth_level_one,
    Fifth_level_two,
    Fifth_level_three,
    Sixth,
    Seventh,
    Eighth_level_one,
    Eighth_level_two,
    Eighth_level_three,
    Eighth_level_four,
    Eighth_level_five,
    Eighth_level_six,
    Eighth_level_seven,
    Eighth_level_eight,
    Eighth_level_nine,
    Master;

    private Phase prevPhase = null;
    private Phase nextPhase = null;

    static {
        for (int i = 1; i<= values().length; i++) {
            Phase current = values()[i % values().length];
            current.prevPhase = values()[i - 1];
            current.nextPhase = values()[(i + 1) % values().length];
        }
    }

    public Phase getPrevPhase() {
        return prevPhase;
    }

    public Phase getNextPhase() {
        return nextPhase;
    }
}
