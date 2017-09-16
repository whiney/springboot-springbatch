package com.springbatch.config;

import com.springbatch.batch.Processor;
import com.springbatch.batch.Reader;
import com.springbatch.batch.Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by baishuai on 2017/9/5.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    @StepScope
    public ItemReader<Map<String, Object>> Reader() throws Exception {
        return new Reader();
    }

    @Bean
    @StepScope
    public ItemProcessor<Map<String, Object>, Map<String, Object>> Processor() {
        return new Processor();
    }

    @Bean
    @StepScope
    public ItemWriter<Map<String, Object>> Writer() {
        return new Writer();
    }

    @Bean
    public Job generateExcel() throws Exception {
        return jobBuilderFactory.get("generateExcel").incrementer(new RunIdIncrementer()).start(step()).build();
    }

    @Bean
    public Step step() throws Exception {
        return stepBuilderFactory.get("finished")
                .<Map<String, Object>, Map<String, Object>>chunk(20)
                .reader(Reader())
                .processor(Processor())
                .writer(Writer()).readerIsTransactionalQueue()
                .build();
    }

}
