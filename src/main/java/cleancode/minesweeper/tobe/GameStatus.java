package cleancode.minesweeper.tobe;

public enum GameStatus {
    IN_PROGRESS("진행 중"),
    WON("승리"),
    LOST("패배"),
    ;

    private final String description;

    GameStatus(String description) {
        this.description = description;
    }
}
