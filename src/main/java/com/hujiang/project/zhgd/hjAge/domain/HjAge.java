package com.hujiang.project.zhgd.hjAge.domain;

/**
 * @ClassName HjAge
 * @Description 年龄设置实体类
 * @Author xieya
 * @Date 2020/3/24  11:13
 */
public class HjAge {

    /**id*/
    private Integer id;
    /**年龄*/
    private Integer age;
    /**项目id*/
    private String pid;
    /**禁止入内的开关，0是关闭1是打开*/
    private Integer enter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getEnter() {
        return enter;
    }

    public void setEnter(Integer enter) {
        this.enter = enter;
    }

    @Override
    public String toString() {
        return "HjAge{" +
                "id=" + id +
                ", age=" + age +
                ", pid='" + pid + '\'' +
                ", enter=" + enter +
                '}';
    }
}