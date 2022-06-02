package jp.excd.servlet;

import java.text.DecimalFormat;

public class PersonRecord {
	
	private String nickname;
	private String unique_code;
	private String joined_date;
	private String gender;
	private String birthday;
	private String listener_count;
	private String language_type;
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getUnique_code() {
		return unique_code;
	}
	
	public void setUnique_code(String unique_code) {
		this.unique_code = unique_code;
	}
	
	public String getJoined_date() {
		return joined_date;
	}
	
	public String setJoined_date_formated(String a) {
		if(a==null) {
			this.joined_date="";
			return joined_date;
		}else {
		this.joined_date = a.substring(0,4)+"年"+a.substring(4,6)+"月"+a.substring(6)+"日";
		return joined_date;
		}
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public  String setBirthday_formated(String a) {
		if(a==null) {
			this.birthday="";
			return birthday;
		}else {
		this.birthday = a.substring(0,4)+"年"+a.substring(4,6)+"月"+a.substring(6)+"日";
		return birthday;
		}
	}
	
	public String getListener_count() {
		return listener_count;
	}
	
	public String setListener_count(Long a) {
		DecimalFormat df = new DecimalFormat("###,###");
		this.listener_count=df.format(a);
		return listener_count;
	}
	
	public String getLanguage_type() {
		return language_type;
	}
	
	public void setLanguage_type(String language_type) {
		this.language_type = language_type;
	}
	
	
	
	
}
