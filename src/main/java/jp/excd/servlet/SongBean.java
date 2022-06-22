package jp.excd.servlet;

public class SongBean{ //出力用のリスト
	String title;
	String imageFileName;
	int	imageFileHeight;
	int	imageFileWidth;
	double formatHeight;
	double cutLength;
	long songId;
	String ratingTotalFormated;
	String ratingAverageFormated;
	String totalListenCountFoemated;
	String releaseDatetimeFormated;
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	
	public void setImageFileHeight(int imageFileHeight) {
		this.imageFileHeight = imageFileHeight;
	}
	
	public void setImageFileWidth(int imageFileWidth) {
		this.imageFileWidth = imageFileWidth;
	}
	
	public void setFormatHeight(double formatHeight) {
		this.formatHeight = formatHeight;
	}
	
	public void setCutLenght(double cutLength) {
		this.cutLength = cutLength;
	}
	
	public void setSongId(long songId) {
		this.songId = songId;
	}
	
	public void setRatingTotalFormated(String ratingTotalFormated) {
		this.ratingTotalFormated = ratingTotalFormated;
	}
	
	public void setRatingAverageFormated(String ratingAverageFormated) {
		this.ratingAverageFormated = ratingAverageFormated;
	}
	
	public void setTotalListenCountFoemated(String totalListenCountFoemated) {
		this.totalListenCountFoemated = totalListenCountFoemated;
	}
	
	public void setReleaseDatetimeFormated(String releaseDatetimeFormated) {
		this.releaseDatetimeFormated = releaseDatetimeFormated;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getImageFileName() {
		return imageFileName;
	}
	
	public int getImageFileHeight() {
		return imageFileHeight;
	}
	
	public int getImageFileWidth() {
		return imageFileWidth;
	}
	
	public double getFormatHeight() {
		return formatHeight;
	}
	
	public double getCutLength() {
		return cutLength;
	}
	
	public long getSongId() {
		return songId;
	}
	
	public String getRatingTotalFormated() {
		return ratingTotalFormated;
	}
	
	public String getRatingAverageFormated() {
		return ratingAverageFormated;
	}
	
	public String getTotalListenCountFoemated() {
		return totalListenCountFoemated;
	}
	
	public String getReleaseDatetimeFormated() {
		return releaseDatetimeFormated;
	}
	
	
	
}