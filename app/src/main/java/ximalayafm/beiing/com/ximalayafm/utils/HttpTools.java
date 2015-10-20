package ximalayafm.beiing.com.ximalayafm.utils;

import android.os.Build;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created Author:Beiing Email:1101587382@qq.com Date:2015/10/12
 */
// 不可new 不可被继承
public final class HttpTools {

    //手机版本型号等信息
    public static final String USER_AGENT = "ting_4.1.7(" + Build.MODEL + "," + Build.VERSION.SDK_INT + ")";

    private HttpTools() {

    }

    private static final int CONNECT_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 6000;


    /**
     * get请求操作
     *
     * @param url
     * @return
     */
    public static byte[] doGet(String url) {
        byte[] ret = null;
        if (url != null) {
            HttpURLConnection conn = null;
            try {
                URL u = new URL(url);
                conn = (HttpURLConnection) u.openConnection();
                //设置连接的配置
                conn.setConnectTimeout(CONNECT_TIMEOUT);
                conn.setReadTimeout(READ_TIMEOUT);

                conn.setRequestMethod("GET");

                //完善HTTp请求的内容
                //1.设置确定的Http头信息

                //设置Accept头信息，告诉服务器，客户端能够接受什么样的数据
                conn.setRequestProperty("Accept", "application/*, text/*, image/*");

                //设置接收的内容压缩编码算法
                conn.setRequestProperty("Accept-Encoding", "gzip");

                //设置 User-Agent
                conn.setRequestProperty("User-Agent", USER_AGENT);

                conn.setDoInput(true);
                conn.connect();
                if (conn.getResponseCode() == 200) {
                    // TODO 给data赋值
                    InputStream in = null;
                    try {
                        in = conn.getInputStream();

                        //TODO 进行网络输入流的GZIP解压缩
                        String encoding = conn.getHeaderField("Content-Encoding");
                        if ("gzip".equals(encoding)) {
                            in = new GZIPInputStream(in);
                        }

                        ret = StreamUtil.readStream(in);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        StreamUtil.close(in);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                StreamUtil.close(conn);
            }
        }
        return ret;
    }

    /**
     * post方式请求操作
     *
     * @param url
     * @param params
     * @param encode
     * @return
     * @throws IOException
     */
    public static byte[] doPost(String url, HashMap<String, String> params,
                                String encode) throws IOException {
        byte[] ret = null;
        // 把要提交的数据组织成username=kk&psw=111格式
        if (url != null && params != null) {
            if (encode == null)
                encode = "UTF-8";

            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, String> en : params.entrySet()) {
                stringBuilder.append(en.getKey()).append("=")
                        .append(URLEncoder.encode(en.getValue(), encode))
                        .append("&");
            }
            // 删除最后的 &符号
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // 设置提交的数据的 类型
            conn.setRequestProperty("Content-Type",
                    " application/x-www-form-urlencoded");
            // 设置提交的数据的长度
            byte[] b = stringBuilder.toString().getBytes("UTF-8");
            conn.setRequestProperty("Content-Length", String.valueOf(b.length));
            // 提交数据 ---向服务器端写入数据
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(b, 0, b.length);
            InputStream in = null;
            if (conn.getResponseCode() == 200) {
                in = conn.getInputStream();
                ret = StreamUtil.readStream(in);
            }

            StreamUtil.close(outputStream);
            StreamUtil.close(in);
        }
        return ret;
    }

}
