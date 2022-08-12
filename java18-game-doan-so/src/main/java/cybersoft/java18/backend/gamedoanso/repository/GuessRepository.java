package cybersoft.java18.backend.gamedoanso.repository;

import cybersoft.java18.backend.gamedoanso.mapper.GuessMapper;
import cybersoft.java18.backend.gamedoanso.model.Guess;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GuessRepository extends AbstractRepository<Guess> {

    public List<Guess> findGuessListByIdGame(String gameSessionId) {
        String query = "select * from Guess where game_session_id = ?";
        return executeQuery(query,new GuessMapper(),gameSessionId);
    }

    public void addGuess(Guess guess) {
        String query = "insert into Guess(guess_num, result, timestamp, game_session_id) values(?,?,?,?)";
        executeUpdate(query,guess.getGuessNum(),guess.getResult(),guess.getTimestamp(),guess.getGameSessionId());
    }

}
