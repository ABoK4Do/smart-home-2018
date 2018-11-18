package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightsEventProcessor implements EventProcessor {
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isLightEvent(event)) return;
        // событие от источника света
        for (Room room : smartHome.getRooms()) {
            Light light = room.getLightById(event.getObjectId());
            if (event.getType() == LIGHT_ON) {
                changeLightState(room, light, true, " was turned on.");
            } else {
                changeLightState(room, light, false, " was turned off.");
            }
        }
    }

    private void changeLightState(Room room, Light light, boolean state, String text) {
        light.setOn(state);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + text);
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}