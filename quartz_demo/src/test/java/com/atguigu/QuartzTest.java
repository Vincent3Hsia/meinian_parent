package com.atguigu;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {
@Test
    public void t1(){
            /*try {
                //定义一个JobDetail
                JobDetail jobDetail = JobBuilder.newJob(HelloQuartz.class)

                        .build();
                //定义一个Trigger  触发器(定义调用的事件规则)
                Trigger trigger = TriggerBuilder.newTrigger()
                        //加入 scheduler之后立刻执行 立刻启动
                        .startNow()
                        //定时 ，每隔1秒钟执行一次 以某种触发器触发。
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1)
                                //重复执行
                                .repeatForever()).build();
                //创建scheduler 调度的容器(管理触发器和任务调度器)
                Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.scheduleJob(jobDetail, trigger);
                // Scheduler只有在调用start()方法后，才会真正地触发trigger（即执行job）
                scheduler.start(); //运行一段时间后关闭
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Scheduler调用shutdown()方法时结束
                scheduler.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }*/

    }
}
