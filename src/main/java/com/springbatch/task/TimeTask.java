package com.springbatch.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * Created by baishuai on 2017/9/5.
 */
@Component
@EnableScheduling
public class TimeTask {


    public final static String DATE = "date";
    public final static String JOD_NAME = "count";

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired()
    private Job job;

    private ObjectMapper objectMapper = new ObjectMapper();

    // 每1分钟执行一次 0 */1 *  * * *
    @Scheduled(cron = "0/30 * * * * ?")
    public void generateFile()  throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException, IOException {

        System.out.println("烤箱已就绪，开始添加鸭子"+new Date());

        //模拟数据
        List<String> params = new ArrayList<String>(){{
            add("鸭子1号");
            add("鸭子2号");
        }};

        JobParameters param = new JobParametersBuilder().addDate(DATE, new Date()).
                addString(JOD_NAME, objectMapper.writeValueAsString(params)).toJobParameters();
        jobLauncher.run(job, param);

    }

}
