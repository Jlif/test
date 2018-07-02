package proxy.jdk;

import org.junit.Test;
import proxy.jdk.impl.ISubjectImpl;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @author jiangchen
 * Date:  2018/7/2
 * @since JDK 1.8
 */
public class JDKProxyTest {

    @Test
    public void test() {
        CustomizeHandler handle = new CustomizeHandler(ISubjectImpl.class);
        ISubject subject = (ISubject) Proxy.newProxyInstance(JDKProxyTest.class.getClassLoader(), new Class[]{ISubject.class}, handle);
        subject.execute();
    }

    @Test
    public void clazzTest() {
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass("$Proxy1", new Class[]{ISubject.class}, 1);
        try {
            FileOutputStream out = new FileOutputStream("D:/Temp/$Proxy1.class");
            out.write(proxyClassFile);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
