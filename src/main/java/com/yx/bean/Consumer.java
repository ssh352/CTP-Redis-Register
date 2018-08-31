package com.yx.bean;

/**
 * @program: redis-register
 * @description:注册consumer需要的数据信息
 * @author: yaoxu
 * @create: 2018-08-24 21:47
 **/
public class Consumer {
    private String ConsumerName ;//消费方名称
    private String CAgentName ;//CAgent 名称
    private String Address ;//RabbitMQ地址1，&符号前地址
    private String Address1 ;//&符号后面地址，address+&+address1
    private String Protocol ;//mq协议

    private String flag ;//开关标志

    public String getConsumerName() {
        return ConsumerName;
    }

    public void setConsumerName(String consumerName) {
        ConsumerName = consumerName;
    }

    public String getCAgentName() {
        return CAgentName;
    }

    public void setCAgentName(String CAgentName) {
        this.CAgentName = CAgentName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getProtocol() {
        return Protocol;
    }

    public void setProtocol(String protocol) {
        Protocol = protocol;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "ConsumerName='" + ConsumerName + '\'' +
                ", CAgentName='" + CAgentName + '\'' +
                ", Address='" + Address + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
