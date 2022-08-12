package cybersoft.java18.backend.gamedoanso.mapper;

import java.sql.ResultSet;

public interface AbstractMapper<T> {
    T rowMapper(ResultSet resultSet);
}
