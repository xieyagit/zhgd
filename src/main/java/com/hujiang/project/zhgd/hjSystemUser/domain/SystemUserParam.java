package com.hujiang.project.zhgd.hjSystemUser.domain;

public class SystemUserParam {


    /** ID */
    private Integer id;
    /** 名称 */
    private String userName;
    /** 联系电话 */
    private String userPhone;
    /** 账户 */
    private String userAccount;
    /** 密码 */
    private String userPassword;
    /** 账户状态（0：禁用  1：启用） */
    private String userState;
    /** 账户类型（0.集团，1：公司  2：项目） */
    private Integer userType;
    /** 登录项（0 只登录app，1 pc 2.pc+app） */
    private Integer entry;
    /** 创建者 id */
    private String creator;
    /** 创建时间 */
    private String createDate;
    /** 修改时间 */
    private String updateDate;
    /** 创建者id */
    private Integer publicId;
    /** 角色 id */
    private String ids;
    // 权限
    private String privilegesId;
    // 项目或者公司，集团 id
    private Integer projectOrCompanyId;


    public String getPrivilegesId() {
        return privilegesId;
    }

    public void setPrivilegesId(String privilegesId) {
        this.privilegesId = privilegesId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getEntry() {
        return entry;
    }

    public void setEntry(Integer entry) {
        this.entry = entry;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getPublicId() {
        return publicId;
    }

    public void setPublicId(Integer publicId) {
        this.publicId = publicId;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Integer getProjectOrCompanyId() {
        return projectOrCompanyId;
    }

    public void setProjectOrCompanyId(Integer projectOrCompanyId) {
        this.projectOrCompanyId = projectOrCompanyId;
    }

    @Override
    public String toString() {
        return "SystemUserParam{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userState='" + userState + '\'' +
                ", userType=" + userType +
                ", entry=" + entry +
                ", creator='" + creator + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", publicId=" + publicId +
                ", ids='" + ids + '\'' +
                ", privilegesId='" + privilegesId + '\'' +
                ", projectOrCompanyId=" + projectOrCompanyId +
                '}';
    }
}
