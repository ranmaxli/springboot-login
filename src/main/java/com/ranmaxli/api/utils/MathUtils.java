package com.ranmaxli.api.utils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by liran on 2017/2/20.
 */
public class MathUtils {
    //平均值
    public static double getAverage(List<Double> scores){
        double sum = 0;
        for(Double score:scores){
            sum += score;
        }
        return (double)(sum / scores.size());
    }

    //计算标准差
    public static double getStandardDevition(List<Double> scores){
        return Math.sqrt(Math.abs(getVariance(scores)));
    }

    //计算方差
    public static double getVariance(List<Double> scores) {
        int count = scores.size();
        double sqrsum = getSquareSum(scores);
        double average = getAverage(scores);
        double result;
        result = (sqrsum - count * average * average) / count;
        return result;
    }

    /**
     * 求给定双精度数组中值的平方和
     *
     * @param scores
     * 输入数据数组
     * @return 运算结果
     */
    public static double getSquareSum(List<Double> scores) {
        if (scores == null || scores.size() == 0)
            return -1;
        double sqrsum = 0.0;
        for (Double score : scores) {
            sqrsum = sqrsum + score * score;
        }
        return sqrsum;
    }

    /**
     * 保留小数位数
     * @param num  原数
     * @param length
     * @return
     */
    public static double round(Double value,int pointNum)
    {
        if(value==null)
            return 0;
        
        if (pointNum < 0)
        {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(value));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, pointNum, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
