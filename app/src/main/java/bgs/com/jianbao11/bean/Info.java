package bgs.com.jianbao11.bean;

import java.util.List;

/**
 * Created by 醇色 on 2016/11/28.
 */

public class Info {
    private String id;
    private String title;
    private String description;
    private String price;
    private String mobile;
    private String qq;
    private String wechat;
    private String email;
    private List<String> list;
    private String issue_time;
    private String final_time;
    private String follow;
    private int  state;
    private int owner;

    public Info(String id, String title, String description, String price, String mobile, String qq, String wechat, String email, List<String> list, String issue_time, String final_time, String follow, int state, int owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.mobile = mobile;
        this.qq = qq;
        this.wechat = wechat;
        this.email = email;
        this.list = list;
        this.issue_time = issue_time;
        this.final_time = final_time;
        this.follow = follow;
        this.state = state;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getIssue_time() {
        return issue_time;
    }

    public void setIssue_time(String issue_time) {
        this.issue_time = issue_time;
    }

    public String getFinal_time() {
        return final_time;
    }

    public void setFinal_time(String final_time) {
        this.final_time = final_time;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
