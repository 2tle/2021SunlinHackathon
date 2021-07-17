package com.example.a2021sunlinhackathon.Data;

public class BookListData {
    public String plantUrl1;
    public String plantText1;

    public String plantUrl2;
    public String plantText2;

    public String plantUrl3;
    public String plantText3;


    public BookListData() {

    }
    public BookListData(BookListData d) {
        this.plantText1 = d.plantText1;
        this.plantText2 = d.plantText2;
        this.plantText3 = d.plantText3;

        this.plantUrl1 = d.plantUrl1;
        this.plantUrl2 = d.plantUrl2;
        this.plantUrl3 = d.plantUrl3;
    }

    public String getPlantUrl1() {
        return plantUrl1;
    }

    public void setPlantUrl1(String plantUrl1) {
        this.plantUrl1 = plantUrl1;
    }

    public String getPlantText1() {
        return plantText1;
    }

    public void setPlantText1(String plantText1) {
        this.plantText1 = plantText1;
    }

    public String getPlantUrl2() {
        return plantUrl2;
    }

    public void setPlantUrl2(String plantUrl2) {
        this.plantUrl2 = plantUrl2;
    }

    public String getPlantText2() {
        return plantText2;
    }

    public void setPlantText2(String plantText2) {
        this.plantText2 = plantText2;
    }

    public String getPlantUrl3() {
        return plantUrl3;
    }

    public void setPlantUrl3(String plantUrl3) {
        this.plantUrl3 = plantUrl3;
    }

    public String getPlantText3() {
        return plantText3;
    }

    public void setPlantText3(String plantText3) {
        this.plantText3 = plantText3;
    }
}
