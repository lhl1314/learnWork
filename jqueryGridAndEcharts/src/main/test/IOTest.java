import org.junit.Test;

import java.io.*;

/**
 * @author lhl
 * @version 1.0
 * @date 2020/1/17 10:40
 * @description TODO
 */
public class IOTest {
    /**
     * inputStream  输入流
     * outputStream 输出流
     */
    @Test
    public void test01() throws IOException {
        String path="C:\\Users\\lhl\\Desktop\\test1.txt";
        InputStream in=new FileInputStream(new File(path));
        byte[]bytes=new byte[in.available()];
        try {
            int by=0;
            while ((by = in.read(bytes)) != -1){
                System.out.print(by+"--"+new String(bytes,0,by));
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
