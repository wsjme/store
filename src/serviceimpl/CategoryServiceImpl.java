package serviceimpl;

import dao.CategoryDao;
import domain.CateGory;
import factory.FactoryDemo;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;
import service.CategoryService;
import utils.JedisUtils;

import java.util.List;

/**
 * Created by wsj on 2017/7/8.
 */
public class CategoryServiceImpl implements CategoryService {
    @Override
    public String findCategory() throws Exception {
        CategoryDao cdbean= (CategoryDao) FactoryDemo.getBean("CategoryDao");
        //CategoryDaoImpl cd = new CategoryDaoImpl();
        List<CateGory> list = cdbean.findCategory();
        JSONArray json = JSONArray.fromObject(list);
        return json.toString();
    }

    @Override
    public String findCategoryByRedis() throws Exception {
        Jedis jedis = JedisUtils.getJedis();
        String value = jedis.get("dht");
        if (value==null) {

            value=findCategory();
            jedis.set("dht",value);
            System.out.println("从mysql获取的");
            return value;
        }
        System.out.println("从redis中获取的");
        jedis.close();
        return value;
    }
}
