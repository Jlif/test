package util;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author jiangchen
 * @date 2018年06月01日
 */
public class StreamTest {

    public static void main(String[] args) {
        testParallelStream();
    }

    public static void testParallelStream() {
        int[] arr = IntStream.range(1, 5).toArray();
        new Thread(() -> {
            Arrays.stream(arr).parallel().forEach((v) -> {
                try {
                    System.out.println("first:" + v);
                    int sum = 0;
                    for (long i = 0; i < (1 << 28); i++) {
                        sum += i % 2;
                    }
                    System.out.println("first:" + v + ":" + sum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }).start();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            Arrays.stream(arr).parallel().forEach((v) -> {
                try {
                    System.out.println("second:" + v);
                    int sum = 0;
                    for (long i = 0; i < (1 << 30); i++) {
                        sum += i % 2;
                    }
                    System.out.println("second:" + v + ":" + sum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }).start();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Arrays.stream(arr).forEach((v) -> {
            //此处有断点
            System.out.println("third" + v);
        });
    }
}
