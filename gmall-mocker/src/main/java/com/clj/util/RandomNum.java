package com.clj.util;

import java.util.Random;

/**
 * @author chen
 * @topic
 * @create 2020-11-03
 */
public class RandomNum {
    public static int getRandInt(int fromNum,int toNum){
        return fromNum + new Random().nextInt(toNum-fromNum+1);
    }
}

