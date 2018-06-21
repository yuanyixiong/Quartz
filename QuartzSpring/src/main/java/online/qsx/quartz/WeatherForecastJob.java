package online.qsx.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import online.qsx.common.WeatherForecast;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;


public class WeatherForecastJob {


	// 把要执行的操作
	public void showWeatherForecast()  {
		String time=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
		WeatherForecast weatherForecast=new WeatherForecast();
		JSONObject jsonObject1=weatherForecast.getJsonAtThisDay("遵义","weatherForecast.ini");
		JSONObject jsonObject2=weatherForecast.getJsonAtPresent("遵义","weatherForecast.ini");

		System.out.println(String.format("[------------------今天天气信息,更新时间:%s------------------]",time));
		System.out.println("[预报气象数据]:"+jsonObject1.get("ptime"));
		System.out.println("城市:"+jsonObject1.get("city"));
		System.out.println("最高温度:"+jsonObject1.get("temp1"));
		System.out.println("最低温度:"+jsonObject1.get("temp2"));
		System.out.println("天气:"+jsonObject1.get("weather"));
		System.out.println("[实时气象数据]:"+jsonObject2.get("time"));
		System.out.println("实时温度:"+jsonObject2.get("temp"));
		System.out.println("实时风向:"+jsonObject2.get("WD"));
		System.out.println("实时风速:"+jsonObject2.get("WS"));
		System.out.println("实时湿度:"+jsonObject2.get("SD"));
	}

}

