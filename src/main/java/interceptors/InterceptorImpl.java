package interceptors;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import usecases.Logger;

import java.io.Serializable;

@MyInterceptor
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class InterceptorImpl implements Serializable {
    @Inject
    Logger logger;

    @AroundInvoke
    public Object logEvent(InvocationContext context) throws Exception {
        logger.addLog(context.getMethod().getName(), context.getTarget().getClass().getName());
        return context.proceed();
    }
}
