package model;

public enum GameMode {
    SINGLE_PLAYER ("Single Player"),
    TWO_PLAYER("Two Player");

    private final String modeName;

    GameMode(String modeName) {
        this.modeName = modeName;
    }

    public String getModeName() {
        return modeName;
    }
}
