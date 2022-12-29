package com.readutf.uls;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Logger {

    LoggerFactory loggerFactory;
    Class<?> clazz;

    public void debug(Object... parts) {
        if(loggerFactory.isDebugAll()) {
            if(!loggerFactory.getDebugEnabledClasses().contains(clazz)) {
                loggerFactory.log(LoggerFactory.LogLevel.DEBUG, clazz, parts);
            }
        } else if (loggerFactory.getDebugEnabledClasses().contains(clazz)) {
            loggerFactory.log(LoggerFactory.LogLevel.DEBUG, clazz, parts);
        }
    }

    public void debug(Supplier<Object> provider) {
        if(loggerFactory.isDebugAll() || loggerFactory.getDebugEnabledClasses().contains(clazz)) {
            loggerFactory.log(LoggerFactory.LogLevel.DEBUG, clazz, provider.get());
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
