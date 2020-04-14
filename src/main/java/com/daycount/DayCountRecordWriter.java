package com.daycount;

import com.bean.DayCountBean;
import com.utils.JDBCUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DayCountRecordWriter extends RecordWriter<IntWritable, DayCountBean> {

    private Connection conn;
    private PreparedStatement preparedStatement;

    public DayCountRecordWriter(TaskAttemptContext taskAttemptContext) {
        conn = JDBCUtils.getConnection();
        String sql = "INSERT INTO daycount VALUES(?,?,?,?)";
        try {
            preparedStatement = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(IntWritable intWritable, DayCountBean dayCountBean) throws IOException, InterruptedException {
        try {
            preparedStatement.setInt(1,dayCountBean.getDay());
            preparedStatement.setInt(2,dayCountBean.getSum());
            preparedStatement.setInt(3,dayCountBean.getCured());
            preparedStatement.setInt(4,dayCountBean.getDead());
            preparedStatement.executeUpdate();
            preparedStatement.clearParameters();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        JDBCUtils.release(preparedStatement,conn);
    }
}
