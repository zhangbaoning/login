package me.zhangbaoning.login.entity;

/**
 * @author: zhangbaoning
 * @date: 2018/11/10
 * @since: JDK 1.8
 * @description: 微信封装类
 */
public class WechatVO {
    String code;
    String state;

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "WechatVO{" +
                "code='" + code + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
