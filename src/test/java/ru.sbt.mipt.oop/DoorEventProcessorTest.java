package ru.sbt.mipt.oop;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DoorEventProcessorTest {
    private SensorEvent event;
    private SmartHome smartHome;

    @Before
    public void init() {
        List<Door> doors = new ArrayList<>();
        doors.add(new Door(true, "1"));
        doors.add(new Door(false, "2"));
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(null, doors, "hall"));
        smartHome = new SmartHome(rooms);
    }

    @Test
    public void processEventTest_openOpenedDoor() throws NoSuchFieldException, IllegalAccessException {
        event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        new DoorEventProcessor().processEvent(smartHome, event);
        Collection<Room> rooms = smartHome.getRooms();
        for(Room room: rooms){
            Door door = room.getDoorById("1");
            Field isOpen = Door.class.getDeclaredField("isOpen");
            isOpen.setAccessible(true);
            assertTrue(isOpen.getBoolean(door));
        }
    }

    @Test
    public void processEventTest_openClosedDoor() throws NoSuchFieldException, IllegalAccessException {
        event = new SensorEvent(SensorEventType.DOOR_OPEN, "2");
        new DoorEventProcessor().processEvent(smartHome, event);
        Collection<Room> rooms = smartHome.getRooms();
        for(Room room: rooms){
            Door door = room.getDoorById("2");
            Field isOpen = Door.class.getDeclaredField("isOpen");
            isOpen.setAccessible(true);
            assertTrue(isOpen.getBoolean(door));
        }
    }

    @Test
    public void processEventTest_closeClosedDoor() throws IllegalAccessException, NoSuchFieldException {
        event = new SensorEvent(SensorEventType.DOOR_CLOSE, "2");
        new DoorEventProcessor().processEvent(smartHome, event);
        Collection<Room> rooms = smartHome.getRooms();
        for(Room room: rooms){
            Door door = room.getDoorById("2");
            Field isOpen = Door.class.getDeclaredField("isOpen");
            isOpen.setAccessible(true);
            assertFalse(isOpen.getBoolean(door));
        }
    }

    @Test
    public void processEventTest_closeOpenedDoor() throws NoSuchFieldException, IllegalAccessException {
        event = new SensorEvent(SensorEventType.DOOR_CLOSE, "1");
        new DoorEventProcessor().processEvent(smartHome, event);
        Collection<Room> rooms = smartHome.getRooms();
        for(Room room: rooms){
            Door door = room.getDoorById("1");
            Field isOpen = Door.class.getDeclaredField("isOpen");
            isOpen.setAccessible(true);
            assertFalse(isOpen.getBoolean(door));
        }
    }
}
