package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.actions.LightOff;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void turnOffLights() {
        this.executeAction(new LightOff());
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        for (Room room : rooms) {
            room.executeAction(action);
        }
    }
}
