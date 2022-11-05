package cybersoft.java18.backend.gamedoanso.mapper;

import cybersoft.java18.backend.gamedoanso.model.Guess;
import cybersoft.java18.backend.gamedoanso.model.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerMapper implements AbstractMapper<Player> {

    @Override
    public Player rowMapper(ResultSet resultSet) {
        try {
            return new Player(
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("name"));

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
