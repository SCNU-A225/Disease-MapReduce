package com.disease;

import com.bean.DiseaseBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiseaseMapper extends Mapper<LongWritable, Text, Text, DiseaseBean> {

    Text k = new Text();
    DiseaseBean v = new DiseaseBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //name,alias,part,age,infection,insurance,department,checklist,symptom,complication,treatment,drug,period,rate,money
        String line = value.toString();
//        String[] fields = line.split(",",-1);
        List<String> fields = mySplit(line);
        k.set(fields.get(0));
        v.setName(fields.get(0));
        v.setAlias(fields.get(1));
        v.setPart(fields.get(2));
        v.setAge(fields.get(3));
        v.setInfection(fields.get(4));
        v.setInsurance(fields.get(5));
        v.setDepartment(fields.get(6));
        v.setChecklist(fields.get(7));
        v.setSymptom(fields.get(8));
        v.setComplication(fields.get(9));
        v.setTreatment(fields.get(10));
        v.setDrug(fields.get(11));
        v.setPeriod(fields.get(12));
        v.setRate(fields.get(13));
        v.setMoney(fields.get(14));
        context.write(k,v);
    }

    private List<String> mySplit(String s){
        List<String> strings = new ArrayList<>();
        int pre = 0;
        int i = 0;
        while (i < s.length()){
            char c = s.charAt(i);
            if (c == ','){
                if (i+1 < s.length() && s.charAt(i+1) == '"'){
                    i = i + 2;
                    while (i < s.length() && s.charAt(i) != '"'){
                        i++;
                    }
                    i++;
                    String string = s.substring(pre,i);
                    strings.add(string);
                    pre = i + 1;
                }
                else{
                    String string = s.substring(pre,i);
                    strings.add(string);
                    pre = i + 1;
                }
            }
            i++;
        }
        return strings;
    }
}
