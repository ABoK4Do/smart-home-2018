package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapter.EventManagerAdapter;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
class MyConfiguration {
    MyConfiguration() {

    }

    @Bean
    public EventManager eventManager() {
        Observer observer = configureSimpleObserver();
        return new EventManagerAdapter(observer);
    }

    @Bean
    public SmartHomeLoader smartHomeLoader() {
        return new FileSmartHomeLoader();
    }

    private static Observer configureSimpleObserver() {
        Observer observer = new HomeEventsObserver();
        observer.subscribe(configureEventProcessors());
        return observer;
    }

    private static Collection<EventProcessor> configureEventProcessors() {
        Collection<EventProcessor> eventProcessors = new ArrayList<>();
        eventProcessors.add(new LightsEventProcessor());
        eventProcessors.add(new DoorEventProcessor());
        eventProcessors.add(new HallDoorEventProcessor());
        return eventProcessors;
    }
}



