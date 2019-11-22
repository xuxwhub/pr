package com.dunshan.biz.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_user")
public class User extends DynamicTableEntity implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    /**
     * 员工号
     */
    @Column(name = "user_number")
    private String userNumber;

    /**
     * 姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 所属机构ID
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取员工号
     *
     * @return user_number - 员工号
     */
    public String getUserNumber() {
        return userNumber;
    }

    /**
     * 设置员工号
     *
     * @param userNumber 员工号
     */
    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    /**
     * 获取姓名
     *
     * @return user_name - 姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置姓名
     *
     * @param userName 姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取所属机构ID
     *
     * @return org_id - 所属机构ID
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置所属机构ID
     *
     * @param orgId 所属机构ID
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取手机
     *
     * @return mobile - 手机
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机
     *
     * @param mobile 手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
            "id='" + id + '\'' +
            ", userNumber='" + userNumber + '\'' +
            ", userName='" + userName + '\'' +
            ", orgId='" + orgId + '\'' +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", createTime=" + createTime +
            "} " + super.toString();
    }
}