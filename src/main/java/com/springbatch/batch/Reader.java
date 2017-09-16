package com.springbatch.batch;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reader implements ItemReader<Map<String,Object>> {

    @Setter
    @Getter
    @Value("#{jobParameters['count']}")
    private String JOD_NAME;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Setter
    @Getter
    private Long index = 0L;

    @Override
    public Map<String, Object> read() throws Exception{

        //判断有没有这个JOB
        if(StringUtils.isEmpty(JOD_NAME)) {
            return null;
        }

        List<String> results = objectMapper.readValue(JOD_NAME,List.class);
        for(;index < results.size();) {
            Map<String,Object> map = new HashMap<String,Object>();
            String duck = results.get(index.intValue());
            System.out.println("============ 捉到了一只 ================= 贴个标签"+duck);
            map.put("duck",duck);
            index++;
            return map;
        }

        return null;
    }
}
