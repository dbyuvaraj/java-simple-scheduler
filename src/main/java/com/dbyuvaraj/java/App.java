package com.dbyuvaraj.java;

import com.dbyuvaraj.java.scheduler.JobScheduler;

public class App {
    public static void main(String[] args) {
        JobScheduler jobScheduler = new JobScheduler();
        jobScheduler.triggerJob();
    }
}
