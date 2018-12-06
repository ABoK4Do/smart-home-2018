package ru.sbt.mipt.oop;

import java.util.ArrayList;

public class HomeEventsObserver implements Observer {
    private ArrayList<EventProcessor> subscribers = new ArrayList<>();
    private SmartHome smartHome;

    HomeEventsObserver(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void subscribe(EventProcessor processor) {
        subscribers.add(processor);
    }

    @Override
    public void unsubscribe(EventProcessor processor) {
        subscribers.remove(processor);
    }

    @Override
    public void notifySubscribers(SensorEvent event) {
        for (EventProcessor subscriber : subscribers) {
            subscriber.processEvent(smartHome, event);
        }
    }
}
