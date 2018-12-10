package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.SmartHome;

public class TurnOnAlarm implements Command {
    final private SmartHome smartHome;
    final private String password;

    TurnOnAlarm(SmartHome smartHome, String password) {
        this.smartHome = smartHome;
        this.password = password;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().activate(password);
    }
}
