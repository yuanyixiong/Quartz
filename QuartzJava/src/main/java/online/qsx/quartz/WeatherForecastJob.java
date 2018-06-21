package online.qsx.quartz;


import com.alibaba.fastjson.JSONObject;
import online.qsx.common.WeatherForecast;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class WeatherForecastJob implements Job {

	@Override
	// 把要执行的操作，写在execute方法中
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		WeatherForecast weatherForecast=new WeatherForecast();
		JSONObject jsonObject1=weatherForecast.getJsonAtThisDay("武汉","weatherForecast.ini");
		System.out.println("[-----------------------今天天气-----------------------]");
		System.out.println("城市:"+jsonObject1.get("city"));
		System.out.println("最高温度:"+jsonObject1.get("temp1"));
		System.out.println("最低温度:"+jsonObject1.get("temp2"));
		System.out.println("天气:"+jsonObject1.get("weather"));
		System.out.println("发布时间:"+jsonObject1.get("ptime"));

		JSONObject jsonObject2=weatherForecast.getJsonAtPresent("武汉","weatherForecast.ini");
		System.out.println("[-----------------------当前天气-----------------------]");
		System.out.println("城市:"+jsonObject2.get("city"));
		System.out.println("温度:"+jsonObject2.get("temp"));
		System.out.println("风向:"+jsonObject2.get("WD"));
		System.out.println("风速:"+jsonObject2.get("WS"));
		System.out.println("湿度:"+jsonObject2.get("SD"));
		System.out.println("发布时间:"+jsonObject2.get("time"));
	}

}

