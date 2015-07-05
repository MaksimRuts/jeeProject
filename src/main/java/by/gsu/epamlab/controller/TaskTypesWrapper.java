package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.TaskTypes;

import java.sql.Date;

public enum TaskTypesWrapper {

    TODAY(TaskTypes.TODAY, false, true, true, true, false, false),
    TOMORROW(TaskTypes.TOMORROW, false, true, true, true, false, false),
    SOMEDAY(TaskTypes.SOMEDAY, true, true, true, true, false, false),
    COMPLETE(TaskTypes.COMPLETE, true, false, false, false, false, false),
    RECYCLE_BIN(TaskTypes.RECYCLE_BIN, true, false, false, false, true, true),
    ALL(TaskTypes.ALL, true, true, false, false, false, false);

    private TaskTypes type;
    private boolean isDateShow;
    private boolean isButtonComplete;
    private boolean isButtonAdd;
    private boolean isButtonEdit;
    private boolean isButtonRestore;
    private boolean isButtonRemoveAll;

    TaskTypesWrapper(TaskTypes type, boolean isDateShow, boolean isButtonComplete, boolean isButtonAdd, boolean isButtonEdit, boolean isButtonRestore, boolean isButtonRemoveAll) {
        this.type = type;
        this.isDateShow = isDateShow;
        this.isButtonComplete = isButtonComplete;
        this.isButtonAdd = isButtonAdd;
        this.isButtonEdit = isButtonEdit;
        this.isButtonRestore = isButtonRestore;
        this.isButtonRemoveAll = isButtonRemoveAll;
    }

    public TaskTypes getType() {
        return type;
    }

    public String getValue() {
        return type.getValue();
    }

    public Date getDate() {
        return type.getDate();
    }

    public boolean isDateShow() {
        return isDateShow;
    }

    public boolean isButtonComplete() {
        return isButtonComplete;
    }

    public boolean isButtonAdd() {
        return isButtonAdd;
    }

    public boolean isButtonEdit() {
        return isButtonEdit;
    }

    public boolean isButtonRestore() {
        return isButtonRestore;
    }

    public boolean isButtonRemoveAll() {
        return isButtonRemoveAll;
    }
}
