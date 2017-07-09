package service;

import java.sql.SQLException;

/**
 * Created by wsj on 2017/7/8.
 */
public interface CategoryService {
    String findCategory() throws Exception;
    String findCategoryByRedis() throws Exception;
}
