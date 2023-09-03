package org.example.tests.practice;

import org.assertj.core.api.LocalDateAssert;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
public class AssertJexample {
    public static void main(String[] args) {
        String response_name = "Gaurav";
        assertThat(response_name).isNotNull().isNotBlank().isEqualTo("Gaurav"); // there are 3 test cases in one using ". operator"

        List<String> names = Arrays.asList("Jack","Doe","Max");
        assertThat(names).hasSize(3).contains("Max").doesNotContain("Gaurav");
        // Printing response is not recommended in automation testing, it will give response on its own

        // objects
        Person person = new Person("Ash",19);
        assertThat(person).hasFieldOrProperty("age").hasFieldOrPropertyWithValue("name","Ash");
        System.out.println("assertion done");

        // time
        LocalDate date = LocalDate.now();
        System.out.println(date);

        assertThat(date)
                .isAfterOrEqualTo(LocalDate.of(2023,6,23))
                .isBeforeOrEqualTo(LocalDate.of(2023,12,16))
                .isBetween(
                        LocalDate.of(2023,06,12),
                        LocalDate.of(2023,07,29)
                );

        // map
        Map ms = new HashMap();
        ms.put("One",1);
        ms.put("Two",2);

        assertThat(ms)
                .hasSize(2)
                .containsEntry("Two",2)
                .doesNotContainEntry("Three",3)
                .doesNotContainValue(3);

        // database
        File file = new File("TestData.json");
        assertThat(file).exists().isFile().canRead(); // 3 assertions here

        // with testng(assertEquals) assertion we cannot use two assertion together unlike assertj

    }

    static class Person{
        String name;
        int age;

        Person(String name,int age){
            this.name = name;
            this.age = age;
        }
    }
}
