package cybersoft.java18.backend.gamedoanso.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.time.format.FormatStyle;
import java.util.List;

public class Guess {
    private final int guessNum;
    private String result;
    private final String code;
    private LocalDateTime timestamp;
    private final String gameSessionId;


    public String getGameSessionId(){
        return this.gameSessionId;
    }

    public Guess(int guessNum,int resultId, GameSession gameSession ) {
        this.gameSessionId = gameSession.getId();
        this.guessNum = guessNum;
        timestamp = LocalDateTime.now();
        if(resultId == 0){
            this.result = "Bạn đã đoán đúng. Kết quả: " + gameSession.getTargetNumber();
            this.code = "success";
        } else if (resultId == 1){
            this.result = "Số bạn đoán lớn hơn kết quả";
            this.code = "danger";
        } else {
            this.result = "Số bạn đoán bé hơn kết quả";
            this.code = "warning";
        }
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

    public String getCode() {
        return code;
    }
}
