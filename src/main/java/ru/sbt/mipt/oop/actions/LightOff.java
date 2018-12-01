package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Light;

public class LightOff implements Action {
    @Override
    public void execute(Object object) {
        if (object instanceof Light) {
            ((Light) object).setOn(false);
        }
    }
}
