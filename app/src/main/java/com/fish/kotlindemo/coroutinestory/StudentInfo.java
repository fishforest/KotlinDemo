package com.fish.kotlindemo.coroutinestory;

public class StudentInfo {
    //学生id
    private long stuId = 999;
    private String name = "fish";
    private int age = 18;

    //关联的语文老师 id
    private long lanTechId = 888;

    public long getStuId() {
        return stuId;
    }

    public void setStuId(long stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getLanTechId() {
        return lanTechId;
    }

    public void setLanTechId(long lanTechId) {
        this.lanTechId = lanTechId;
    }
}
