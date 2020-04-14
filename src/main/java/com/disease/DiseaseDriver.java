package com.disease;

import com.bean.DayCountBean;
import com.bean.DiseaseBean;
import com.daycount.DayCountDriver;
import com.daycount.DayCountMapper;
import com.daycount.DayCountOutputFormat;
import com.daycount.DayCountReducer;
import com.utils.JDBCUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DiseaseDriver {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{"E:/hadoopTestFile/disease.csv","E:/hadoopTestFile/output"};
        //执行前清空表
        Connection conn = JDBCUtils.getConnection();
        String sql = "TRUNCATE TABLE diseasetable";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        JDBCUtils.release(preparedStatement,conn);

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(DiseaseDriver.class);
        job.setMapperClass(DiseaseMapper.class);
        job.setReducerClass(DiseaseReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DiseaseBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DiseaseBean.class);

        job.setOutputFormatClass(DiseaseOutputFormat.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        boolean result = job.waitForCompletion(true);
    }
}
