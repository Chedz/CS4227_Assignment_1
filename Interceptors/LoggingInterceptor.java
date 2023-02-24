package Interceptors;

import ContextObjects.PostRequestContext;
import ContextObjects.PreRequestContext;
// import Dispatchers.LoggingDispatcher;
// import java.lang.reflect.InvocationHandler;
// import java.lang.reflect.Method;
// import java.lang.reflect.Proxy;

public interface LoggingInterceptor {
    void onPreMarshalRequest(PreRequestContext context);
    void onPostMarshalRequest(PostRequestContext context);
}

// public class LoggingInterceptor implements InvocationHandler {
//     private Object targetObject;

//     public LoggingInterceptor(Object targetObject) {
//         this.targetObject = targetObject;
//     }

//     public Object createProxy() {
//         return Proxy.newProxyInstance(
//                 targetObject.getClass().getClassLoader(),
//                 targetObject.getClass().getInterfaces(),
//                 this);
//     }

//     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//         Object result;
//         try {
//             log("Before method " + method.getName());
//             log("Arguments: " + args[0] + ", " + args[1]);
//             result = method.invoke(targetObject, args);
//             log("After method " + method.getName());
//         } catch (Exception e) {
//             log("Exception in method " + method.getName() + ": " + e.getMessage());
//             throw e;
//         }
//         return result;
//     }

//     private void log(String message) {
//         System.out.println("[LOG] " + message);
//     }

// }
