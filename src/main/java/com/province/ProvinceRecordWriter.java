package com.province;

import com.bean.ProvinceBean;
import com.utils.JDBCUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProvinceRecordWriter extends RecordWriter<Text, ProvinceBean> {

    private Connection conn;
    private PreparedStatement preparedStatement;

    public ProvinceRecordWriter(TaskAttemptContext job) {
        conn = JDBCUtils.getConnection();
        String sql = "INSERT INTO provinces(province,sum,cured,dead) VALUES(?,?,?,?)";
        try {
            preparedStatement = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Text text, ProvinceBean provinceBean) throws IOException, InterruptedException {
        try {
            preparedStatement.setString(1,provinceBean.getProvince());
            preparedStatement.setInt(2,provinceBean.getSum());
            preparedStatement.setInt(3,provinceBean.getCured());
            preparedStatement.setInt(4,provinceBean.getDead());
            preparedStatement.executeUpdate();
            preparedStatement.clearParameters();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        JDBCUtils.release(preparedStatement,conn);
    }
}
