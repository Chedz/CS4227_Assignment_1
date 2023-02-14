import MovieRentalSystem.Customer;
import MovieRentalSystem.Movie;
import MovieRentalSystem.Rental;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");
        Customer customer = new Customer("John");
        System.out.println("Added new Customer: " + customer.getName() );
        System.out.println("Initial Customer Statement:  \n" + customer.statement() + "\n");

        Movie movie = new Movie("The Godfather", 1);
        Rental rental = new Rental(movie, 1);
        customer.addRental(rental);

        Movie movie2 = new Movie("The Godfather Part II", 0);
        Rental rental2 = new Rental(movie2, 3);
        customer.addRental(rental2);

        System.out.println("Update Customer Statement:  \n" + customer.statement());
    }
}
