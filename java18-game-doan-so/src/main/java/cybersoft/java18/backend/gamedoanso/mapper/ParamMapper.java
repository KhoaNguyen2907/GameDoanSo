package cybersoft.java18.backend.gamedoanso.mapper;

import java.sql.*;
import java.time.LocalDateTime;

public class ParamMapper {
    public static void setParameter(PreparedStatement statement, Object... parameter) throws SQLException {
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
}
