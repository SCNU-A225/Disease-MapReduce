package com.daycount;

import com.bean.DayCountBean;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DayCountMapper extends Mapper<LongWritable, Text, IntWritable, DayCountBean> {

    private DayCountBean v = new DayCountBean();
    private IntWritable k = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 获取行
        String line = value.toString();
        // 切割字段
        String[] fields = line.trim().split(",");
        // 封装对象
        //date,province_code,province,city_code,city,confirmed,suspected,cured,dead
        //20191201,420000,湖北省,420100,武汉市,1,0,0,0
        int day = Integer.parseInt(fields[0]);
        int sum = Integer.parseInt(fields[5]);
        int cured = Integer.parseInt(fields[7]);
        int dead = Integer.parseInt(fields[8]);

        k.set(day);
        v.setDay(day);
        v.setSum(sum);
        v.setCured(cured);
        v.setDead(dead);

        context.write(k,v);
    }
}
