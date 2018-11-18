package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class HallDoorEventProcessorTest {
    @Test
    public void processEventTest_notDoorEvent() {
        SensorEvent event = mock(SensorEvent.class);
        when(event.getType()).thenReturn(SensorEventType.LIGHT_OFF);
        SmartHome smartHome = mock(SmartHome.class);
        new HallDoorEventProcessor().processEvent(smartHome, event);
        verify(event).getType();
    }

    @Test
    public void processEventTest_hallAndNotHall() {
        SensorEvent event = mock(SensorEvent.class);
        when(event.getType()).thenReturn(SensorEventType.LIGHT_ON);
        when(event.getObjectId()).thenReturn("1");
        SmartHome smartHome = mock(SmartHome.class);
        List<Door> doors = new ArrayList<>();
        doors.add(new Door(true, "1"));
        doors.add(new Door(false, "2"));
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(null, doors, "hall"));
        rooms.add(new Room(null, doors, "bath"));
        when(smartHome.getRooms()).thenReturn(rooms);
        doNothing().when(smartHome).turnOffLights();
        new HallDoorEventProcessor().processEvent(smartHome, event);
        verify(smartHome, times(1)).turnOffLights();
    }
}
