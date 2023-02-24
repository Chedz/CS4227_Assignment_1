package Dispatchers;

import java.util.Vector;

import ContextObjects.UnmarshaledRequest;
import Interceptors.LoggingInterceptor;

public class LoggingDispatcher implements LoggingInterceptor{
    Vector interceptors_;

    synchronized public void registerClientRequestInterceptor(LoggingInterceptor i) {
        interceptors_.addElement(i);
    }

    synchronized public void removeClientRequestInterceptor(LoggingInterceptor i) {
        interceptors_.removeElement(i);
    }

    public void onPreMarshalRequest(UnmarshaledRequest context) {
        for (int i = 0; i < interceptors_.size(); i++) {
            LoggingInterceptor interceptor = (LoggingInterceptor) interceptors_.elementAt(i);
            interceptor.onPreMarshalRequest(context);
        }
    }

    public void dispatchClientRequestInterceptorPreMarshal( UnmarshaledRequest context) {
        Vector interceptors;
        
        synchronized (this) {
            interceptors = (Vector) interceptors_.clone();
        }
    
        for(int i = 0; i < interceptors.size(); i++) {
            LoggingInterceptor ic = (LoggingInterceptor)interceptors.elementAt(i);
            ic.onPreMarshalRequest(context);
        }
    }

    public static LoggingDispatcher theInstance() {
        return null;
    }
}
