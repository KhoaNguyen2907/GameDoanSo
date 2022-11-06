package cybersoft.java18.backend.gamedoanso.repository;

import cybersoft.java18.backend.gamedoanso.mapper.TopRankMapper;
import cybersoft.java18.backend.gamedoanso.model.GameSession;
import cybersoft.java18.backend.gamedoanso.model.TopRank;

import java.util.List;

public class TopRankRepository extends AbstractRepository<TopRank> {
    public List<TopRank> getTopRankList(int listAmount) {
        String query = "select name, count(gs.id) as guess_times from GameSession gs inner join Guess g " +
                       "on gs.id = g.game_session_id " +
                       "inner join Player p on gs.username = p.username " +
                       "where is_completed = true " +
                       "group by game_session_id, p.name order by count(gs.id) limit ?";
        return executeQuery(query, new TopRankMapper(), listAmount);
    }
}
