package com.devpro.javaweb.dto;

import com.devpro.javaweb.model.BaseEntity;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity()
public class OrderDetail extends BaseEntity {
    private String title;
    private BigDecimal price;
    private BigDecimal priceSale;
    private Integer quality;
    private Date createdDate;
    private String code;
    private String avatar;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(BigDecimal priceSale) {
        this.priceSale = priceSale;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
