package com.disease;

import com.bean.DiseaseBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class DiseaseOutputFormat extends FileOutputFormat<Text, DiseaseBean> {

    @Override
    public RecordWriter<Text, DiseaseBean> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        return new DiseaseRecordWriter(taskAttemptContext);
    }
}
