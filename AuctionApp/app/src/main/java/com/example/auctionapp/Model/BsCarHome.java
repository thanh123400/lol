package com.example.auctionapp.Model;

public class BsCarHome {

    //Thông số của một biển số trên toàn hệ thống, phân biệt bằng id, và đã đấu giá hay chưa thông qua Cost
    private String id;

    private String nameBs;
    private String province;
    private String typeCar;
    private int countFavourite;
    private String expBs;
    private String dateAuction;
    private String timeAuctionStart;
    private String timeAuctionEnd;
    private String Cost;
    private PdfImage image;

    public PdfImage getImage() {
        return image;
    }

    public void setImage(PdfImage image) {
        this.image = image;
    }



    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getDateAuction() {
        return dateAuction;
    }

    public void setDateAuction(String dateAuction) {
        this.dateAuction = dateAuction;
    }

    public String getTimeAuctionStart() {
        return timeAuctionStart;
    }

    public void setTimeAuctionStart(String timeAuctionStart) {
        this.timeAuctionStart = timeAuctionStart;
    }

    public String getTimeAuctionEnd() {
        return timeAuctionEnd;
    }

    public void setTimeAuctionEnd(String timeAuctionEnd) {
        this.timeAuctionEnd = timeAuctionEnd;
    }

    public BsCarHome() {

    }
    //test
    public BsCarHome(String nameBs, String province, String typeCar, int countFavourite) {
        this.nameBs = nameBs;
        this.province = province;
        this.typeCar = typeCar;
        this.countFavourite = countFavourite;
    }
    public BsCarHome(String nameBs, String province, String typeCar, String dateAuction, String timeAuctionStart, String timeAuctionEnd) {
        this.nameBs = nameBs;
        this.province = province;
        this.typeCar = typeCar;
        this.dateAuction = dateAuction;
        this.timeAuctionStart = timeAuctionStart;
        this.timeAuctionEnd = timeAuctionEnd;
    }
    public BsCarHome(String nameBs, String province, String typeCar, String Cost, String dateAuction) {
        this.nameBs = nameBs;
        this.province = province;
        this.typeCar = typeCar;
        this.Cost = Cost;
        this.dateAuction = dateAuction;
    }
    public BsCarHome(String dateAuction, String timeAuctionStart, String timeAuctionEnd, String Cost) {
        this.dateAuction = dateAuction;
        this.timeAuctionStart = timeAuctionStart;
        this.timeAuctionEnd = timeAuctionEnd;
        this.Cost = Cost;
    }

    public BsCarHome(PdfImage image, String nameBs, String dateAuction, String timeAuctionStart, String timeAuctionEnd, int countFavourite, String typeCar, String province) {
        this.image = image;
        this.nameBs = nameBs;
        this.dateAuction = dateAuction;
        this.timeAuctionStart = timeAuctionStart;
        this.timeAuctionEnd = timeAuctionEnd;
        this.countFavourite = countFavourite;
        this.typeCar = typeCar;
        this.province = province;
    }
    public BsCarHome(String id, PdfImage image, String dateAuction, String timeAuctionStart, String timeAuctionEnd) {
        this.id = id;
        this.image = image;
        this.dateAuction = dateAuction;
        this.timeAuctionStart = timeAuctionStart;
        this.timeAuctionEnd = timeAuctionEnd;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getNameBs() {
        return nameBs;
    }

    public void setNameBs(String nameBs) {
        this.nameBs = nameBs;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(String typeCar) {
        this.typeCar = typeCar;
    }

    public int getCountFavourite() {
        return countFavourite;
    }

    public void setCountFavourite(int countFavourite) {
        this.countFavourite = countFavourite;
    }

    public String getExpBs() {
        return expBs;
    }

    public void setExpBs(String expBs) {
        this.expBs = expBs;
    }
}
