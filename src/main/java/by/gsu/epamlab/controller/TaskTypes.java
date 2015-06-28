package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.Task;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;

public enum TaskTypes {

    TODAY("Today", false, true) {
        @Override
        boolean validateTask(Task task) {
            return task.getDateEnding().equals(Date.valueOf(LocalDate.now())) &&
                    !task.isCompleted() &&
                    !task.isDeleted();
        }
    },

    TOMORROW("Tomorrow", false, true) {
        @Override
        boolean validateTask(Task task) {
            return task.getDateEnding().equals(Date.valueOf(LocalDate.now().plusDays(DAYS_TO_NEXT_DAY))) &&
                    !task.isCompleted() &&
                    !task.isDeleted();
        }
    },

    SOMEDAY("Someday", true, true) {
        @Override
        boolean validateTask(Task task) {
            return !task.getDateEnding().equals(Date.valueOf(LocalDate.now().plusDays(DAYS_TO_NEXT_DAY))) &&
                    !task.getDateEnding().equals(Date.valueOf(LocalDate.now())) &&
                    !task.isCompleted() &&
                    !task.isDeleted();
        }
    },

    FIXED("Fixed", true, false) {
        @Override
        boolean validateTask(Task task) {
            return task.isCompleted();
        }
    },

    RECYCLE_BIN("Recycle Bin", true, false) {
        @Override
        boolean validateTask(Task task) {
            return task.isDeleted();
        }
    },

    ALL("All", true, true) {
        @Override
        boolean validateTask(Task task) {
            return true;
        }
    };

    private String value;
    private boolean isDataView;
    private boolean isFixedView;

    private TaskTypes(String value, boolean isDataView, boolean isFixedView) {
        this.value = value;
        this.isDataView = isDataView;
        this.isFixedView = isFixedView;
    }

    private static final int DAYS_TO_NEXT_DAY = 1;

    abstract boolean validateTask(Task task);

    public boolean getFixedViewParam() {
        return isFixedView;
    }

    public boolean getDateViewParam(){
        return isDataView;
    }

    public String getValue() {
        return value;
    }

    public List<Task> thinOutTasks(List<Task> tasks) {
        ListIterator<Task> iterator = tasks.listIterator();
        while (iterator.hasNext()) {
            if (!this.validateTask(iterator.next())) {
                iterator.remove();
            }
        }

        return tasks;
    }
}
