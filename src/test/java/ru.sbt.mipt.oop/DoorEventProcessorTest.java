package ru.sbt.mipt.oop;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class DoorEventProcessorTest {
    @Test
    public void processEventTest_notDoorEvent() {
        SensorEvent event = mock(SensorEvent.class);
        when(event.getType()).thenReturn(SensorEventType.LIGHT_OFF);
        SmartHome smartHome = mock(SmartHome.class);
        new DoorEventProcessor().processEvent(smartHome, event);
        //Check that isDoorEvent called getType() twice
        verify(event, times(2)).getType();
    }

    @Test
    public void processEventTest_ok() throws NoSuchFieldException, IllegalAccessException {
        SensorEvent event = mock(SensorEvent.class);
        when(event.getType()).thenReturn(SensorEventType.DOOR_CLOSE);
        when(event.getObjectId()).thenReturn("1");
        SmartHome smartHome = mock(SmartHome.class);
        List<Door> doors = new ArrayList<>();
        doors.add(new Door(true, "1"));
        doors.add(new Door(false, "2"));
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(null, doors, "hall"));
        rooms.add(new Room(null, doors, "bath"));
        when(smartHome.getRooms()).thenReturn(rooms);
        new DoorEventProcessor().processEvent(smartHome, event);
        //Check that doors with id=1 are closed
        Door door1 = rooms.get(0).getDoorById("1");
        Door door2 = rooms.get(1).getDoorById("1");
        Field isOpen = Door.class.getDeclaredField("isOpen");
        isOpen.setAccessible(true);
        assertFalse(isOpen.getBoolean(door1));
        assertFalse(isOpen.getBoolean(door2));
    }
}
