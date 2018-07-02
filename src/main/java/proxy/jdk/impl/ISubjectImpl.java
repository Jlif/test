package proxy.jdk.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import proxy.jdk.ISubject;

public class ISubjectImpl implements ISubject {

    private final static Logger LOGGER = LoggerFactory.getLogger(ISubjectImpl.class);

    /**
     * 执行
     */
    public void execute() {
        LOGGER.info("ISubjectImpl execute");
    }
}
