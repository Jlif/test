package proxy.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomizeHandler implements InvocationHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomizeHandler.class);

    private Object target;

    public CustomizeHandler(Class clazz) {
        try {
            this.target = clazz.newInstance();
        } catch (InstantiationException e) {
            LOGGER.error("InstantiationException", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException", e);
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();
        Object result = method.invoke(target, args);
        after();

        LOGGER.info("proxy class={}", proxy.getClass());
        return result;
    }

    private void before() {
        LOGGER.info("handle before");
    }

    private void after() {
        LOGGER.info("handle after");
    }
}
