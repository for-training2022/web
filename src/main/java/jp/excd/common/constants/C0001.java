package jp.excd.common.constants;

public enum C0001 {
	
	/**
	 * 英語
	 */
	ENG("001","英語"),
	
	/**
	 * 日本語
	 */
	JPN("002","日本語");
	
	private String id;
	private String label;
	
	private C0001(String id,String label) {
		this.id = id;
		this.label = label;
	}
	
	public String getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	
	/**
	 * データベースから取得したコード値があるか走査する
	 * @param データベースから取得したコード値
	 * @return
	 */
	public  static C0001 getById(String id) {
		
		for( C0001 C1:C0001.values()) {
			if(C1.getId().equals(id)) {
				return C1;
				
			}
		}
		return null;
	}
}
