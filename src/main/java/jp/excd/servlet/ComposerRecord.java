package jp.excd.servlet;

public class ComposerRecord{ //SQLで取得したデータを一時的に保持する
	long composerId;
	String uniqueCode;
	String nickname;
	String joinedDate;
	String gender;
	String birthday;
	String message;
	String fbLink;
	String twLink;
	String otherLinkUrl;
	String otherLinkDecription;
	
	public void setComposerId(long composerId) {
		this.composerId = composerId;
	}
		
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setFbLink(String fbLink) {
		this.fbLink = fbLink;
	}
	
	public void setTwLink(String twLink) {
		this.twLink = twLink;
	}
	
	public void setOtherLinkUrl(String otherLinkUrl) {
		this.otherLinkUrl = otherLinkUrl;
	}
	
	public void setOtherLinkDecription(String otherLinkDecription) {
		this.otherLinkDecription = otherLinkDecription;
	}
	
	public long getComposerId() {
		return composerId;
	}
	
	public String getUniqueCode() {
		return uniqueCode;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getjJoinedDate() {
		return joinedDate;
	}
	
	public String getGender() {
		return gender;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getFbLink() {
		return fbLink;
	}
	
	public String getTwLink() {
		return twLink;
	}
	
	public String getOtherLinkUrl() {
		return otherLinkUrl;
	}
	
	public String getOtherLinkDecription() {
		return otherLinkDecription;
	}
}