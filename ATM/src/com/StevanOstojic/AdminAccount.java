package com.StevanOstojic;

public class AdminAccount {

    private String adminName = "SonyABM";
    private int adminPINNumber = 3012;

    AdminAccount() {
        getAdminName();
        getAdminPINNumber();
    }

    public int getAdminPINNumber() {
        return adminPINNumber;
    }

    public String getAdminName() {
        return adminName;
    }


}
