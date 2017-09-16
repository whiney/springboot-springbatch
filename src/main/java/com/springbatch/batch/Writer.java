package com.springbatch.batch;


import com.springbatch.domain.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import java.util.List;
import java.util.Map;


public class Writer implements ItemWriter<Map<String, Object>> {

    public static final Logger LOG = LoggerFactory.getLogger(Writer.class);


    @Override
    public void write(List<? extends Map<String, Object>> outputs) throws Exception {

        for (Map<String, Object> output : outputs) {

            String duck = output.get(Constant.DUCK).toString();
            System.out.println(duck + "，我来了。。");

        }

    }






}
