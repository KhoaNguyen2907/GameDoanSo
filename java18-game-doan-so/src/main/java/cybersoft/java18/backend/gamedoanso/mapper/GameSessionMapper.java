package cybersoft.java18.backend.gamedoanso.mapper;

import cybersoft.java18.backend.gamedoanso.model.GameSession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class GameSessionMapper {
    public static GameSession rowMapper(ResultSet resultSet) throws RuntimeException {
        try {
            GameSession game = new GameSession(resultSet.getString("id"),
                    resultSet.getInt("target_num"),
                    resultSet.getTimestamp("start_time").toLocalDateTime(),
                    resultSet.getString("username"),
                    resultSet.getBoolean("is_completed"),
                    resultSet.getBoolean("is_active"));
            if (resultSet.getBoolean("is_completed")){
                game.setEndTime(resultSet.getTimestamp("end_time").toLocalDateTime());
            }
            return game;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
