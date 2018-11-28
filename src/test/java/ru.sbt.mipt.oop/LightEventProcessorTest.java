package ru.sbt.mipt.oop;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LightEventProcessorTest {
    private SensorEvent event;
    private SmartHome smartHome;

    @Before
    public void init() {
        List<Light> lights = new ArrayList<>();
        lights.add(new Light(true, "1"));
        lights.add(new Light(false, "2"));
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(lights, null, "hall"));
        smartHome = new SmartHome(rooms);
    }


    @Test
    public void processEventTest_switchOnOnLight() {
        event = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        new LightsEventProcessor().processEvent(smartHome, event);
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room : rooms) {
            Light light = room.getLightById("1");
            assertTrue(light.isOn());
        }
    }

    @Test
    public void processEventTest_switchOnOffLight() {
        event = new SensorEvent(SensorEventType.LIGHT_ON, "2");
        new LightsEventProcessor().processEvent(smartHome, event);
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room : rooms) {
            Light light = room.getLightById("2");
            assertTrue(light.isOn());
        }
    }

    @Test
    public void processEventTest_switchOffOffLight() {
        event = new SensorEvent(SensorEventType.LIGHT_OFF, "2");
        new LightsEventProcessor().processEvent(smartHome, event);
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room : rooms) {
            Light light = room.getLightById("2");
            assertFalse(light.isOn());
        }
    }

    @Test
    public void processEventTest_switchOffOnLight() {
        event = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        new LightsEventProcessor().processEvent(smartHome, event);
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room : rooms) {
            Light light = room.getLightById("1");
            assertFalse(light.isOn());
        }
    }
}
