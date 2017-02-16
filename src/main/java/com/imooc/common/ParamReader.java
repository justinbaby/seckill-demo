package com.imooc.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ParamReader {

    @Autowired
    Environment environment;

    public String getEnvParam(String key){
        return environment.getProperty(key);
    }

    public String[] getEnvParamList(String key){
        String listStr = environment.getProperty(key);
        if (listStr.contains(",")){
            return listStr.split(",");
        }else {
            return new String[]{listStr};
        }
    }

}
