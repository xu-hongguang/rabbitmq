package com.xhg.rabbitmq.pojo;

import java.io.Serializable;
import java.util.Date;

public class Information implements Serializable {

    private Integer id;
    private String infoNo;
    private String description;
    private Date createDate;

    public Information() {
    }

    public Information(Integer id, String infoNo, String description, Date createDate) {
        this.id = id;
        this.infoNo = infoNo;
        this.description = description;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", infoNo='" + infoNo + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfoNo() {
        return infoNo;
    }

    public void setInfoNo(String infoNo) {
        this.infoNo = infoNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
