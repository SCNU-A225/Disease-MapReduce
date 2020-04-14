package com.daycount;

import com.bean.DayCountBean;
import com.utils.JDBCUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DayCountDriver {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException, InterruptedException {

//        args = new String[]{"E:/hadoopTestFile/country.csv","E:/hadoopTestFile/output"};
        //执行前清空表
        Connection conn = JDBCUtils.getConnection();
        String sql = "TRUNCATE TABLE daycount";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        JDBCUtils.release(preparedStatement,conn);

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(DayCountDriver.class);
        job.setMapperClass(DayCountMapper.class);
        job.setReducerClass(DayCountReducer.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(DayCountBean.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(DayCountBean.class);

        job.setOutputFormatClass(DayCountOutputFormat.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        boolean result = job.waitForCompletion(true);
    }
}
