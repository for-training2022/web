package jp.excd.servlet;

public class SongComposerBean {

	String id;
	String title;
	String unique_code;
	String nickname;
	String rating_total_formated;
	String rating_average_formated;
	String total_listen_count_formated;
	String release_datetime_formated;
	String image_file_name;
	String image_file_height;
	String image_file_width;

	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	
	public void setUnique_code(String unique_code) {
		this.unique_code = unique_code;
	}
	public String getUniqueCode() {
		return unique_code;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNickname() {
		return nickname;
	}
	
	public void setRating_total_formated(String rating_total) {
		this.rating_total_formated = rating_total;
	}
	public String getRating_total() {
		return rating_total_formated;
	}
	
	public void setRating_average_formated(String rating_average) {
		this.rating_average_formated = rating_average;
	}
	public String getRating_average() {
		return rating_average_formated;
	}
	
	public void setTotal_listen_count_formated(String total_listen_count) {
		this.total_listen_count_formated = total_listen_count;
	}
	public String getTotal_listen_count() {
		return total_listen_count_formated;
	}
	
	public void setRelease_datetime_formated(String release_datetime) {
		this.release_datetime_formated = release_datetime;
	}
	public String getRelease_datetime() {
		return release_datetime_formated;
	}
	
	public void setImage_file_name(String image_file_name) {
		this.image_file_name = image_file_name;
	}
	public String getImage_file_name() {
		return image_file_name;
	}
	
	public void setImage_file_height(String image_file_height) {
		this.image_file_height = image_file_height;
	}
	public String getImage_file_height() {
		return image_file_height;
	}
	
	public void setImage_file_width(String image_file_width) {
		this.image_file_width = image_file_width;
	}
	public String getImage_file_width() {
		return image_file_width;
	}


}
