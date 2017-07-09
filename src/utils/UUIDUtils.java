package utils;

import java.util.UUID;

/**
 * Created by wsj on 2017/7/6.
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
