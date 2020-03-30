package com.hujiang.project.zhgd.hjEpidemicSituation.domain;

/**
 * @ClassName EpidemicSituation
 * @Description 疫情城市表 hj_epidemic_situation
 * @Author xieya
 * @Date 2020/3/23  18:14
 */
public class EpidemicSituation {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;
    /**
     * 名称
     */
    private String title;
    /**
     * 类型
     */
    private String type;
    /**
     * 上级id
     */
    private Integer parentId;

    /**项目id*/
    private String pid;

    /**开关  0打开   1关闭*/
    private Integer enter;

    /**城市id*/
    private Integer areaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    @Override
    public String toString() {
        return "EpidemicSituation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", parentId=" + parentId +
                ", pid='" + pid + '\'' +
                ", enter=" + enter +
                ", areaId=" + areaId +
                '}';
    }
}