package jp.excd.bean;

public class SongBean {


	String title;
	String ratingTotal;
	String ratingAverage;
	String totalListenCount;
	String releaseDatetime;
	String lastUpdateDatetime;
	String message;
	String key;
	String scoreType;
	String bpm;
	String imageFileName;
	double imageFileHeight;
	int imageFileWidth;
	String otherLinkUrl;
	String otherLinkDescription;
	String nickname1;
	String uniqueCode1;
	double cutLength;

	public void setTitle(String str1) {
		title = str1;
	}
	public void setRatingTotal(String str2) {
		ratingTotal = str2;
	}
	public void setRatingAverage(String str3) {
		ratingAverage = str3;
	}
	public void setTotalListenCount(String str4) {
		totalListenCount = str4;
	}
	public void setReleaseDatetime(String str5) {
		releaseDatetime = str5;
	}
	public void setLastUpdateDatetime(String str6) {
		lastUpdateDatetime = str6;
	}
	public void setMessage(String str7) {
		message = str7;
	}
	public void setKey(String str8) {
		key = str8;
	}
	public void setScoreType(String str9) {
		scoreType= str9;
	}
	public void setBpm(String str10) {
		bpm = str10;
	}
	public void setImageFileName(String str11) {
		imageFileName = str11;
	}
	public void setImageFileHeight(double d) {
		imageFileHeight = d;
	}
	public void setImageFileWidth(int int2) {
		imageFileWidth = int2;
	}
	public void setOtherLinkUrl(String str12) {
		otherLinkUrl = str12;
	}
	public void setOtherLinkDescription(String str13) {
		otherLinkDescription = str13;
	}
	
	public String getTitle() {
		return title;
	}

	public String getRatingTotal() {
		return ratingTotal;
	}

	public String getRatingAverage() {
		return ratingAverage;
	}

	public String getTotalListenCount() {
		return totalListenCount;
	}

	public String getReleaseDatetime() {
		return releaseDatetime;
	}

	public String getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}

	public String getMessage() {
		return message;
	}

	public String getKey() {
		return key;
	}

	public String getScoreType() {
		return scoreType;
	}

	public String getBpm() {
		return bpm;
	}
	public String getImageFileName() {
		return imageFileName;
	}

	public double getImageFileHeight() {
		return imageFileHeight;
	}

	public int getImageFileWidth() {
		return imageFileWidth;
	}

	public String getOtherLinkUrl() {
		return otherLinkUrl;
	}

	public String getOtherLinkDescription() {
		return otherLinkDescription;
	}
	public void setNickname1(String str18) {
		nickname1 = str18;
	}
	public void setUniqueCode1(String str20) {
		uniqueCode1 = str20;
	}
	public String getNickname1() {
		return nickname1;
	}

	public String getUniqueCode1() {
		return uniqueCode1;
	}
	public void setCutLength(double d) {
		cutLength = d;
		
	}
	public double getCutLength() {
		return cutLength;
	}
	

}
