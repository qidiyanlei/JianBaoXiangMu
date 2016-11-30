package bgs.com.jianbao11.bean;

import java.io.Serializable;

public class Info_Bean implements Serializable {
	private String title;
	private String price;
	private String id;
	private String picture_str;
	private String issue_time;
	private String final_time;
	private String follow;

	public Info_Bean(String title, String price, String id, String picture_str, String issue_time, String final_time, String follow) {
		this.title = title;
		this.price = price;
		this.id = id;
		this.picture_str = picture_str;
		this.issue_time = issue_time;
		this.final_time = final_time;
		this.follow = follow;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPicture_str() {
		return picture_str;
	}

	public void setPicture_str(String picture_str) {
		this.picture_str = picture_str;
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
}
