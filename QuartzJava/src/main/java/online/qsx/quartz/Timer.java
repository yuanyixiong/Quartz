package online.qsx.quartz;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class Timer {

    /**
     * 根据根据频率定时调度
     */
    public void test1() {
        // 通过schedulerFactory获取一个调度器
        SchedulerFactory schedulerfactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            // 通过schedulerFactory获取一个调度器
            scheduler = schedulerfactory.getScheduler();

            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            JobDetail jobDetail = new JobDetail("job1", "jgroup1", WeatherForecastJob.class);

            // 定义调度触发规则，比如每1秒运行一次，共运行8次
            SimpleTrigger simpleTrigger = new SimpleTrigger("simpleTrigger", "triggerGroup");
            // 马上启动
            simpleTrigger.setStartTime(new Date());
            // 间隔时间
            simpleTrigger.setRepeatInterval(1000);
            // 运行次数
            simpleTrigger.setRepeatCount(1);

            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, simpleTrigger);
            // 启动调度
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    /**
     * 指定时间规则，定时调度
     */
    public void test2() {
        //通过schedulerFactory获取一个调度
        SchedulerFactory schedulerfactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            // 通过schedulerFactory获取一个调度器
            scheduler = schedulerfactory.getScheduler();

            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            JobDetail jobDetail = new JobDetail("job1", "jgroup1", WeatherForecastJob.class);

            // 定义调度触发规则，每天上午6：55执行
            CronTrigger cornTrigger = new CronTrigger("cronTrigger", "triggerGroup");
            // 执行规则表达式
            cornTrigger.setCronExpression("0 55 6 * * ? *");
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, cornTrigger);

            // 启动调度
            scheduler.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
