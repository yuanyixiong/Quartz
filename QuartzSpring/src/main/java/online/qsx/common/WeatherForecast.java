package online.qsx.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 该文件为提供给学员的资料
 * 天气的访问工具 必须在classpath下提供一个weatherForecast.ini的配置文件 文件内容为：
 * 101010100:北京
 * 101010300:朝阳
 * 101010400:顺义
 * 101010500:怀柔
 * .....
 * @author qsx
 *
 */
public final class WeatherForecast {
    /**
     * 访问国家气象局的气象数据,通过城市编码访问[当前的实时环境信息]
     *
     * @param cityid
     *            城市编码
     * @JsonProperty city 城市
     * @JsonProperty temp 温度
     * @JsonProperty WD 风向
     * @JsonProperty WS 风速
     * @JsonProperty SD 湿度
     * @JsonProperty time 发布时间
     * @return
     * @throws IOException
     */
    public final JSONObject getJsonAtPresent(String cityid) throws IOException {
        URL url = new URL(String.format("http://www.weather.com.cn/data/sk/%s.html", cityid));
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        BufferedReader br = new BufferedReader(new InputStreamReader(connectionData.getInputStream(), "UTF-8"));
        StringBuilder json = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            json.append(line);
        }
        return JSONObject.parseObject(json.toString()).getJSONObject("weatherinfo");
    }

    /**
     * 访问国家气象局的气象数据,通过城市名称访问[当前的实时环境信息]
     *
     * @param cityName
     *            城市名称
     * @param fileName
     *            文件
     * @JsonProperty city 城市
     * @JsonProperty temp 温度
     * @JsonProperty WD 风向
     * @JsonProperty WS 风速
     * @JsonProperty SD 湿度
     * @JsonProperty time 发布时间
     * @return
     */
    public final JSONObject getJsonAtPresent(String cityName, String fileName) {
        try {
            return getJsonAtPresent(loadCity(fileName).get(cityName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 访问国家气象局的气象数据,通过城市编码访问[当前的实时天气信息]
     *
     * @param cityid
     *            城市编码
     * @JsonProperty city 城市
     * @JsonProperty temp1 最高温度
     * @JsonProperty temp2 最低温度
     * @JsonProperty weather 天气
     * @JsonProperty ptime 发布时间
     * @return
     * @throws IOException
     */
    public final JSONObject getJsonAtThisDay(String cityid) throws IOException {
        URL url = new URL(String.format("http://www.weather.com.cn/data/cityinfo/%s.html", cityid));
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        BufferedReader br = new BufferedReader(new InputStreamReader(connectionData.getInputStream(), "UTF-8"));
        StringBuilder json = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            json.append(line);
        }
        return JSONObject.parseObject(json.toString()).getJSONObject("weatherinfo");
    }

    /**
     * 访问国家气象局的气象数据,通过城市名称访问[当前的实时天气信息]
     *
     * @param cityid
     *            城市编码
     * @JsonProperty city 城市
     * @JsonProperty temp1 最高温度
     * @JsonProperty temp2 最低温度
     * @JsonProperty weather 天气
     * @JsonProperty ptime 发布时间
     * @return
     * @throws IOException
     */
    public final JSONObject getJsonAtThisDay(String cityName, String fileName){
        try {
            return getJsonAtThisDay(loadCity(fileName).get(cityName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加载城市编码的初始文件,返回城市编码及城市信息
     *
     * @return map key->城市名 value->城市编码
     * @throws IOException
     */
    public final Map<String, String> loadCity(String fileName) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName==null?"weatherForecast.ini":fileName);
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        final BufferedReader bufferedReader = new BufferedReader(reader);
        @SuppressWarnings("serial")
        Map<String, String> map = new HashMap<String, String>() {
            {
                String text = null;
                while ((text = bufferedReader.readLine()) != null) {
                    String[] citysInfo = text.split(":");
                    put(citysInfo[1], citysInfo[0]);

                }
            }
        };
        bufferedReader.close();
        reader.close();
        inputStream.close();
        return map;
    }
}
