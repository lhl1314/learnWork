package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    /**
     * //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */

    public static String app_id = "2016082100305078";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥----就是您生成应用公钥的那个私钥
     */

    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJB5C+Of8qXJTfU7Oo0qzqF7FM43cMtOrksTKUtN6/pNsx3DttUNpfqovA4H9P8oqQv319bpNGIPEvbG3Mnb0zrAy9miOeVnJS3PsM1B3S7WU4sR2bjo7Imy3fXTx5XXee4UFQ6RbYPhpTfs0AiMeAyrbBPFw+prsCL6je8dU3g0ljUTUHjiTEQ9e8ii+wjemH0tp3qPQeFRn3smA4y1BZzQHSH4bN0peCEXRgx3ID6vOmxdC9vZyf/+vmhOJUwoVg8a0lGkTU6i6ZYlqATgWaUinD6GxhgkNsZtX0u7O8GhX3ZHYzQtkBvH8E9zfdMAhiRTtoF2+zwhudeE1ooMaLAgMBAAECggEAKIGpAuKIEFCv3ViT2kr0vHpmefEfpwzwBsxuxFC3K/WiqhmNI6Jr+y2iBFEYsVxkI6fHg8WlGWzxfvBRgNP8MwMf/QhMkSjCwMX8HG2zpz9v1ZNdyjcY9CoTJF0eXE4J51h1A2Lz0taA17+6//Zu6x6BaZvbNNX6oAnQpJ7eRXtFn7deB8L582SVvQfSlu7GQDw2x0PiQEnQmWAcWc+hWKM6L1BvdZeFyo+XHA013DeuurTIrFK4BSxA4EF82GstM/PsE34kD+ZgbDqCY+Cu//0H/eIokRf+9MXkzsProk1Fd/pMvHTfIe6y8oByEVnEyw4yNf+kIrAjCiRJq3qS4QKBgQDLjU/cFN1D1IiYZgnJH0R/OIMztp546RbYY3xLsjCNyIVivk5tCdf9GB8SSHsiR+48hKhB7PI3sHKHwrLaEHb3AklDveekcCgDPl91C5mJYCME9VGbkMZipE6AEyDi9e/oIXt2u4g+qhCnEb7q8IEYtV9514xFyRSEX8l9rMI5OwKBgQCsVk19zQe6A/tZHF1VwyBNon0ifHM8tuba7ZHo5LC7uA5aZU0Nr/WkjE/kRf0h+xrD62bQEPUzHpCnigcqX8EMlzn6zrPGhCZXqw0QKMZdo/2JpWy46Ez7tyFCuZ85S8aFI5LOFtV5Ek/SLDv60FPjVeh5h/TPXkJ6wrF/FxxS8QKBgQCauu7L42hXGYZEIrypkAUnmDPT6awJSVY+Y1Gm0OsrvBP4/aTsKiFvmRh1c3d2las5gbXNd2qgfpZxMMV4Ei/8kFZVElS1oTQpUw6JEKpDzTqRmFHSqXjEnxW1MdU78avOjzUlCmUFDY3fEhRIz02zFJxhKELPAY2BGrc1u09g5QKBgFLBsr6ZaX9zEA0PqOZZKDb/s1WPK4tZTWyOLsYGo7PRQ9s20BSljbExy9+TmGXr/kNc1GdP7V9Z6x75Ek3P45VXPXlqLzxCE2N/+WrsGa235TVnIdl9fBJYM3mmR4atE6y1k3ax0lssZvZHVZow1kfR5iwOFIpL+mKXAcNE5s4RAoGAH5e33G4UCj0Drz6Fcv5N0PkPK9e9vg+YemtncXtcUiiHD96Ki2KPjlG/bN0uKMWf596WOxWZPz0YHuZGewVhEQLffs1l1ZXFCH/MydhQFXXsJiCV93kCkbXeHvmxJO4uEqls9DTaMrzx0jrIG8LvYll50f3hvWTW2+J3uA489OU=";

    /**------通过应用公钥获取的支付宝公钥
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     */
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnFnluhpkiXs9eeufJyOv6BhBCLP8R66O0Ul7zNGivX8nSZhXo67J+g7QtdTyyy3Uu1CKKqolnG5zpslwy2SL8hFOy5BIMpfQgYQ3rFn2F7bkZU7cX+pBYouXhFvWa3h3myjhd1hWUKhfqkdP46xjv55BtBiobktNiHIhXtyHikqbEfcHV2ET3nOCljVjz/mO2BtHhJ5Fb7oQoyugfYOWmIoNyI+N68f8on4Fne/sqwHNfCZC7arl6WZ3/wZ0L1IzYDQOS9dF5yDvdxExuZq/JA1PhOJeyJz78pUe3w+qaKqa1MIjSQ0SMmRWuvQwataqzVCwISyf15Eb3wlekfm/sQIDAQAB";
    /**
     * // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static String notify_url = "http://localhost:8080/notify_url.jsp";

    /**
     * // 页面跳转同步通知页面路径 需http://格式的完整路径，alipay.trade.page.pay-JAVA-UTF-8不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static String return_url = "http://www.bestlhl.top/order/updateOrderState.do?";
//  public static String return_url = "http://localhost/order/updateOrderState.do?";

//    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";
//
//    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String return_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    /**
     * // 签名方式
     */
    public static String sign_type = "RSA2";

    /**
     * 字符编码格式
     */
    public static String charset = "utf-8";

    /**
     * 支付宝网关
     */
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 支付宝网关
     */
    public static String log_path = "C:\\";

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

