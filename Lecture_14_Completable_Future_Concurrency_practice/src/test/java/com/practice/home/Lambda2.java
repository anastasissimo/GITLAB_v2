package com.practice.home;

import java.util.function.Function;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * : Convert to Lambda
 */
public class Lambda2 {

    public static class Person {

        String name;
        int age;
        Gender gender;

        public Person(String name, int age, Gender gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Gender getGender() {
            return gender;
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        enum Gender {
            MALE,
            FEMALE,
            UNKNOWN
        }
    }

    /**
     * Fill in the gaps and insert instructions to make code executable
     */
    @Test
    public void testLambda() throws InterruptedException {
        // : replace with com.data.lambda all person access points

        Person person1 = new Person("Andrey", 10, Person.Gender.MALE);
        Person person2 = new Person("Evgenii", 20, Person.Gender.MALE);
        Person person3 = new Person("Vala", 12, Person.Gender.FEMALE);

        List<Person> list = Arrays.asList(person1, person2, person3);
        List<Person> males = new ArrayList<>();


        list.stream().filter(person -> person.gender == Person.Gender.MALE)
                .forEach(person -> males.add(person));

        assertArrayEquals(males.toArray(), Arrays.asList(person1, person2).toArray());


        males.clear();

        list.stream().filter(person -> person.gender == Person.Gender.MALE && person.age > 10)
                .forEach(males::add); // METHOD REFERENCE IS NOT ALLOWED IN PREDICATE


        assertArrayEquals(males.toArray(), Arrays.asList(person2).toArray());


        List<Person> peopleWithSalary = new ArrayList<>();


        list.stream().filter(
                person -> (person.gender == Person.Gender.MALE && person.age > 10) || (person.gender
                        == Person.Gender.FEMALE)).forEach(person -> peopleWithSalary.add(person));

        int age = 0;

        age = peopleWithSalary.stream().mapToInt(person -> person.getAge()).sum();

        assertEquals(age, 32);


        StringBuilder sb = new StringBuilder();

        list.stream().forEach(person -> {
                    Function<Person, String> getName = Person::getName;
                    sb.append(getName.apply(person));
                    sb.append(" ");
                }
        );

        assertEquals("Andrey Evgenii Vala ", sb.toString());
    }

    private String getName(Person p) {
        return p.getName();
    }
}