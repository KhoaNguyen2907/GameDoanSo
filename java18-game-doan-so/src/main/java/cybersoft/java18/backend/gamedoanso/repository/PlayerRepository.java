package cybersoft.java18.backend.gamedoanso.repository;

import cybersoft.java18.backend.gamedoanso.jdbc.MySQLConnection;
import cybersoft.java18.backend.gamedoanso.mapper.PlayerMapper;
import cybersoft.java18.backend.gamedoanso.model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class PlayerRepository extends AbstractRepository<Player> {

    public Player findByUserName(String userName) {
        String query = "select * from Player where username = ?";
       List<Player> playerList = executeQuery(query,new PlayerMapper(),userName);
       if ( !playerList.isEmpty()){
           return playerList.get(0);
       }
       return null;
    }


    public void save(String userName, String password, String name) {
        if (isValid(userName, password, name)) {
            if (!isExisted(userName)) {
                String query = "insert into Player(username,password,name) " +
                               "values(?,?,?)";
                executeUpdate(query,userName,password,name);
            }
        }
    }

    private boolean isExisted(String userName) {
        if (findByUserName(userName) != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isValid(String userName, String password, String name) {
        if (userName == null || password == null || name == null
                || "".equals(userName) || "".equals(password) || "".equals(name)) {
            return false;
        }
        return true;
    }
}
