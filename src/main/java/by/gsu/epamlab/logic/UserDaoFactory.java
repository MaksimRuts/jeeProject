package by.gsu.epamlab.logic;

import java.util.HashMap;
import java.util.Map;

public class UserDaoFactory {
    private static final Map<Class<?>, Object> map = new HashMap<Class<?>, Object>();

    static {
        map.put(UserDaoMemory.class, new UserDaoMemory());
        map.put(UserDaoDB.class, new UserDaoDB());
    }

    public static <T> T getUserDao(Class<T> type) {
        if (map.containsKey(type)) {
            return type.cast(map.get(type));
        } else {
            throw new IllegalArgumentException(type.getName());
        }
    }
}
