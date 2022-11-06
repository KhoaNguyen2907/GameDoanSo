package cybersoft.java18.backend.gamedoanso.model;

import cybersoft.java18.backend.gamedoanso.repository.GameSessionRepository;
import cybersoft.java18.backend.gamedoanso.service.GameService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameSession {
    // Keep soId from resetting when rerun server
    private static GameService gameService = GameService.getINSTANCE();
    private static int soId = gameService.getGameList().size()+1;

    // Normal properties
    private static Random random = null;
    private final String id;
    private final int targetNumber;
    private List<Guess> guessList;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isCompleted;
    private boolean isActive;
    private String userName;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

    public GameSession(String id, int targetNumber, LocalDateTime startTime, String userName, boolean isCompleted, boolean isActive) {
        this.id = id;
        this.guessList = new ArrayList<>();
        this.startTime = startTime;
        this.endTime = endTime;
        this.targetNumber = targetNumber;
        this.isCompleted = isCompleted;
        this.isActive = isActive;
        this.userName = userName;
    }

    public GameSession(String userName) {
        this.isCompleted = false;
        this.userName = userName;
        this.startTime = LocalDateTime.now();
        this.id = "Game " + String.format("%05d", soId++);
        this.targetNumber = getRandomInt();
        this.guessList = new ArrayList<>();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    private int getRandomInt() {
        if (random == null) {
            random = new Random();
        }
        return random.nextInt(10 - 1) + 1;
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

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
