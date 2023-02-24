import Interceptors.LoggingInterceptor;
import MovieRentalSystem.Customer;

import MovieRentalSystem.Customer;
import MovieRentalSystem.Movie;
import MovieRentalSystem.MovieRentalSystem;
import MovieRentalSystem.Rental;
import ContextObjects.PreRequestContext;
import ContextObjects.PostRequestContext;
import Dispatchers.LoggingDispatcher;
import Interceptors.LoggingInterceptor;


// public class App {
//     public static void main(String[] args) throws Exception {

//         // LoggingInterceptor myInterceptor = new LoggingInterceptor () {
//         //     public void onPreMarshalRequest(UnmarshaledRequest context) {
//         //         System.out.println(context.getObj() + "called");
//         //     // .............
//         //     }
            
//         //     // public void onPostMarshalRequest(MarshaledRequest context) {
//         //     //     // ............
//         //     // }
//         // };

//         // LoggingDispatcher.theInstance().registerClientRequestInterceptor(myInterceptor);
//         // Do Work

//         // System.out.println("Hello, World!");
//         Customer customer = new Customer("John");
//         System.out.println("Added new Customer: " + customer.getName() );
//         System.out.println("Initial Customer Statement:  \n" + customer.statement() + "\n");

//         Movie movie = new Movie("The Godfather", 1);
//         Rental rental = new Rental(movie, 1);
//         customer.addRental(rental);

//         Movie movie2 = new Movie("The Godfather Part II", 0);
//         Rental rental2 = new Rental(movie2, 3);
//         customer.addRental(rental2);

//         System.out.println("Update Customer Statement:  \n" + customer.statement());
//     }
// }



public class App {
    public static void main(String[] args) {
        //Calculator calculator = new SimpleCalculator();

        // Customer customer = new Customer("John");
        // LoggingInterceptor interceptor = new LoggingInterceptor(customer);
        // Customer proxy = (Customer) interceptor.createProxy();
        // int result = proxy.getTotalFrequentRenterPoints();
        // System.out.println("Result: " + result);

        //Create Movie Rental System 
        MovieRentalSystem movieRentalSystem = new MovieRentalSystem();
        
        // Define concrete logging interceptor
        LoggingInterceptor interceptor = new LoggingInterceptor() {
            public void onPreMarshalRequest(PreRequestContext context) {
                // System.out.println(context.startTimer() + "called");
                //log frequent renter points before request 

                // start timer
                context.startTimer();   
            // .............
            }
            
            public void onPostMarshalRequest(PostRequestContext context) {
                // ............
                // stop timer
                System.out.println("INFO: It took "+context.stopTimer()+" milliseconds to create a movie");  
            }
        };

        //Register Logging Interceptor With Dispatcher
        movieRentalSystem.getLoggingDispatcherInstance().registerLoggingInterceptor(interceptor);

        //Register Logging Interceptor With Dispatcher
        // LoggingDispatcher dispatcher = new LoggingDispatcher();
        // dispatcher.registerLoggingInterceptor(interceptor);

        // Customer customer = new Customer("John");
        // System.out.println("Added new Customer: " + customer.getName() );

        // movieRentalSystem.createCustomer(customer);
        // movieRentalSystem.createRental(customer, new Movie("The Godfather", 1), 1);
        Movie movie = movieRentalSystem.createMovie("The Godfather", 1);

        //Customer customer = new Customer("John");
        // System.out.println("Added new Customer: " + customer.getName() );

        Customer customer = movieRentalSystem.createCustomer("John");

        // System.out.println(customer.statement());

        movieRentalSystem.addRental(customer, movieRentalSystem.createRental(movie, 1));

        System.out.println(movieRentalSystem.getStringStatement(customer));



       // LoggingInterceptor interceptor = new LoggingInterceptor(customer);
        //Customer proxy = (Customer) interceptor.createProxy();
        //int result = MovieRentalSystem.getLoggingDispatcherInstance() //.getTotalFrequentRenterPoints();
          //Register Credential Validation Interceptor With Dispatcher
        //movieRentalSystem.getLoggingDispatcherInstance().registerLoggingInterceptor(interceptor);
        //System.out.println("Result: " + result);
    }
}
