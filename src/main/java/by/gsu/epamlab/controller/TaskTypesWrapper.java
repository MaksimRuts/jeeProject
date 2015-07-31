package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.TaskType;

import java.sql.Date;

public enum TaskTypesWrapper {

    TODAY(TaskType.TODAY, false, true, true, true, false, false),
    TOMORROW(TaskType.TOMORROW, false, true, true, true, false, false),
    SOMEDAY(TaskType.SOMEDAY, true, true, true, true, false, false),
    COMPLETE(TaskType.COMPLETE, true, false, false, false, false, false) {
        @Override
        public String getEmptyMessage() {
            return "You don't have completed tasks";
        }
    },
    RECYCLE_BIN(TaskType.RECYCLE_BIN, true, false, false, false, true, true) {
        @Override
        public String getEmptyMessage() {
            return "You don't have removed tasks";
        }
    },
    ALL(TaskType.ALL, true, true, false, false, false, false) {
        @Override
        public String getEmptyMessage() {
            return "You don't have tasks, You are lucky!";
        }
    };

    private TaskType type;
    private boolean isDateShow;
    private boolean isButtonComplete;
    private boolean isButtonAdd;
    private boolean isButtonEdit;
    private boolean isButtonRestore;
    private boolean isButtonRemoveAll;

    TaskTypesWrapper(TaskType type, boolean isDateShow, boolean isButtonComplete, boolean isButtonAdd, boolean isButtonEdit, boolean isButtonRestore, boolean isButtonRemoveAll) {
        this.type = type;
        this.isDateShow = isDateShow;
        this.isButtonComplete = isButtonComplete;
        this.isButtonAdd = isButtonAdd;
        this.isButtonEdit = isButtonEdit;
        this.isButtonRestore = isButtonRestore;
        this.isButtonRemoveAll = isButtonRemoveAll;
    }

    public TaskType getType() {
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

    public String getEmptyMessage() {
        return "You don't have tasks for " + getValue().toLowerCase();
    }
}
