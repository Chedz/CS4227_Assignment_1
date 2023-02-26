package MovieRentalSystem;

import ContextObjects.PostRequestContext;
import ContextObjects.PreRequestContext;
import Dispatchers.LoggingDispatcher;
import java.util.logging.Logger;

public class MovieRentalSystem {
    private static final LoggingDispatcher loggingDispatcher =  new LoggingDispatcher();
    private long timeMilliseconds;
    private static final Logger LOGGER = Logger.getLogger(MovieRentalSystem.class.getName());

    public LoggingDispatcher getLoggingDispatcherInstance(){
        return loggingDispatcher;
    }

    public Movie createMovie(String name, int priceCode){

        //Pre Movie Creation Context object creation
        PreRequestContext preMovieCreationContext = new PreRequestContext() {

            @Override
            public void startTimer() {
                timeMilliseconds = System.currentTimeMillis();
            }

            @Override
            public void logCall() {
                // System.out.println("[LOG]: " + "MovieRentalSystem.createMovie() called creating new Movie: " + name + " with priceCode: " + priceCode + "");
                LOGGER.info("MovieRentalSystem.createMovie() called creating new Movie: " + name + " with priceCode: " + priceCode + "");
            }
            
        };
        //pre movie creation
        loggingDispatcher.onPreMarshalRequest(preMovieCreationContext);
        Movie newMovie = new Movie(name, priceCode);
        //Post movie creation context object creation
        PostRequestContext postRequestContext = new PostRequestContext() {

            @Override
            public long stopTimer() {
                return System.currentTimeMillis() - timeMilliseconds;
            }
            
        };
        //post movie creation
        loggingDispatcher.dispatchLoggingInterceptorPostMarshal(postRequestContext);
        return newMovie;
    }

    public Customer createCustomer(String name ){
        //System.out.println("[LOG]: " + "Adding new Customer: " + name );

        //Pre create customer context object creation
        PreRequestContext createCustomerContext = new PreRequestContext() {

            @Override
            public void startTimer() {
                timeMilliseconds = System.currentTimeMillis();
            }

            @Override
            public void logCall() {
                LOGGER.getLogger(MovieRentalSystem.class.getName()).info("Adding new Customer: " + name);
            }
            
        };
        loggingDispatcher.onPreMarshalRequest(createCustomerContext);

        try {
            Customer customer = new Customer(name);

            PostRequestContext postRequestContext = new PostRequestContext() {

                @Override
                public long stopTimer() {
                    return System.currentTimeMillis() - timeMilliseconds;
                }
                
            };
            //post create customer
            loggingDispatcher.dispatchLoggingInterceptorPostMarshal(postRequestContext);

            return customer;
        } catch (Exception e) {
            return null;
        }

    }

    public Rental createRental(Movie movie, int daysRented ){

        return new Rental(movie, daysRented);
    }

    public String addRental(Customer customer, Rental rental ){

        PreRequestContext addRentalContext = new PreRequestContext() {

            @Override
            public void startTimer() {
                timeMilliseconds = System.currentTimeMillis();
            }

            @Override
            public void logCall() {
                LOGGER.getLogger(MovieRentalSystem.class.getName()).info("MovieRentalSystem.addRental() called adding new Rental to Customer: " + customer.getName() + " with Movie: " + rental.getMovie().getTitle() + " for " + rental.getDaysRented() + " days");
            }
            
        };
        loggingDispatcher.onPreMarshalRequest(addRentalContext);

        //print frequent renter points before request
        LOGGER.getLogger(MovieRentalSystem.class.getName()).info("Initial Frequent Renter Points: " + customer.getTotalFrequentRenterPoints());
        try {
            customer.addRental(rental);
            //print frequent renter points after request
            LOGGER.getLogger(MovieRentalSystem.class.getName()).info("Updated Frequent Renter Points: " + customer.getTotalFrequentRenterPoints());
        } catch (Exception e) {
            LOGGER.getLogger(MovieRentalSystem.class.getName()).warning((e.getMessage()));
            return e.getMessage();
        }

        PostRequestContext postRequestContext = new PostRequestContext() {

            @Override
            public long stopTimer() {
                return System.currentTimeMillis() - timeMilliseconds;
            }
            
        };
        //post add rental
        loggingDispatcher.dispatchLoggingInterceptorPostMarshal(postRequestContext);
        
        return "Rental added successfully";
    }

    public String getStringStatement(Customer customer ){
        LOGGER.getLogger(MovieRentalSystem.class.getName()).info("MovieRentalSystem.getStringStatement() called for Customer: " + customer.getName() );

        return customer.statement();
    }

    public int getFrequentRenterPoints(Customer customer ){
        LOGGER.getLogger(MovieRentalSystem.class.getName()).info("MovieRentalSystem.getFrequentRenterPoints() called for Customer: " + customer.getName() );

        return customer.getTotalFrequentRenterPoints();
    }

}