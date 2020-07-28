package com.dbyuvaraj.java.job;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.IOException;

@Slf4j
public class CatFactsApiRequestJob implements Job {

    public CatFactsApiRequestJob() {
        log.info("Job Created!");
    }

    @Override
    public void execute(JobExecutionContext jobContext) {
        try {
            log.info("Calling Cat Facts API");
            Content content = Request.Get("https://cat-fact.herokuapp.com/facts")
                    .execute().returnContent();
            log.info("The response from API");
            String responseString = content.asString();
            log.info(responseString.substring(0, 100));
        } catch (IOException e) {
            log.error("Error while retrieving data from api ", e);
        }
    }

}
