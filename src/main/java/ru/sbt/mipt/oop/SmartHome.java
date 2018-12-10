package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.homeComponents.Alarm;
import ru.sbt.mipt.oop.homeComponents.Light;
import ru.sbt.mipt.oop.homeComponents.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;
    private Alarm alarm;

    public SmartHome() {
        rooms = new ArrayList<>();
        alarm = new Alarm();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
        alarm = new Alarm();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void turnOffLights() {
        this.executeAction(object -> {
            if (object instanceof Light) {
                ((Light) object).setOn(false);
            }
        });
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        for (Room room : rooms) {
            room.executeAction(action);
        }
    }
}
