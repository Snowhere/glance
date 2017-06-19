package util;

import annotation.DI;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/6/11.
 */
public class BeanFactory {
    private ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
    private static BeanFactory instance = new BeanFactory();

    public static BeanFactory getInstance() {
        return instance;
    }

    public  <T> T getBean(Class<T> claz) {
        Object bean = map.get(claz.getName());
        try {
            if (bean == null) {
                bean = claz.newInstance();
                map.put(claz.getName(), bean);
                inject(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return (T)bean;
    }

    private <T> void inject(T bean) throws IllegalAccessException {
        Class claz = bean.getClass();
        for (Field field : claz.getDeclaredFields()) {
            if (field.getAnnotation(DI.class) != null) {
                Object o = getBean(field.getType());
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                field.set(bean,o);
            }
        }
    }
}
