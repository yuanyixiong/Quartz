package online.qsx.test;

import online.qsx.quartz.WeatherForecastJob;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestQuartzSpring {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext-quartz.xml");
        System.out.println("Test start.");
    }
}
