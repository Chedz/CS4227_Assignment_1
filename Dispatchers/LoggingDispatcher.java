package Dispatchers;

import java.util.Vector;

import ContextObjects.PreRequestContext;
import ContextObjects.PostRequestContext;
import Interceptors.LoggingInterceptor;

public class LoggingDispatcher implements LoggingInterceptor{
    Vector interceptors_;

    public LoggingDispatcher() {
        interceptors_ = new Vector();
    }

    synchronized public void registerLoggingInterceptor(LoggingInterceptor i) {
        interceptors_.addElement(i);
    }

    synchronized public void removeLoggingInterceptor(LoggingInterceptor i) {
        interceptors_.removeElement(i);
    }

    public void dispatchLoggingInterceptorPreMarshal( PreRequestContext context) {
        Vector interceptors;
        
        synchronized (this) {
            interceptors = (Vector) interceptors_.clone();
        }
    
        for(int i = 0; i < interceptors.size(); i++) {
            LoggingInterceptor ic = (LoggingInterceptor)interceptors.elementAt(i);
            ic.onPreMarshalRequest(context);
        }
    }

    public void dispatchLoggingInterceptorPostMarshal( PostRequestContext context) {
        Vector interceptors;
        
        synchronized (this) {
            interceptors = (Vector) interceptors_.clone();
        }
    
        for(int i = 0; i < interceptors.size(); i++) {
            LoggingInterceptor ic = (LoggingInterceptor)interceptors.elementAt(i);
            ic.onPostMarshalRequest(context);
        }
    }

    // public static LoggingDispatcher theInstance() {
    //     return new LoggingDispatcher();
    // }

    @Override
    public void onPostMarshalRequest(PostRequestContext context) {
        // TODO Auto-generated method stub
        for (int i = 0; i < interceptors_.size(); i++) {
            LoggingInterceptor interceptor = (LoggingInterceptor) interceptors_.elementAt(i);
            interceptor.onPostMarshalRequest(context);
        }
    }

    @Override
    public void onPreMarshalRequest(PreRequestContext context) {
        for (int i = 0; i < interceptors_.size(); i++) {
            LoggingInterceptor interceptor = (LoggingInterceptor) interceptors_.elementAt(i);
            interceptor.onPreMarshalRequest(context);
        }
    }
}
