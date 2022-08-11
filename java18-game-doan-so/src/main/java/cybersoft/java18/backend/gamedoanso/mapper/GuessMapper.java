package cybersoft.java18.backend.gamedoanso.mapper;

import cybersoft.java18.backend.gamedoanso.model.Guess;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuessMapper {

    public static Guess rowMapper(ResultSet resultSet) {
        try {
            Guess guess = new Guess(
                    resultSet.getInt("guess_num"),
                    resultSet.getString("result"),
                    resultSet.getTimestamp("timestamp").toLocalDateTime(),
                    resultSet.getString("game_session_id"));
            return guess;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
