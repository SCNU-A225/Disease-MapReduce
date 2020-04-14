package com.daycount;

import com.bean.DayCountBean;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DayCountReducer extends Reducer<IntWritable, DayCountBean, IntWritable, DayCountBean> {
    @Override
    protected void reduce(IntWritable key, Iterable<DayCountBean> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        int cured = 0;
        int dead = 0;

        for (DayCountBean bean : values){
            sum += bean.getSum();
            cured += bean.getCured();
            dead += bean.getDead();
        }

        DayCountBean v = new DayCountBean(key.get(),sum,cured,dead);

        context.write(key,v);
    }
}
