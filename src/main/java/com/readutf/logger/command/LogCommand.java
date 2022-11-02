package com.readutf.logger.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandIssuer;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import com.readutf.logger.LoggerFactory;
import lombok.AllArgsConstructor;

import java.util.List;

@CommandAlias("logger|log|debug|debugger") @AllArgsConstructor
public class LogCommand extends BaseCommand {

    ClassLoader classLoader;
    LoggerFactory loggerFactory;

    @Subcommand("toggleclass")
    public void toggleClass(CommandIssuer issuer, String className) {
        if(!className.matches("^[a-zA-Z0-9_]*$")) {
            issuer.sendMessage("Invalid classname");
            return;
        }
        Class<?> clazz;
        try {
            clazz = classLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            issuer.sendMessage("Invalid classname");
            return;
        }
        List<Boolean> results = loggerFactory.toggleDebugClasses(clazz);
        if(results.get(0)) {
            issuer.sendMessage("Debugs within " + className + " have been enabled.");
        } else {
            issuer.sendMessage("Debugs within " + className + " have been disabled.");
        }

    }

    @Subcommand("list")
    public void list(CommandIssuer issuer) {
        issuer.sendMessage("&7&m-------------------------");
        issuer.sendMessage("&6&lEnabled Classes: &7(Click to disabled)");
        for (Class<?> debugEnabledClass : loggerFactory.getDebugEnabledClasses()) {
            issuer.sendMessage(" &7* &e" + debugEnabledClass.getSimpleName());
        }
        issuer.sendMessage("&7&m-------------------------");
    }

}
