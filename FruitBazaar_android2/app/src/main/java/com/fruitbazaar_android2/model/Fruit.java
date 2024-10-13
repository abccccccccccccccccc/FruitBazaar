package com.fruitbazaar_android2.model;

public class Fruit {
    private int id;
    private String code;
    private String name;
    private Long fruitTypeId;
    private Double oldPrice;
    private Double newPrice;
    private String origin;
    private String desctext;
    private String level;
    private String img;
    private Integer status;
    private Long unitId;
    private String remark;
    private Long creatorId;
    private String createdAt;
    private Long modifierId;
    private int quantity;
    private boolean selected;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFruitTypeId() {
        return fruitTypeId;
    }

    public void setFruitTypeId(Long fruitTypeId) {
        this.fruitTypeId = fruitTypeId;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDesctext() {
        return desctext;
    }

    public void setDesctext(String desctext) {
        this.desctext = desctext;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", fruitTypeId=" + fruitTypeId +
                ", oldPrice=" + oldPrice +
                ", newPrice=" + newPrice +
                ", origin='" + origin + '\'' +
                ", desctext='" + desctext + '\'' +
                ", level='" + level + '\'' +
                ", img='" + img + '\'' +
                ", status=" + status +
                ", unitId=" + unitId +
                ", remark='" + remark + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt='" + createdAt + '\'' +
                ", modifierId=" + modifierId +
                '}';
    }
}

