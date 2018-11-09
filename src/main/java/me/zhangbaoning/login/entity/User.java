package me.zhangbaoning.login.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author: zhangbaoning
 * @date: 2018/11/8
 * @since: JDK 1.8
 * @description: TODO
 */
@Entity
public class User {
    private long id;
    private String username;
    private String idCard;
    private Integer openid;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "id_card")
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "openid")
    public Integer getOpenid() {
        return openid;
    }

    public void setOpenid(Integer openid) {
        this.openid = openid;
    }

    @Basic
    @Column(name = "gmt_create")
    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Basic
    @Column(name = "gmt_modified")
    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (idCard != null ? !idCard.equals(user.idCard) : user.idCard != null) return false;
        if (openid != null ? !openid.equals(user.openid) : user.openid != null) return false;
        if (gmtCreate != null ? !gmtCreate.equals(user.gmtCreate) : user.gmtCreate != null) return false;
        if (gmtModified != null ? !gmtModified.equals(user.gmtModified) : user.gmtModified != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (openid != null ? openid.hashCode() : 0);
        result = 31 * result + (gmtCreate != null ? gmtCreate.hashCode() : 0);
        result = 31 * result + (gmtModified != null ? gmtModified.hashCode() : 0);
        return result;
    }
}
