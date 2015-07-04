package by.gsu.epamlab.testing;

import by.gsu.epamlab.controller.TaskTypesWrapper;

import java.time.LocalDate;

public class LocalDateTest {
    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        System.out.println(LocalDate.MIN);
        System.out.println(LocalDate.MAX);

        System.out.println(TaskTypesWrapper.TODAY.getValue());
    }
}
