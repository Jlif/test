package proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class RealSubjectIntercept implements MethodInterceptor {

    private final static Logger LOGGER = LoggerFactory.getLogger(RealSubjectIntercept.class);

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        LOGGER.info("before");

        Object invoke = methodProxy.invoke(o, objects);
        LOGGER.info("after");
        return invoke;
    }
}
