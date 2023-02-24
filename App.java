import Interceptors.LoggingInterceptor;
import MovieRentalSystem.Customer;

// import MovieRentalSystem.Customer;
// import MovieRentalSystem.Movie;
// import MovieRentalSystem.Rental;
// import ContextObjects.UnmarshaledRequest;
// import Interceptors.LoggingInterceptor;


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
        Customer customer = new Customer("John");
        LoggingInterceptor interceptor = new LoggingInterceptor(customer);
        Customer proxy = (Customer) interceptor.createProxy();
        int result = proxy.getTotalFrequentRenterPoints();
        System.out.println("Result: " + result);
    }
}
