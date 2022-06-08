package jp.excd.common.constants;

public enum C0004 {
	
	/**
	 * コメント
	 */
	NORMAL("0","通常コメント"),
	
	/**
	 * コメントに対する返信
	 */
	REPLY("1","返信");
	
	private String id;
	private String label;
	
	private C0004(String id,String label) {
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
	 * データベースから取得したコード値があるか走査
	 * @param データベースから取得したコード値
	 * @return
	 */
	public  static C0004 getById(String id) {
		
		for( C0004 C4:C0004.values()) {
			if(C4.getId().equals(id)) {
				return C4;
				
			}
		}
		return null;
	}
}
