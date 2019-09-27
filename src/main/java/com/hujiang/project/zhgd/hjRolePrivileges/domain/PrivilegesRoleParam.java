package com.hujiang.project.zhgd.hjRolePrivileges.domain;

public class PrivilegesRoleParam {


    private Integer roleId;

    /** ID */
    private Integer id;
    /** 权限名称 */
    private String privilegesName;
    /** 请求路径 */
    private String url;
    /** 权限类型（0菜单，1目录，2按钮） */
    private Integer privilegesType;
    /** 是否可见（0.显示  1.隐藏） */
    private Integer whetherOrNotVisible;
    /** 父级ID（1级为0） */
    private Integer parentId;
    /** app或者pc权限菜单 */
    private Integer appOrPc;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrivilegesName() {
        return privilegesName;
    }

    public void setPrivilegesName(String privilegesName) {
        this.privilegesName = privilegesName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPrivilegesType() {
        return privilegesType;
    }

    public void setPrivilegesType(Integer privilegesType) {
        this.privilegesType = privilegesType;
    }

    public Integer getWhetherOrNotVisible() {
        return whetherOrNotVisible;
    }

    public void setWhetherOrNotVisible(Integer whetherOrNotVisible) {
        this.whetherOrNotVisible = whetherOrNotVisible;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getAppOrPc() {
        return appOrPc;
    }

    public void setAppOrPc(Integer appOrPc) {
        this.appOrPc = appOrPc;
    }
}
