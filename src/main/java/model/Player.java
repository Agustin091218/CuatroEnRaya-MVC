package model;

public class Player {

    private String name;
    private final int id;

    public Player(String name, int id) {
        validateName(name);
        this.name = name;
        this.id = id;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(GameConstants.ERROR_PLAYER_NAME_EMPTY);
        }
        if (name.length() > GameConstants.MAX_PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException(GameConstants.ERROR_PLAYER_NAME_TOO_LONG);
        }
        if (name.matches(".*\\d.*")) {
            throw new IllegalArgumentException(GameConstants.ERROR_PLAYER_NAME_HAS_NUMBERS);
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }
}
