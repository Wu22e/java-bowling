package bowling.domain;

public class Player {
    private final String name;

    public Player(String name) {
//        validateName(name);
        this.name = name;
    }

//    private void validateName(String name) {
//
//    }

    public String name() {
        return this.name;
    }
}
