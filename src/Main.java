import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Person> people = List.of(
                new Person("Sameh", Gender.MALE),
                new Person("Ahmed", Gender.MALE),
                new Person("Hend", Gender.FEMALE),
                new Person("Mohamed", Gender.MALE),
                new Person("Zenab", Gender.FEMALE)
        );

        // ***** Imperative approach *****
        System.out.println("***** Imperative approach *****");
        List<Person> males = new ArrayList<>();

        for (Person p : people) {
            if (Gender.MALE.equals(p.gender)) {
                males.add(p);
            }
        }

        males.forEach(p -> {
            System.out.println(p.toString());
        });

        // ***** Declarative approach *****
        System.out.println("***** Declarative approach *****");
        people.stream()
                .filter(person -> Gender.FEMALE.equals(person.gender))
                .toList()
                .forEach(System.out::println);

        // OR
        System.out.println("OR");
        Predicate<Person> personPredicate = person -> Gender.FEMALE.equals(person.gender);
        List<Person> females = people.stream()
                .filter(personPredicate)
                .toList();

        females.forEach(System.out::println);

        // ***** Function *****
        System.out.println("***** Function *****");

        // with old way
        System.out.println("with old way");
        int result = increment(1);
        System.out.println(result);

        // with new way
        System.out.println("with new way");
        int increment = incrementByOneFunction.apply(1);
        System.out.println(increment);

        int multiplyBy10 = multiplyBy10FUnction.apply(2);
        System.out.println(multiplyBy10);

        int addByOneThenMultiplyBy10 = addByOneThenMultiplyBy10Function.apply(2);
        System.out.println(addByOneThenMultiplyBy10);

        int incrementByOneThenMultiplyByNumber = incrementByOneAndMultiply.apply(2, 3);
        System.out.println(incrementByOneThenMultiplyByNumber);

        // ***** Consumer *****
        System.out.println("***** Consumer *****");

        Customer customer = new Customer("sameh", "0111");

        // with old way
        System.out.println("with old way");
        greetCustomer(customer);

        // with new way
        System.out.println("with new way");
        greetCustomerConsumer.accept(customer);

        greetCustomerWithPhoneNumberOrNot.accept(customer, true);
        greetCustomerWithPhoneNumberOrNot.accept(customer, false);

        // ***** Predicate *****
        System.out.println("***** Predicate *****");

        // with old way
        System.out.println("with old way");
        System.out.println(isPhoneNumberValid("01115546850"));
        System.out.println(isPhoneNumberValid("02115546850"));
        System.out.println(isPhoneNumberValid("011155468"));

        // with new way
        System.out.println("with new way");
        System.out.println(isPhoneNumberValidPredicate.test("01115546850"));
        System.out.println(isPhoneNumberValidPredicate.test("02115546850"));
        System.out.println(isPhoneNumberValidPredicate.test("011155468"));

        System.out.println(isContain5.test("01115468"));
        System.out.println(isContain5.test("0111468"));

        System.out.println(isPhoneNumberValidPredicate.and(isContain5).test("01115546850"));
        System.out.println(isPhoneNumberValidPredicate.and(isContain5).test("01113346810"));

        // ***** Supplier *****
        System.out.println("***** Supplier *****");

        // with old way
        System.out.println("with old way");
        System.out.println(getDBConnectionUrl());

        // with new way
        System.out.println("with new way");
        System.out.println(getDBConnectionUrlSupplier.get());

        // ***** Streams *****
        System.out.println("***** Streams *****");

        people.stream()
                .map(person -> person.gender)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        people.stream()
                .map(person -> person.gender)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        people.stream()
                .map(person -> person.name)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        people.stream()
                .map(person -> person.name)
                .mapToInt(String::length) // = name -> name.length()
                .forEach(System.out::println);
        // that = that
        Function<Person, String> personStringFunction = person -> person.name;
        ToIntFunction<String> length = String::length;
        IntConsumer println = System.out::println;
        people.stream()
                .map(personStringFunction)
                .mapToInt(length)
                .forEach(println);

        boolean isContainOnlyFemales = people.stream()
                .allMatch(person -> Gender.FEMALE.equals(person.gender));
        System.out.println(isContainOnlyFemales);

        boolean isContainFemalesAtLeast = people.stream()
                .anyMatch(person -> Gender.FEMALE.equals(person.gender));
        System.out.println(isContainFemalesAtLeast);

        boolean isDoNotContainFemales = people.stream()
                .noneMatch(person -> Gender.FEMALE.equals(person.gender));
        System.out.println(isDoNotContainFemales);

        // ***** Optional *****
        System.out.println("***** Optional *****");

        Object value = Optional.ofNullable(null)
                .orElseGet(() -> "default value");
        System.out.println(value);

        Object value2 = Optional.ofNullable("Hello 2")
                .orElseGet(() -> "default value");
        System.out.println(value2);

        Object value3 = Optional.ofNullable("Hello 3")
                .orElseThrow(() -> new IllegalStateException("Exception"));
        System.out.println(value3);

        Optional.ofNullable("Hello 4")
                .ifPresent(v -> System.out.println("message: " + v));

        Optional.ofNullable(null)
                .ifPresentOrElse(v -> System.out.println("message sent: " + v),
                        () -> System.out.println("failed to send message"));
    }

    enum Gender {
        MALE,
        FEMALE
    }

    static class Person {
        private final String name;
        private final Gender gender;

        Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    // ***** Functional *****
    // Functional Function interface

    // with new way
    // Function <input, return output>
    static Function<Integer, Integer> incrementByOneFunction = number -> number + 1;

    static Function<Integer, Integer> multiplyBy10FUnction = number -> number * 10;

    static Function<Integer, Integer> addByOneThenMultiplyBy10Function = incrementByOneFunction.andThen(multiplyBy10FUnction);

    // take two numbers int and return int
    // BiFunction<input1, input2, return output>
    static BiFunction<Integer, Integer, Integer> incrementByOneAndMultiply =
            (numberToIncrement, numberToMultiply) -> (numberToIncrement + 1) * numberToMultiply;

    // with old way
    static int increment(int number) {
        return number + 1;
    }

    // For Consumer Functional Function interface
    // ***** Consumer *****
    static class Customer {
        private final String name;
        private final String phone;

        public Customer(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }
    }

    // by old way
    static void greetCustomer(Customer customer) {
        System.out.println("Hello, " + customer.name + ", thanks for register, your phone number: " + customer.phone);
    }

    // by new way
    // Consumer<input> no return
    static Consumer<Customer> greetCustomerConsumer = customer ->
            System.out.println("Hello, " + customer.name + ", thanks for register, your phone number: " + customer.phone);

    // BiConsumer<input, input> no return
    static BiConsumer<Customer, Boolean> greetCustomerWithPhoneNumberOrNot = (customer, showPhone) ->
            System.out.println("Hello, " + customer.name + ", thanks for register, your phone number: " +
                    (showPhone ? customer.phone : "**********"));

    // ***** Predicate *****
    static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.startsWith("01") && phoneNumber.length() == 11;
    }

    // Predicate<Input Type> return Boolean
    static Predicate<String> isPhoneNumberValidPredicate = phoneNumber ->
            phoneNumber.startsWith("01") && phoneNumber.length() == 11;

    static Predicate<String> isContain5 = number ->
            number.contains("5");

    // ***** Supplier *****
    static String getDBConnectionUrl() {
        return "https//:192.168.1.1";
    }

    // Supplier<Output type> no input parameter
    static Supplier<String> getDBConnectionUrlSupplier = () -> "https//:192.168.1.1";
}