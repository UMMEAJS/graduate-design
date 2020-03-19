package com.oncb.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

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

    public static int getCurrPage(HttpServletRequest request) throws ServletException, IOException {
        String currPage = request.getParameter("currPage");
        if (currPage == null || currPage.trim().isEmpty()) {
            return 1;
        }
        return Integer.parseInt(currPage);
    }

    public static String getUrl(HttpServletRequest request) {
        String contextPath =  request.getContextPath();
        String servletPath = request.getServletPath();
        String queryString = request.getQueryString();

        if (queryString.contains("&currPage=")) {
            int index = queryString.indexOf("&currPage=");
            queryString = queryString.substring(0, index);
        }

        return contextPath + servletPath + "?" + queryString;
    }

    public static String getReferer(HttpServletRequest request) {
        if ("1".equals(request.getParameter("adminPage"))) {
            return "admin";
        } else {
            return "home";
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
