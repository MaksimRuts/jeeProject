package by.gsu.epamlab.controller;

public enum TaskTypes {

    TODAY("Today"), TOMORROW("Tomorrow"), SOMEDAY("Someday"), FIXED("Fixed"), RECYCLE_BIN("Recycle Bin");

    private String value;

    private TaskTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
