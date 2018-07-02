package proxy.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RealSubject {

    private final static Logger LOGGER = LoggerFactory.getLogger(RealSubject.class);

    public void exec() {
        LOGGER.info("real exec");
    }
}
