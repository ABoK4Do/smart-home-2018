package ru.sbt.mipt.oop;

public interface Observer {
    void subscribe(EventProcessor processor);
    void unsubscribe(EventProcessor processor);
    void notifySubscribers(SensorEvent event);
}
