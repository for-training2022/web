package jp.excd.common.constants;

public enum C0005 {
	
	/**
	 * 男
	 */
	MALE("1","男性"),
	FEMALE("2","女性");
	
	private String id;
	private String label;
	
	private C0005(String id,String label) {
		this.id = id;
		this.label = label;
	}
	
	public String getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public  static C0005 getById(String id) {
		
		for( C0005 C5:C0005.values()) {
			if(C5.getId().equals(id)) {
				return C5;
				
			}
		}
		return null;
	}
}
