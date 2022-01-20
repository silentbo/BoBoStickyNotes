package com.bobo.sticky.notes.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author bobo
 * @date 2022/1/15 20:37
 */
public class LogBoBo {

    private final static Map<Class, Logger> loggerMap = new HashMap<>();

    public static void info(Class cla, String info){
        // 已有对应类
        if (loggerMap.containsKey(cla)){
            loggerMap.get(cla).info(info);
        } else {
            Logger logger = Logger.getLogger(cla.getName());
            logger.info(info);
            loggerMap.put(cla, logger);
        }
    }

}
