package utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

class DateConverter implements Converter {
    @Override
    public Object convert(Class type, Object value) {
        if (type != Date.class) {
            return  null;
        }
        if (value == null || value.toString().trim().isEmpty()) {
            return null;
        }
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(value.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

public class CommonUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static Date getDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return date;
    }

    public static <T> T toBean(Map map, Class<T> tclass) {
        try {
            T bean = tclass.getConstructor().newInstance();
            ConvertUtils.register(new DateConverter(), Date.class);
            BeanUtils.populate(bean, map);
            return bean;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
