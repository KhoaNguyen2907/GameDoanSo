package cybersoft.java18.backend.gamedoanso.mapper;

import cybersoft.java18.backend.gamedoanso.model.TopRank;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopRankMapper implements AbstractMapper<TopRank> {
    @Override
    public TopRank rowMapper(ResultSet resultSet) {
        try {
            return new TopRank(
                    resultSet.getString("name"),
                    resultSet.getInt("guess_times"));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
