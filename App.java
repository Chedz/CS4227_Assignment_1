import Interceptors.LoggingInterceptor;
import MovieRentalSystem.Customer;
import MovieRentalSystem.Movie;
import MovieRentalSystem.MovieRentalSystem;
import ContextObjects.PreRequestContext;
import ContextObjects.PostRequestContext;
import java.util.logging.Logger;


public class App {
    private static final Logger LOGGER = Logger.getLogger(MovieRentalSystem.class.getName());
    public static void main(String[] args) {
        //Create Movie Rental System 
        MovieRentalSystem movieRentalSystem = new MovieRentalSystem();
        
        // Define concrete logging interceptor
        LoggingInterceptor interceptor = new LoggingInterceptor() {
            private static final Logger LOGGER = Logger.getLogger(MovieRentalSystem.class.getName());
            public void onPreMarshalRequest(PreRequestContext context) {
                // start timer
                context.startTimer();
                context.logCall();   
            }
            
            public void onPostMarshalRequest(PostRequestContext context) {
                // stop timer
                System.out.println("INFO: It took "+context.stopTimer()+" milliseconds to process the request \n");  
            }
        };

        //Register Logging Interceptor With Dispatcher
        movieRentalSystem.getLoggingDispatcherInstance().registerLoggingInterceptor(interceptor);


        Customer customer = movieRentalSystem.createCustomer("John");

        Movie movie = movieRentalSystem.createMovie("The Godfather", 1);
        movieRentalSystem.addRental(customer, movieRentalSystem.createRental(movie, 1));

        System.out.println(movieRentalSystem.getStringStatement(customer));

        Movie movie1 = movieRentalSystem.createMovie("Toy Story", 2);
        movieRentalSystem.addRental(customer, movieRentalSystem.createRental(movie1, 3));

        System.out.println(movieRentalSystem.getStringStatement(customer));
    }
}
