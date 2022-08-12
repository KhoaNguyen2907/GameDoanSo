package cybersoft.java18.backend.gamedoanso.repository;

import cybersoft.java18.backend.gamedoanso.mapper.GameSessionMapper;
import cybersoft.java18.backend.gamedoanso.model.GameSession;

import java.util.List;

public class GameSessionRepository extends AbstractRepository<GameSession> {

    public List<GameSession> findGamesByUserName(String userName) {
        String query = "select * from GameSession where username = ?";
        return executeQuery(query, new GameSessionMapper(),userName);
    }

    public void save(GameSession gameSession) {
        if (gameSession.isCompleted()) {
            String query = "insert into GameSession(id, target_num, start_time, end_time, username, is_completed, is_active) " +
                           "values (?,?,?,?,?,?,?)";
            executeUpdate(query, gameSession.getId(),
                    gameSession.getTargetNumber(), gameSession.getStartTime(),
                    gameSession.getEndTime(), gameSession.getUserName(),
                    gameSession.isCompleted(), gameSession.isActive());
        } else {
            String query = "insert into GameSession(id, target_num, start_time, username, is_completed, is_active) " +
                           "values (?,?,?,?,?,?)";
            executeUpdate(query, gameSession.getId(),
                    gameSession.getTargetNumber(), gameSession.getStartTime(),
                    gameSession.getUserName(), gameSession.isCompleted(), gameSession.isActive());

        }
    }

    public void update(GameSession gameSession) {
        if (gameSession.isCompleted()) {
            String query = "UPDATE GameSession " +
                           "SET end_time = ?, is_completed = ?, is_active = ? " +
                           "WHERE id = ?";
            executeUpdate(query, gameSession.getEndTime(),
                    gameSession.isCompleted(), gameSession.isActive(), gameSession.getId());
        } else {
            String query = "UPDATE GameSession " +
                           "SET is_completed = ?, is_active = ? " +
                           "WHERE id = ? ";
            executeUpdate(query, gameSession.isCompleted(), gameSession.isActive(),
                    gameSession.getId());
        }
    }

}
