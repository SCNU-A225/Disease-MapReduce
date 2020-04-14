package com.bean;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DiseaseBean implements Writable {
    private String name;            //病名
    private String alias;           //别名
    private String part;            //发病部位
    private String age;             //患者常见年龄
    private String infection;       //传染性
    private String insurance;       //是否为医保疾病
    private String department;      //就诊部门
    private String checklist;       //检查项目
    private String symptom;         //症状
    private String complication;    //并发症
    private String treatment;       //治疗方式
    private String drug;            //相关药物
    private String period;          //治疗周期
    private String rate;            //治愈率
    private String money;           //治疗费用

    public DiseaseBean(){
        super();
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(name);
        dataOutput.writeUTF(alias);
        dataOutput.writeUTF(part);
        dataOutput.writeUTF(age);
        dataOutput.writeUTF(infection);
        dataOutput.writeUTF(insurance);
        dataOutput.writeUTF(department);
        dataOutput.writeUTF(checklist);
        dataOutput.writeUTF(symptom);
        dataOutput.writeUTF(complication);
        dataOutput.writeUTF(treatment);
        dataOutput.writeUTF(drug);
        dataOutput.writeUTF(period);
        dataOutput.writeUTF(rate);
        dataOutput.writeUTF(money);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.name = dataInput.readUTF();
        this.alias = dataInput.readUTF();
        this.part = dataInput.readUTF();
        this.age = dataInput.readUTF();
        this.infection = dataInput.readUTF();
        this.insurance = dataInput.readUTF();
        this.department = dataInput.readUTF();
        this.checklist = dataInput.readUTF();
        this.symptom = dataInput.readUTF();
        this.complication = dataInput.readUTF();
        this.treatment = dataInput.readUTF();
        this.drug = dataInput.readUTF();
        this.period = dataInput.readUTF();
        this.rate = dataInput.readUTF();
        this.money = dataInput.readUTF();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getInfection() {
        return infection;
    }

    public void setInfection(String infection) {
        this.infection = infection;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getChecklist() {
        return checklist;
    }

    public void setChecklist(String checklist) {
        this.checklist = checklist;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getComplication() {
        return complication;
    }

    public void setComplication(String complication) {
        this.complication = complication;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "DiseaseBean{" +
                "name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", part='" + part + '\'' +
                ", age='" + age + '\'' +
                ", infection='" + infection + '\'' +
                ", insurance='" + insurance + '\'' +
                ", department='" + department + '\'' +
                ", checklist='" + checklist + '\'' +
                ", symptom='" + symptom + '\'' +
                ", complication='" + complication + '\'' +
                ", treatment='" + treatment + '\'' +
                ", drug='" + drug + '\'' +
                ", period='" + period + '\'' +
                ", rate='" + rate + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
