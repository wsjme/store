package dao;

import domain.CateGory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wsj on 2017/7/8.
 */
public interface CategoryDao {
    List<CateGory> findCategory() throws SQLException;
}
