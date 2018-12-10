package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.SmartHome;

public class AlarmModeOn implements Command {
    final private SmartHome smartHome;

    AlarmModeOn(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().setAlarmMode();
    }
}
