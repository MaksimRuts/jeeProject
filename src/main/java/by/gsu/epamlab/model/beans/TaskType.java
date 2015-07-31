package by.gsu.epamlab.model.beans;

import java.sql.Date;
import java.time.LocalDate;

public enum TaskType {
    TODAY("Today", false, false) {
        @Override
        public Date getDateHigher() {
            return Date.valueOf(LocalDate.now());
        }

        @Override
        public Date getDate() {
            return Date.valueOf(LocalDate.now());
        }
    },
    TOMORROW("Tomorrow", false, false) {
        @Override
        public Date getDateBelow() {
            return Date.valueOf(LocalDate.now().plusDays(TOMORROW_DAY_OFFSET));
        }

        @Override
        public Date getDateHigher() {
            return Date.valueOf(LocalDate.now().plusDays(TOMORROW_DAY_OFFSET));
        }

        @Override
        public Date getDate() {
            return Date.valueOf(LocalDate.now().plusDays(TOMORROW_DAY_OFFSET));
        }
    },
    SOMEDAY("Someday", false, false) {
        @Override
        public Date getDateBelow() {
            return Date.valueOf(LocalDate.now().plusDays(SOMEDAY_DAY_OFFSET));
        }
    },
    COMPLETE("Complete", true, false),
    RECYCLE_BIN("Recycle Bin", false, true),
    ALL("All", true, true);

    private static final int TOMORROW_DAY_OFFSET = 1;
    private static final int SOMEDAY_DAY_OFFSET = 2;

    private String value;
    private boolean isCompleted;
    private boolean isDeleted;

    private TaskType(String value, boolean isCompleted, boolean isDeleted) {
        this.value = value;
        this.isCompleted = isCompleted;
        this.isDeleted = isDeleted;
    }

    public Date getDateBelow() {
//        return Date.valueOf(LocalDate.MIN);
        return Date.valueOf(LocalDate.now().minusYears(100));
    }

    public Date getDateHigher() {
//        return Date.valueOf(LocalDate.MAX);
        return Date.valueOf(LocalDate.now().plusYears(100));
    }

    public Date getDate() {
        return null;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public String getValue() {
        return value;
    }
}
