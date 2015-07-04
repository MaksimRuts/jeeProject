package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.TaskTypes;

public enum TaskTypesWrapper {

    TODAY(TaskTypes.TODAY, false, true),
    TOMORROW(TaskTypes.TOMORROW, false, true),
    SOMEDAY(TaskTypes.SOMEDAY, true, true),
    COMPLETE(TaskTypes.COMPLETE, true, false),
    RECYCLE_BIN(TaskTypes.RECYCLE_BIN, true, false),
    ALL(TaskTypes.ALL, true, true), ;

    private TaskTypes type;
    private boolean isDateShow;
    private boolean isButtonComplete;

    TaskTypesWrapper(TaskTypes type, boolean isDateShow, boolean isButtonComplete) {
        this.type = type;
        this.isDateShow = isDateShow;
        this.isButtonComplete = isButtonComplete;
    }

    public TaskTypes getType() {
        return type;
    }

    public String getValue() {
        return getType().getValue();
    }

    public boolean isDateShow() {
        return isDateShow;
    }

    public boolean isButtonComplete() {
        return isButtonComplete;
    }
}
