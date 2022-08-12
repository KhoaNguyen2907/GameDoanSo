package cybersoft.java18.backend.gamedoanso.mapper;

import cybersoft.java18.backend.gamedoanso.model.Guess;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuessMapper implements AbstractMapper<Guess> {
    @Override
    public Guess rowMapper(ResultSet resultSet) {
        try {
            return new Guess(
                    resultSet.getInt("guess_num"),
                    resultSet.getString("result"),
                    resultSet.getTimestamp("timestamp").toLocalDateTime(),
                    resultSet.getString("game_session_id"));

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
