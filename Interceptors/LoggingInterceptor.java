package Interceptors;

import ContextObjects.PostRequestContext;
import ContextObjects.PreRequestContext;

public interface LoggingInterceptor {
    void onPreMarshalRequest(PreRequestContext context);
    void onPostMarshalRequest(PostRequestContext context);
}
