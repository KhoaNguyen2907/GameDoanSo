package cybersoft.java18.backend.gamedoanso.repository;

import cybersoft.java18.backend.gamedoanso.jdbc.MySQLConnection;
import cybersoft.java18.backend.gamedoanso.mapper.GuessMapper;
import cybersoft.java18.backend.gamedoanso.mapper.ParamMapper;
import cybersoft.java18.backend.gamedoanso.model.Guess;
import cybersoft.java18.backend.gamedoanso.store.GameStore;
import cybersoft.java18.backend.gamedoanso.store.GameStoreHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GuessRepository {
    private final GameStore store;

    public GuessRepository() {
        store = GameStoreHolder.getStore();
    }

    public List<Guess> getGuessList(){
        List<Guess> guessList = new ArrayList<>();
        try(Connection connection = MySQLConnection.getConnection()){
            String query = "select * from Guess";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                guessList.add(GuessMapper.rowMapper(resultSet));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return guessList;
    }

    public Collection<? extends Guess> findGuessListByIdGame(String gameSessionId) {
        return getGuessList().stream()
                .filter(g -> g.getGameSessionId().equals(gameSessionId))
                .collect(Collectors.toList());
    }

    public void addGuess(Guess guess) {
        try (Connection connection = MySQLConnection.getConnection()){
            String query = "insert into Guess(guess_num, result, timestamp, game_session_id) values(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            ParamMapper.setParameter(statement,guess.getGuessNum(),guess.getResult(),guess.getTimestamp(),guess.getGameSessionId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
