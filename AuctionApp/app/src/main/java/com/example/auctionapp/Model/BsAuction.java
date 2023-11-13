package com.example.auctionapp.Model;

import java.util.List;

public class BsAuction {
    //Danh sách người dùng tham gia đấu giá
    private String idBs;
    private List<MessageInAuction> messageInAuctionList;
    private int Cost;
    public BsAuction() {

    }
    public BsAuction(String idBs, int Cost, List<MessageInAuction> messageInAuctionList) {
        this.idBs = idBs;
        this.Cost = Cost;
        this.messageInAuctionList = messageInAuctionList;

    }

    public List<MessageInAuction> getMessageInAuctionList() {
        return messageInAuctionList;
    }

    public void setMessageInAuctionList(List<MessageInAuction> messageInAuctionList) {
        this.messageInAuctionList = messageInAuctionList;
    }

    public String getIdBs() {
        return idBs;
    }

    public void setIdBs(String idBs) {
        this.idBs = idBs;
    }


    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }
}
