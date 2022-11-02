package com.readutf.logger;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Logger {

    LoggerFactory loggerFactory;
    Class<?> clazz;

    public void debug(Object... parts) {
        if(loggerFactory.isDebugAll() || loggerFactory.getDebugEnabledClasses().contains(clazz)) {
            loggerFactory.log(LoggerFactory.LogLevel.DEBUG, clazz, parts);
        }
    }

    public void fine(Object... parts) {
        loggerFactory.log(LoggerFactory.LogLevel.FINE, clazz, parts);
    }

    public void warn(Object... parts) {
        loggerFactory.log(LoggerFactory.LogLevel.FINE, clazz, parts);
    }

    public void severe(Object... parts) {
        loggerFactory.log(LoggerFactory.LogLevel.FINE, clazz, parts);
    }


}
