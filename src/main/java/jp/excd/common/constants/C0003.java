package jp.excd.common.constants;

public enum C0003 {
	
	/**
	 * 楽譜通り
	 */
	NORMAL("0","楽譜どおり"),
	/**
	 * 1オクターブ上
	 */
	VB("1","1オクターブ上で表記");
	
	private String id;
	private String label;
	
	private C0003(String id,String label) {
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
	public  static C0003 getById(String id) {
		
		for( C0003 C3:C0003.values()) {
			if(C3.getId().equals(id)) {
				return C3;
				
			}
		}
		return null;
	}
	
}
