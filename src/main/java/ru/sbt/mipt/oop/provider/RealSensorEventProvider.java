package ru.sbt.mipt.oop.provider;

import ru.sbt.mipt.oop.event.SensorEvent;

import java.util.Collection;

public class RealSensorEventProvider implements SensorEventProvider {
    final private Collection<SensorEvent> sensorEvents;

    RealSensorEventProvider(Collection<SensorEvent> sensorEvents) {
        this.sensorEvents = sensorEvents;
    }

    @Override
    public SensorEvent getNextSensorEvent() {
        return null;
    }
}
