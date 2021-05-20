package com.chinaliyq.designpatterns.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/19 - 14:58
 * @Version: 1.0
 **/
public class TestLoger {
    public static void main(String[] args) {
        Logger log = Logger.getLogger("lavasoft");
        log.setLevel(Level.INFO);
        Logger log1 = Logger.getLogger("lavasoft");
        System.out.println(log==log1);     //true
        Logger log2 = Logger.getLogger("lavasoft.blog");
//        log2.setLevel(Level.WARNING);

        log.info("aaa");
        log2.info("bbb");
        log2.fine("fine");
    }

}
