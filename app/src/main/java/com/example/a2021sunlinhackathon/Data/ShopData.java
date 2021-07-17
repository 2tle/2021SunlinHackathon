package com.example.a2021sunlinhackathon.Data;

public class ShopData {
    public String leftImageUrl;
    public String leftShopId;

    public String rightImageUrl;
    public String rightShopId;

    public ShopData(String leftImageUrl, String leftShopId, String rightImageUrl, String rightShopId) {
        this.leftImageUrl = leftImageUrl;
        this.leftShopId = leftShopId;
        this.rightImageUrl = rightImageUrl;
        this.rightShopId = rightShopId;
    }

    public String getLeftImageUrl() {
        return leftImageUrl;
    }

    public String getLeftShopId() {
        return leftShopId;
    }

    public String getRightImageUrl() {
        return rightImageUrl;
    }

    public String getRightShopId() {
        return rightShopId;
    }

    public void setLeftImageUrl(String leftImageUrl) {
        this.leftImageUrl = leftImageUrl;
    }

    public void setLeftShopId(String leftShopId) {
        this.leftShopId = leftShopId;
    }

    public void setRightImageUrl(String rightImageUrl) {
        this.rightImageUrl = rightImageUrl;
    }

    public void setRightShopId(String rightShopId) {
        this.rightShopId = rightShopId;
    }
}
