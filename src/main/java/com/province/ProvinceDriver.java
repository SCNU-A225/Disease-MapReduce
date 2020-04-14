package com.province;

import com.bean.ProvinceBean;
import com.utils.JDBCUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProvinceDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, SQLException {

        args = new String[]{"E:/hadoopTestFile/province.csv","E:/hadoopTestFile/output"};

        //执行前清空表
        Connection conn = JDBCUtils.getConnection();
        String sql = "TRUNCATE TABLE provinces";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        JDBCUtils.release(preparedStatement,conn);

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(ProvinceDriver.class);
        job.setMapperClass(ProvinceMapper.class);
        job.setReducerClass(ProvinceReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(ProvinceBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(ProvinceBean.class);

        job.setOutputFormatClass(ProvinceOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        boolean result = job.waitForCompletion(true);
    }
}
