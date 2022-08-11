package cybersoft.java18.backend.gamedoanso.repository;

import cybersoft.java18.backend.gamedoanso.jdbc.MySQLConnection;
import cybersoft.java18.backend.gamedoanso.mapper.GameSessionMapper;
import cybersoft.java18.backend.gamedoanso.mapper.ParamMapper;
import cybersoft.java18.backend.gamedoanso.model.GameSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GameSessionRepository {

    public List<GameSession> findGamesByUserName(String userName) {
        List<GameSession> gameList = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "select * from GameSession where username = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                gameList.add(GameSessionMapper.rowMapper(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return gameList;
    }

    public void save(GameSession gameSession) {
        try (Connection connection = MySQLConnection.getConnection()) {

            if (gameSession.isCompleted()) {
                String query = "insert into GameSession(id, target_num, start_time, end_time, username, is_completed, is_active) " +
                        "values (?,?,?,?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(query);
                ParamMapper.setParameter(statement, gameSession.getId(),
                        gameSession.getTargetNumber(), gameSession.getStartTime(),
                        gameSession.getEndTime(), gameSession.getUserName(),
                        gameSession.isCompleted(), gameSession.isActive());
                statement.executeUpdate();
            } else {
                String query = "insert into GameSession(id, target_num, start_time, username, is_completed, is_active) " +
                        "values (?,?,?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(query);
                ParamMapper.setParameter(statement, gameSession.getId(),
                        gameSession.getTargetNumber(), gameSession.getStartTime(),
                        gameSession.getUserName(), gameSession.isCompleted(), gameSession.isActive());
                statement.executeUpdate();
            }


        } catch (Exception e) {
            throw new RuntimeException(String.format("Error when save game session: %s", e.getMessage()));
        }
    }

    public void update(GameSession currentGame) {
        try (Connection connection = MySQLConnection.getConnection()) {
        // WWorking here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            String query = "modifies GameSession(id, target_num, start_time, end_time, username, is_completed, is_active) " +
                    "values (?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            ParamMapper.setParameter(statement, gameSession.getId(),
                    gameSession.getTargetNumber(), gameSession.getStartTime(),
                    gameSession.getEndTime(), gameSession.getUserName(),
                    gameSession.isCompleted(), gameSession.isActive());
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(String.format("Error when save game session: %s", e.getMessage()));
        }
    }

}
