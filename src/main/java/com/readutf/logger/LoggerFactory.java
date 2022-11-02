package com.readutf.logger;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Getter
public class LoggerFactory {

    //settings
    private String prefix;
    @Setter boolean debugAll;
    private List<Class<?>> debugEnabledClasses;

    //logging
    @Setter private Consumer<String> logConsumer;
    @Setter private String logFormat;


    public LoggerFactory(String prefix, Class<?>... debugEnabledClasses) {
        this.debugEnabledClasses = Arrays.asList(debugEnabledClasses);
        this.logConsumer = System.out::println;
        this.logFormat = "[%prefix%] (%class%) %message%";
    }

    public List<Boolean> toggleDebugClasses(Class<?>... classes) {
        List<Boolean> enabled = new ArrayList<>();

        for (Class<?> aClass : classes) {
            boolean removed = debugEnabledClasses.remove(aClass);
            if(!removed) {
                debugEnabledClasses.add(aClass);
            }
            enabled.add(!removed);
        }
        return enabled;
    }

    public void log(LogLevel logLevel, Class<?> clazz, Object... parts) {
        String message = getLogFormat()
                .replace("%class%", clazz.getSimpleName())
                .replace("%message%", Arrays.stream(parts).map(Object::toString).collect(Collectors.joining(", ")))
                .replace("%prefix%", prefix);
        getLogConsumer().accept(message);
    }

    public Logger getLogger(Class<?> clazz) {
        return new Logger(this, clazz);
    }

    enum LogLevel {

        DEBUG, FINE, WARN, SEVERE

    }

}
