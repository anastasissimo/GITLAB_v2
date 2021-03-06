package com.data.lambda.part2.exercise;

import com.data.Person;

import java.util.function.Predicate;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class ArrowNotationExercise {

    @Test
    public void getAge() {
        // Person -> Integer
        final Function<Person, Integer> getAge = person -> person.getAge(); //

        assertEquals(Integer.valueOf(33), getAge.apply(new Person("", "", 33)));
    }

    @Test
    public void compareAges() {
        //  use BiPredicate
        // compareAges: (Person, Person) -> boolean
        final BiPredicate<Person, Person> compareAges = (person, person2) -> person.getAge() == person2.getAge();
        assertEquals(true, compareAges.test(new Person("a", "b", 22), new Person("c", "d", 22)));
    }

    //
    // getFullName: Person -> String

    //
    // ageOfPersonWithTheLongestFullName: (Person -> String) -> (Person, Person) -> int
    //

    @Test
    public void getAgeOfPersonWithTheLongestFullName() {
        // Person -> String
        final Function<Person, String> getFullName = person -> person.getFirstName() + person
                .getLastName(); //

        // (Person, Person) -> Integer
        //  use ageOfPersonWithTheLongestFullName(getFullName)
        final BiFunction<Person, Person, Integer> ageOfPersonWithTheLongestFullName =
                (person, person2) -> {
                    if (getFullName.apply(person).length() > getFullName.apply(person2).length()) {
                        return person.getAge();
                    }
                    return person2.getAge();
                };

        assertEquals(
                Integer.valueOf(1),
                ageOfPersonWithTheLongestFullName.apply(
                        new Person("a", "b", 2),
                        new Person("aa", "b", 1)));
    }
}