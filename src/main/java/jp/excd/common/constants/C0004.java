package jp.excd.common.constants;

public enum C0004 {
	
	NORMAL("0","通常コメント"),
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
	
	public  static C0004 getById(String id) {
		
		for( C0004 C4:C0004.values()) {
			if(C4.getId().equals(id)) {
				return C4;
				
			}
		}
		return null;
	}
}
