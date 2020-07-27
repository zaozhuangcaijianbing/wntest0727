package com.refactor.chapter1;

import com.refactor.chapter1.ver1.Customer;
import com.refactor.chapter1.ver1.Movie;
import com.refactor.chapter1.ver1.Rental;

import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        Customer customer = new Customer("wn");
        Movie movie = new Movie("重构", 0);
        Rental rental = new Rental(movie, 30);
        customer.addRental(rental);

        String statement = customer.statement();
        System.out.println(statement);


        Customer customer2 = null;
        Customer customer1 = Optional.ofNullable(customer2).filter(o -> {
            boolean a = o.getName() == null;
            return a;
        }).orElse(new Customer("jjj"));

        System.out.println(customer1);
    }
}
