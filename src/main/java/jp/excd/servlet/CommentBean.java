package jp.excd.servlet;

public class CommentBean {
	String comment;
	int sequence;
	String type;
	String toCommentId;
	String writeDatetime;
	String nickname2;
	String uniqueCode2;
	String rating;
	String composerId;
	String id;
	
	
	public void setComment(String str1) {
		comment = str1;
	}
	public void setSequence(int int1) {
		sequence = int1;
	}
	public void setType(String str2) {
		type = str2;
	}
	public void setToCommentId(String str3) {
		toCommentId = str3;
	}
	public void setWriteDatetime(String str4) {
		writeDatetime = str4;
	}
	
	public void setNickname2(String str5) {
		nickname2 = str5;
	}
	public void setUniqueCode2(String str6) {
		uniqueCode2 = str6;
	}
	public void setRating(String str7) {
		rating = str7;
	}
	public void setComposerId(String str8) {
		composerId = str8;
	}
	
	public void setId(String str9) {
		id = str9;
	}
	
	
	
	public String getComment() {
		return comment;
	}

	public int getSequence() {
		return sequence;
	}

	public String getType() {
		return type;
	}
	public String getToCommentId() {
		return toCommentId;
	}

	public String getWriteDatetime() {
		return writeDatetime;
	}


	public String getRating() {
		return rating;
	}

	public String getNickname2() {
		return nickname2;
	}


	public String getUniqueCode2() {
		return uniqueCode2;
	}
	
	public String getComposerId() {
		return composerId;
	}
	public String getId() {
		return id;
	}
	
}

