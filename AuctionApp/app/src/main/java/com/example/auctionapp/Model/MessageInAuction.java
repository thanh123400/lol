package com.example.auctionapp.Model;

import java.util.List;

public class MessageInAuction {
    private String idUser;
    private String costUser;

    public MessageInAuction() {
    }

    public MessageInAuction(String idUser, String costUser) {
        this.idUser = idUser;
        this.costUser = costUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getCostUser() {
        return costUser;
    }

    public void setCostUser(String costUser) {
        this.costUser = costUser;
    }
}
