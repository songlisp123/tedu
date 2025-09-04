package com.egadress0904.demo.pojo.VO;

public class addressVO {
    private String receiver;
    private String address;
    private String mobile;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "addressVO{" +
                "receiver='" + receiver + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
