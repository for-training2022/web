package jp.excd.bean;

public class ComposerRecord {

	private String nickname;
	private String joined_date;
	private String gender;
	private String birthday;
	private String listener_count;
	private String language_type;
	private String unique_code;

	/**
	 * @return title
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname セットする
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return joined_date
	 */
	public String getJoined_date() {
		return joined_date;
	}
	/**
	 * @param joined_date セットする 
	 */
	public void setJoined_date(String joined_date) {
		this.joined_date = joined_date;
	}
	/**
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender セットする 
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthdayt セットする 
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return listener_count
	 */
	public String getListener_count() {
		return listener_count;
	}
	/**
	 * @param listener_count セットする
	 */
	public void setListener_count(String listener_count) {
		this.listener_count = listener_count;
	}
	/**
	 * @return language_type
	 */
	public String getLanguage_type() {
		return language_type;
	}
	/**
	 * @param language_type セットする 
	 */
	public void setLanguage_type(String language_type) {
		this.language_type = language_type;
	}
	/**
	 * @return unique_code
	 */
	public String getUniqueCode() {
		return unique_code;
	}
	/**
	 * @param unique_code セットする 
	 */
	public void setUniqueCode(String unique_code) {
		this.unique_code = unique_code;
	}

}
