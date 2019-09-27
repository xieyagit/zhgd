package com.hujiang.project.zhgd.hjTeam.domain;

public class TeamParam {

    /** ID */
    private Integer id;
    /** 班组名称 */
    private String teamName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "TeamParam{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
