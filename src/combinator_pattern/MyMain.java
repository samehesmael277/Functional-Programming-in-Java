package combinator_pattern;

import java.time.LocalDate;

import static combinator_pattern.CustomerRegistrationValidator.*;

public class MyMain {

    public static void main(String[] args) {

        CustomerValidatorService validatorService = new CustomerValidatorService();

        Customer customer1 = new Customer(
                "Sameh",
                "samehesmael277@gmail.com",
                "011155",
                LocalDate.of(2003, 1, 8)
        );
        // old
        System.out.println(validatorService.isValid(customer1));

        // new
        ValidationResult result1 = isEmailValid()
                .and(isPhoneValid())
                .and(isAdult())
                .apply(customer1);
        System.out.println(result1);

        Customer customer2 = new Customer(
                "Sameh",
                "samehesmael277gmail.com",
                "0011155",
                LocalDate.of(2003, 1, 8)
        );
        // old
        System.out.println(validatorService.isValid(customer2));

        // new
        ValidationResult result2 = isEmailValid()
                .and(isPhoneValid())
                .and(isAdult())
                .apply(customer2);
        System.out.println(result2);
    }
}
