package com.springbatch.batch;

import com.springbatch.domain.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import java.util.HashMap;
import java.util.Map;


public class Processor implements ItemProcessor<Map<String,Object>, Map<String,Object>> {

    private static final Logger logger = LoggerFactory.getLogger(Processor.class);

    @Override
    public Map<String, Object> process(Map<String, Object> input) throws Exception {
        logger.info("spring batch 参数处理");
        String duck = input.get(Constant.DUCK).toString();
        Map<String,Object> output = new HashMap<String,Object>();
        output.put(Constant.DUCK,duck+" ，我被煮熟了");
        return output;
    }
}
