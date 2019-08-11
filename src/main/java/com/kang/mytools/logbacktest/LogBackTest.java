package com.kang.mytools.logbacktest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * slf4j与logback日志框架
 * 1、配置logback.xml放在classpath下且文件名不可变
 * 2、Logger log = LoggerFactory.getLogger(this.class);实例化使用
 */
public class LogBackTest {

    //获取日志类
    static Logger log = LoggerFactory.getLogger(LogBackTest.class);

    public static void main(String[] args){
        System.out.println();
        log.debug("-------------------debug----------------------");
        log.debug("print----{}", 3);//{}为占位符，3为其赋值
        for (int i = 0; i < 3; i++){
            System.out.println(i);
        }
        log.info("-------------------info----------------------");
        String str = "A" + "B" + "C";
        System.out.println(str);
        log.warn("-------------------warn----------------------");
        System.out.println("测试");
        log.error("-------------------error--------------------");
    }
}
