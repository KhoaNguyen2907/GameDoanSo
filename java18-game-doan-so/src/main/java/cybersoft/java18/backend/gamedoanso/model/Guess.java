package cybersoft.java18.backend.gamedoanso.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Guess {
    private final int guessNum;
    private final String gameSessionId;
    private final LocalDateTime timestamp;
    private String result;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");


    public Guess(int guessNum, String result, GameSession gameSession) {
        this.gameSessionId = gameSession.getId();
        this.guessNum = guessNum;
        timestamp = LocalDateTime.now();
        this.result = result;
    }

    public Guess(int guess_num, String result, LocalDateTime timestamp, String game_session_id) {
            this.guessNum = guess_num;
            this.result = result;
            this.timestamp = timestamp;
            this.gameSessionId = game_session_id;
    }

    public String getGameSessionId() {
        return this.gameSessionId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getGuessNum() {
        return this.guessNum;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
