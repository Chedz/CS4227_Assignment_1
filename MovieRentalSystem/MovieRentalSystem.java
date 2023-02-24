package MovieRentalSystem;

import ContextObjects.PostRequestContext;
import ContextObjects.PreRequestContext;
import Dispatchers.LoggingDispatcher;

public class MovieRentalSystem {
    private static final LoggingDispatcher loggingDispatcher =  new LoggingDispatcher();
    private long timeMilliseconds;

    public LoggingDispatcher getLoggingDispatcherInstance(){
        return loggingDispatcher;
    }


    public Movie createMovie(String name, int priceCode){

        System.out.println("[LOG]: " + "MovieRentalSystem.createMovie() called creating new Movie: " + name + " with priceCode: " + priceCode + "");

        //Pre Movie Creation Context object creation
        PreRequestContext preMovieCreationContext = new PreRequestContext() {

            @Override
            public void startTimer() {
                timeMilliseconds = System.currentTimeMillis();
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
        System.out.println("[LOG]: " + "Added new Customer: " + name );
        return new Customer(name);
    }

    public Rental createRental(Movie movie, int daysRented ){

        return new Rental(movie, daysRented);
    }

    public Boolean addRental(Customer customer, Rental rental ){

        System.out.println("[LOG]: " + "MovieRentalSystem.addRental() called adding new Rental to Customer: " + customer.getName() + " with Movie: " + rental.getMovie().getTitle() + " for " + rental.getDaysRented() + " days");

        //print frequent renter points before request
        System.out.println("[LOG]: " + "Initial Frequent Renter Points: " + customer.getTotalFrequentRenterPoints());
        try {
            customer.addRental(rental);
            //print frequent renter points after request
            System.out.println("[LOG]: " + "Final Frequent Renter Points: " + customer.getTotalFrequentRenterPoints());
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }

    public String getStringStatement(Customer customer ){
        System.out.println("[LOG]: " + "MovieRentalSystem.getStringStatement() called for Customer: " + customer.getName() );

        return customer.statement();
    }

    public int getFrequentRenterPoints(Customer customer ){

        return customer.getTotalFrequentRenterPoints();
    }

}