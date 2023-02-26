package Interceptors;

import ContextObjects.PostRequestContext;
import ContextObjects.PreRequestContext;

public interface LoggingInterceptor {
    void onPreRequest(PreRequestContext context);
    void onPostRequest(PostRequestContext context);
}
