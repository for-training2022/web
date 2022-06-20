package jp.excd.servlet;

public class ComposerBean{ //出力用のデータを纏める用
	String uniqueCode;
	String nickname;
	String message;
	String genderResult;
	String birthdayFormated;
	String fbLink;
	String twLink;
	String joinedDateFormated;
	int	songTotal;
	String totalRatingFormated;
	String averageRatingFormated;
	String totalListenFormated;
	String otherLinkUrl;
	String otherLinkDecription;
	
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setGenderResult(String genderResult) {
		this.genderResult = genderResult;
	}
	
	public void setBirthdayFormated(String birthdayFormated) {
		this.birthdayFormated = birthdayFormated;
	}
	
	public void setFbLink(String fbLink) {
		this.fbLink = fbLink;
	}
	
	public void setTwLink(String twLink) {
		this.twLink = twLink;
	}
	
	public void setJoinedDateFormated(String joinedDateFormated) {
		this.joinedDateFormated = joinedDateFormated;
	}
	public void setSongTotal(int songTotal) {
		this.songTotal = songTotal;
	}
	
	public void setTotalRatingFormated(String totalRatingFormated) {
		this.totalRatingFormated = totalRatingFormated;
	}
	
	public void setAverageRatingFormated(String averageRatingFormated) {
		this.averageRatingFormated = averageRatingFormated;
	}
	
	public void setTotalListenFormated(String totalListenFormated) {
		this.totalListenFormated = totalListenFormated;
	}
	
	public void setOtherLinkUrl(String otherLinkUrl) {
		this.otherLinkUrl = otherLinkUrl;
	}
	
	public void setOtherLinkDecription(String otherLinkDecription) {
		this.otherLinkDecription = otherLinkDecription;
	}
	
	public String getUniqueCode() {
		return uniqueCode;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getGenderResult() {
		return genderResult;
	}
	
	public String getBirthdayFormated() {
		return birthdayFormated;
	}
	
	public String getFbLink() {
		return fbLink;
	}
	
	public String getTwLink() {
		return twLink;
	}
	
	public String getJoinedDateFormated() {
		return joinedDateFormated;
	}
	
	public int getSongTotal() {
		return songTotal;
	}
	
	public String getTotalRatingFormated() {
		return totalRatingFormated;
	}
	
	public String getAverageRatingFormated() {
		return averageRatingFormated;
	}
	
	public String getTotalListenFormated() {
		return totalListenFormated;
	}
	
	public String getOtherLinkUrl() {
		return otherLinkUrl;
	}
	
	public String getOtherLinkDecription() {
		return otherLinkDecription;
	}
}