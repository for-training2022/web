package jp.excd.servlet;

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
	
	public void setJoined_date(String joiend_date) {
		this.joined_date = joiend_date;
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
	
	public  void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getListener_count() {
		return listener_count;
	}
	
	public void setListener_count(String listener_count) {
		this.listener_count = listener_count;
	}
	
	public String getLanguage_type() {
		return language_type;
	}
	
	public void setLanguage_type(String language_type) {
		this.language_type = language_type;
	}
	
	
	
	
}
