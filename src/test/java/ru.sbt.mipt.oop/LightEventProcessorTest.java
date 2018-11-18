package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class LightEventProcessorTest {
    @Test
    public void processEventTest_notDoorEvent() {
        SensorEvent event = mock(SensorEvent.class);
        when(event.getType()).thenReturn(SensorEventType.DOOR_CLOSE);
        SmartHome smartHome = mock(SmartHome.class);
        new LightsEventProcessor().processEvent(smartHome, event);
        //Check that isLightEvent called getType() twice
        verify(event, times(2)).getType();
    }

    @Test
    public void processEventTest_ok() throws NoSuchFieldException, IllegalAccessException {
        SensorEvent event = mock(SensorEvent.class);
        when(event.getType()).thenReturn(SensorEventType.LIGHT_OFF);
        when(event.getObjectId()).thenReturn("1");
        SmartHome smartHome = mock(SmartHome.class);
        List<Light> lights = new ArrayList<>();
        lights.add(new Light( "1", true));
        lights.add(new Light("2", false));
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(lights, null, "hall"));
        rooms.add(new Room(lights, null, "bath"));
        when(smartHome.getRooms()).thenReturn(rooms);
        new LightsEventProcessor().processEvent(smartHome, event);
        //Check that doors with id=1 are closed
        Light light1 = rooms.get(0).getLightById("1");
        Light light2 = rooms.get(1).getLightById("1");
        assertFalse(light1.isOn());
        assertFalse(light2.isOn());
    }
}
