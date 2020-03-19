package com.oncb.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private String getTableName(String str) {
        String ret = "";

        if (str.contains("Review")) {
            ret = "review";
        }

        if (str.contains("Textbook")) {
            ret = "textbook";
        }

        if (str.contains("User")) {
            ret = "user";
        }

        return ret;
    }

    public void logAddStatus(JoinPoint joinPoint) throws Throwable {
        String typeSimpleName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String tableName = getTableName(typeSimpleName);
        logger.info("Add a record into " + tableName + "!");
    }

    public void logDeleteStatus(JoinPoint joinPoint) throws Throwable {
        String typeSimpleName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String tableName = getTableName(typeSimpleName);
        logger.info("Delete a record from " + tableName + "!");
    }

    public void logEditStatus(JoinPoint joinPoint) throws Throwable {
        String typeSimpleName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String tableName = getTableName(typeSimpleName);
        logger.info("Edit a record from " + tableName + "!");
    }
}
