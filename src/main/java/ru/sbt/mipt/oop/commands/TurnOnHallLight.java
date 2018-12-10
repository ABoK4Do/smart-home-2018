package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homeComponents.Light;
import ru.sbt.mipt.oop.homeComponents.Room;
import ru.sbt.mipt.oop.SmartHome;

public class TurnOnHallLight implements Command {
    final private SmartHome smartHome;

    TurnOnHallLight(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall")) {
                room.executeAction(objectLight -> {
                    if (objectLight instanceof Light) {
                        ((Light) objectLight).setOn(true);
                    }
                });
            }
        }
    }
}
