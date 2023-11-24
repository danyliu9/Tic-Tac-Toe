package model;

public enum GameMode {
    SINGLE_PLAYER ("Single Player"),
    LOCAL_TWO_PLAYER ("Local Two Player");

    private final String modeName;

    GameMode(String modeName) {
        this.modeName = modeName;
    }

    public String getModeName() {
        return modeName;
    }
}
