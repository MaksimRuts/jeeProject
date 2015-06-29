package by.gsu.epamlab.model.beans;

import java.sql.Date;
import java.time.LocalDate;

public enum TaskTypes {
    TODAY("Today", false, false, false) {
        @Override
        public Date getDateHigher() {
            return Date.valueOf(LocalDate.now());
        }
    },
    TOMORROW("Tomorrow", false, false, false) {
        @Override
        public Date getDateBelow() {
            return Date.valueOf(LocalDate.now().plusDays(DAYS_TO_NEXT_DAY));
        }

        @Override
        public Date getDateHigher() {
            return Date.valueOf(LocalDate.now().plusDays(DAYS_TO_NEXT_DAY));
        }
    },
    SOMEDAY("Someday", false, false, true),
    COMPLETE("Complete", true, false, true),
    RECYCLE_BIN("Recycle Bin", false, true, true),
    ALL("All", true, true, true);

    private static final int DAYS_TO_NEXT_DAY = 1;

    private String value;
    private boolean isCompleted;
    private boolean isDeleted;
    private boolean isDateShow;

    private TaskTypes(String value, boolean isCompleted, boolean isDeleted, boolean isDateShow) {
        this.value = value;
        this.isCompleted = isCompleted;
        this.isDeleted = isDeleted;
        this.isDateShow = isDateShow;
    }

    public Date getDateBelow() {
//        return Date.valueOf(LocalDate.MIN);
        return Date.valueOf(LocalDate.now().minusYears(100));
    }

    public Date getDateHigher() {
//        return Date.valueOf(LocalDate.MAX);
        return Date.valueOf(LocalDate.now().plusYears(100));
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public boolean isDateShow() {
        return isDateShow;
    }

    public String getValue() {
        return value;
    }
}
