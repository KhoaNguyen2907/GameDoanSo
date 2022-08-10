package cybersoft.java18.backend.gamedoanso.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameSession {
    private static int soId = 1;
    private static Random random = null;
    private final String id;
    private List<Guess> guessList;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final int targetNumber;
    private boolean isCompleted;
    private boolean isActive;
    private String userName;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getId() {
        return id;
    }

    public GameSession(String userName) {
        this.isCompleted = false;
        this.userName = userName;
        this.id = "Game " + String.format("%05d", soId++);
        this.targetNumber = getRandomInt();
        this.guessList= new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    private int getRandomInt() {
        if (random == null) {
            random = new Random();
        }
        return random.nextInt(4 - 1) + 1;
    }

    public List<Guess> getGuessList() {
        return guessList;
    }

    public void setGuessList(List<Guess> guessList) {
        this.guessList = guessList;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }


}
