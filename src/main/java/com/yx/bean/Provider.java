package com.yx.bean;

/**
 * @program: redis-register
 * @description: provider的注册信息
 * @author: yaoxu
 * @create: 2018-08-25 21:53
 **/
public class Provider {
    private String providerName ;
    private String pagentName ;
    private String protocol ;
    private String address;
    private String address1 ;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getPagentName() {
        return pagentName;
    }

    public void setPagentName(String pagentName) {
        this.pagentName = pagentName;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "providerName='" + providerName + '\'' +
                ", pagentName='" + pagentName + '\'' +
                ", protocol='" + protocol + '\'' +
                ", address='" + address + '\'' +
                ", address1='" + address1 + '\'' +
                '}';
    }
}
