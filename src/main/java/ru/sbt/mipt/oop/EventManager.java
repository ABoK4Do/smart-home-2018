package ru.sbt.mipt.oop;

public class EventManager {
    final private Observer observer;
    final private SensorEventProvider sensorEventProvider;

    EventManager(Observer observer, SensorEventProvider sensorEventProvider) {
        this.observer = observer;
        this.sensorEventProvider = sensorEventProvider;
    }

    public void runEventsCycle(SmartHome smartHome) {
        // начинаем цикл обработки событий
        SensorEvent event = sensorEventProvider.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            observer.notifySubscribers(smartHome, event);
            event = sensorEventProvider.getNextSensorEvent();
        }
    }
}
