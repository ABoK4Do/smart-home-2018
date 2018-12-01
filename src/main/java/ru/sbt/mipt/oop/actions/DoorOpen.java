package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Door;

public class DoorOpen implements Action {
    @Override
    public void execute(Object object) {
        if(object instanceof Door){
            ((Door) object).setOpen(true);
        }
    }
}
