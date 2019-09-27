package com.hujiang.project.zhgd.hjSystemPrivileges.domain;

public class SystemRoleParam {


    private Integer userId;

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
    /** app、pc后台、pc公司、pc项目、pc看板权限菜单（app 0,pc后台1，pc公司 ,2, pc项目3、pc看板 4）
     *  */
    private Integer appOrPc;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
