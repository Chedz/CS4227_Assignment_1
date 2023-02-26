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

    public void dispatchLoggingInterceptorPreTargetRequest( PreRequestContext context) {
        Vector interceptors;
        
        synchronized (this) {
            interceptors = (Vector) interceptors_.clone();
        }
    
        for(int i = 0; i < interceptors.size(); i++) {
            LoggingInterceptor ic = (LoggingInterceptor)interceptors.elementAt(i);
            ic.onPreRequest(context);
        }
    }

    public void dispatchLoggingInterceptorPostTargetRequest( PostRequestContext context) {
        Vector interceptors;
        
        synchronized (this) {
            interceptors = (Vector) interceptors_.clone();
        }
    
        for(int i = 0; i < interceptors.size(); i++) {
            LoggingInterceptor ic = (LoggingInterceptor)interceptors.elementAt(i);
            ic.onPostRequest(context);
        }
    }

    @Override
    public void onPostRequest(PostRequestContext context) {
        for (int i = 0; i < interceptors_.size(); i++) {
            LoggingInterceptor interceptor = (LoggingInterceptor) interceptors_.elementAt(i);
            interceptor.onPostRequest(context);
        }
    }

    @Override
    public void onPreRequest(PreRequestContext context) {
        for (int i = 0; i < interceptors_.size(); i++) {
            LoggingInterceptor interceptor = (LoggingInterceptor) interceptors_.elementAt(i);
            interceptor.onPreRequest(context);
        }
    }
}
