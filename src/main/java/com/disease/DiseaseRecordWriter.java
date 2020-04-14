package com.disease;

import com.bean.DiseaseBean;
import com.utils.JDBCUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DiseaseRecordWriter extends RecordWriter<Text, DiseaseBean> {
    private Connection conn;
    private PreparedStatement preparedStatement;
    public DiseaseRecordWriter(TaskAttemptContext taskAttemptContext) {
        conn = JDBCUtils.getConnection();
        String sql = "INSERT INTO diseasetable(name,alias,part,age,infection,insurance,department,checklist,symptom,complication,treatment,drug,period,rate,money) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            preparedStatement = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Text text, DiseaseBean diseaseBean) throws IOException, InterruptedException {
        //name,alias,part,age,infection,insurance,department,checklist,symptom,complication,treatment,drug,period,rate,money
        try {
            preparedStatement.setString(1,diseaseBean.getName());
            preparedStatement.setString(2,diseaseBean.getAlias());
            preparedStatement.setString(3,diseaseBean.getPart());
            preparedStatement.setString(4,diseaseBean.getAge());
            preparedStatement.setString(5,diseaseBean.getInfection());
            preparedStatement.setString(6,diseaseBean.getInsurance());
            preparedStatement.setString(7,diseaseBean.getDepartment());
            preparedStatement.setString(8,diseaseBean.getChecklist());
            preparedStatement.setString(9,diseaseBean.getSymptom());
            preparedStatement.setString(10,diseaseBean.getComplication());
            preparedStatement.setString(11,diseaseBean.getTreatment());
            preparedStatement.setString(12,diseaseBean.getDrug());
            preparedStatement.setString(13,diseaseBean.getPeriod());
            preparedStatement.setString(14,diseaseBean.getRate());
            preparedStatement.setString(15,diseaseBean.getMoney());
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
