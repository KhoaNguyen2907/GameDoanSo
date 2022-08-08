package cybersoft.java18.backend.gamedoanso.model;

import java.util.List;

public class Guess {
    private final int guessNum;
    private String result;
    private final String gameSessionId;

    public String getGameSessionId(){
        return this.gameSessionId;
    }

    public Guess(int guessNum, String gameSessionId) {
        this.gameSessionId = gameSessionId;
        this.guessNum = guessNum;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getguessNum() {
        return this.guessNum;
    }

}
