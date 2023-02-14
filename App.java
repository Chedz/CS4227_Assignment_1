import MovieRentalSystem.Customer;
import MovieRentalSystem.Movie;
import MovieRentalSystem.Rental;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Customer customer = new Customer("John");
        System.out.println(customer.statement());

        Movie movie = new Movie("The Godfather", 1);
        Rental rental = new Rental(movie, 1);
        customer.addRental(rental);
        System.out.println(customer.statement());
    }
}
