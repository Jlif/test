package nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author jiangchen
 * @date 2018年06月12日
 */
public class NIOTest {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(new File("D:\\Temp\\a.txt"));
        FileOutputStream fos = new FileOutputStream(new File("D:\\Temp\\b.txt"));
        FileChannel readChannel = fis.getChannel(); // 读文件通道
        FileChannel writeChannel = fos.getChannel(); // 写文件通道
        ByteBuffer buffer = ByteBuffer.allocate(1024); // 读入数据缓存
        while (true) {
            buffer.clear();
            int len = readChannel.read(buffer); // 读入数据
            if (len == -1) {
                break; // 读取完毕
            }
            System.out.println("limit=" + buffer.limit() + " capacity=" + buffer.capacity() + " position=" + buffer.position());
            buffer.flip();//翻转标记
            System.out.println("翻转之后：limit=" + buffer.limit() + " capacity=" + buffer.capacity() + " position=" + buffer.position());
            writeChannel.write(buffer); // 写入文件
        }
        readChannel.close();
        writeChannel.close();
    }
}
