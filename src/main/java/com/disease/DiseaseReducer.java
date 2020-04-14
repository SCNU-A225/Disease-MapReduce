package com.disease;

import com.bean.DiseaseBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DiseaseReducer extends Reducer<Text, DiseaseBean, Text, DiseaseBean> {
    @Override
    protected void reduce(Text key, Iterable<DiseaseBean> values, Context context) throws IOException, InterruptedException {
        for (DiseaseBean bean : values){
            context.write(key,bean);
        }
    }
}
