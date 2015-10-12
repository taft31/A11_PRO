package com.huayu.taft.Model;

/**
 * Created by Administrator on 15-10-11.
 */
public class SendbackInfo {
    private String stbi_ID;
    private String sdb_ID;
    private String bk_ID;

    public SendbackInfo() {
    }

    public SendbackInfo(String stbi_ID, String sdb_ID, String bk_ID) {
        this.setStbi_ID(stbi_ID);
        this.setSdb_ID(sdb_ID);
        this.setBk_ID(bk_ID);
    }

    public String getStbi_ID() {
        return stbi_ID;
    }

    public void setStbi_ID(String stbi_ID) {
        this.stbi_ID = stbi_ID;
    }

    public String getSdb_ID() {
        return sdb_ID;
    }

    public void setSdb_ID(String sdb_ID) {
        this.sdb_ID = sdb_ID;
    }

    public String getBk_ID() {
        return bk_ID;
    }

    public void setBk_ID(String bk_ID) {
        this.bk_ID = bk_ID;
    }
}
