package cybersoft.java18.backend.gamedoanso.repository;

import cybersoft.java18.backend.gamedoanso.jdbc.MySQLConnection;
import cybersoft.java18.backend.gamedoanso.mapper.AbstractMapper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AbstractRepository<T> {

    public void setParameter(PreparedStatement statement, Object... parameter) throws SQLException {
        for (int i = 0; i < parameter.length; i++) {
            if (parameter[i] instanceof String){
                statement.setString(i+1, (String) parameter[i]);
            } else if (parameter[i] instanceof Integer){
                statement.setInt(i+1, (Integer) parameter[i]);
            } else if (parameter[i] instanceof Boolean){
                statement.setBoolean(i+1, (Boolean) parameter[i]);
            }
            else if (parameter[i] instanceof LocalDateTime){
                if (parameter[i] != null){
                    statement.setTimestamp(i+1, Timestamp.valueOf((LocalDateTime) parameter[i]));
                }
            }
        }
    }

    public List<T> executeQuery(String query, AbstractMapper<T> rowMapper,Object... parameter) {
        try (Connection connection = MySQLConnection.getConnection()) {
            List<T> list = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(query);
            setParameter(statement,parameter);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                list.add(rowMapper.rowMapper(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void executeUpdate(String query, Object... parameter){
        try (Connection connection = MySQLConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            setParameter(statement,parameter);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


}
