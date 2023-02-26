package TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;
import MovieRentalSystem.MovieRentalSystem;
import Interceptors.LoggingInterceptor;
import ContextObjects.PreRequestContext;
import ContextObjects.PostRequestContext;
import MovieRentalSystem.Customer;
import MovieRentalSystem.Movie;

public class TestInterceptor {

    @Test
    public void test() {
        // initialize MovieRentalSystem
        MovieRentalSystem movieRentalSystem = new MovieRentalSystem();
        
        // Define concrete logging interceptor
        LoggingInterceptor interceptor = new LoggingInterceptor() {

            private static final Logger LOGGER = Logger.getLogger(MovieRentalSystem.class.getName());
            
            public void onPreRequest(PreRequestContext context) {
                // start timer
                context.startTimer();
                // log call made to target method that is being intercepted
                context.logCall();   
            }
            
            public void onPostRequest(PostRequestContext context) {
                // stop timer 
                LOGGER.info("INFO: It took "+context.stopTimer()+" milliseconds to process the request \n"); 
            }
        };

        //Register Logging Interceptor With Dispatcher
        movieRentalSystem.getLoggingDispatcherInstance().registerLoggingInterceptor(interceptor);

        // interceptor architecture is initialised, now we can test the interceptor

        Customer customer = movieRentalSystem.createCustomer("John");

        Movie movie = movieRentalSystem.createMovie("The Godfather", 1);
        movieRentalSystem.addRental(customer, movieRentalSystem.createRental(movie, 1));

        Movie movie1 = movieRentalSystem.createMovie("Toy Story", 2);
        movieRentalSystem.addRental(customer, movieRentalSystem.createRental(movie1, 3));

        String mockStatement = "Rental Record for John\n" + "\t" + "The Godfather" + "\t" + "3.0" + "\n" + "\t" + "Toy Story" + "\t" + "1.5" + "\n" + "Amount owed is 4.5" + "\n" + "You earned 3 frequent renter points";   
        assertEquals(mockStatement, movieRentalSystem.getStringStatement(customer)); // test that the statement is correct

        Movie movie2 = movieRentalSystem.createMovie("InterStellar", 0);
        movieRentalSystem.addRental(customer, movieRentalSystem.createRental(movie2, 2));

        assertEquals(5, movieRentalSystem.getFrequentRenterPoints(customer));   // test that the frequent renter points are correct
    }
    
}
