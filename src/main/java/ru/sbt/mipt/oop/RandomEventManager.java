package ru.sbt.mipt.oop;

import java.util.Collection;

class RandomEventManager implements EventManager {
    @Override
    public void runEventsCycle(SmartHome smartHome) {
        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(smartHome);
        SensorEventProvider sensorEventProvider = new RandomSensorEventProvider();
        // начинаем цикл обработки событий
        SensorEvent event = sensorEventProvider.getNextSensorEvent();
        Collection<EventProcessor> eventProcessors = ConfigProcessors.configureEventProcessors();
        //Подписываем всех процессоров на наблюдателя
        for (EventProcessor processor : eventProcessors) {
            homeEventsObserver.subscribe(processor);
        }

        while (event != null) {
            System.out.println("Got event: " + event);
            homeEventsObserver.notifySubscribers(event);
            event = sensorEventProvider.getNextSensorEvent();
        }
    }
}
