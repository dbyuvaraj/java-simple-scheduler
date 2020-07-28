package com.dbyuvaraj.java.scheduler;

import com.dbyuvaraj.java.job.CatFactsApiRequestJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

@Slf4j
public class JobScheduler {

    public void triggerJob() {
        try {
            log.info("Creating the scheduler");
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            JobBuilder jobBuilder = JobBuilder.newJob(CatFactsApiRequestJob.class);
            JobDetail jobDetail = jobBuilder.build();

            log.info("Creating Trigger");
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("apiJobTrigger", "apiJobs")
                    .startNow()
//                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * * * ?"))
                    .withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(5))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("Exception while processing", e);
        }
    }

}
