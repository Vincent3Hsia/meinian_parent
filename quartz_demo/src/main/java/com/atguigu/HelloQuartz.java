package com.atguigu;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import java.util.Date;

public class HelloQuartz implements Job {
    //具体的任务(调用的功能)
    public void execute(JobExecutionContext jobExecutionContext) {

        //System.out.println( new Date());
    }
}
