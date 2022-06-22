package jp.excd.bean;

public class SongBean {

	private String title;
	private String rating_total_formated;
	private String rating_average_formated;
	private String total_listen_count_formated;
	private String release_datetime_formated;
	private String image_file_name;
	private int	imageFileHeight;
	private int	imageFileWidth;
	private double formatHeight;
	private double cutLength;
	private String song_id;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRating_total_formated() {
		return rating_total_formated;
	}
	public void setRating_total_formated(String rating_total_formated) {
		this.rating_total_formated = rating_total_formated;
	}
	public String getRating_average_formated() {
		return rating_average_formated;
	}
	public void setRating_average_formated(String rating_average_formated) {
		this.rating_average_formated = rating_average_formated;
	}
	public String getTotal_listen_count_formated() {
		return total_listen_count_formated;
	}
	public void setTotal_listen_count_formated(String total_listen_count_formated) {
		this.total_listen_count_formated = total_listen_count_formated;
	}
	public String getRelease_datetime_formated() {
		return release_datetime_formated;
	}
	public void setRelease_datetime_formated(String release_datetime_formated) {
		this.release_datetime_formated = release_datetime_formated;
	}
	public String getImage_file_name() {
		return image_file_name;
	}
	public void setImage_file_name(String image_file_name) {
		this.image_file_name = image_file_name;
	}
	public int getImageFileHeight() {
		return imageFileHeight;
	}
	public void setImageFileHeight(int imageFileHeight) {
		this.imageFileHeight = imageFileHeight;
	}
	public int getImageFileWidth() {
		return imageFileWidth;
	}
	public void setImageFileWidth(int imageFileWidth) {
		this.imageFileWidth = imageFileWidth;
	}
	public double getFormatHeight() {
		return formatHeight;
	}
	public void setFormatHeight(double formatHeight) {
		this.formatHeight = formatHeight;
	}
	public double getCutLength() {
		return cutLength;
	}
	public void setCutLength(double cutLength) {
		this.cutLength = cutLength;
	}
	public String getSong_id() {
		return song_id;
	}
	public void setSong_id(String song_id) {
		this.song_id = song_id;
	}

}
