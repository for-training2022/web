package jp.excd.common.constants;

public enum C0002 {
	C_N_Major("01","Cメジャー"),
	C_S_Major("02","Cシャープメジャー"),
	D_F_Major("03","Dフラットメジャー"),
	D_N_Major("04","Dメジャー"),
	D_S_Major("05","Dシャープメジャー"),
	E_S_Major("06","Eフラットメジャー"),
	E_N_Major("07","Eメジャー"),
	F_N_Major("08","Fメジャー"),
	F_S_Major("09","Fシャープメジャー"),
	G_F_Major("10","Gフラットメジャー"),
	G_N_Major("11","Gメジャー"),
	G_S_Major("12","Gシャープメジャー"),
	A_F_Major("13","Aフラットメジャー"),
	A_N_Major("14","Aメジャー"),
	A_S_Major("15","Aシャープメジャー"),
	B_F_Major("16","Bフラットメジャー"),
	B_N_Major("17","Bメジャー"),
	C_N_Minor("18","Cマイナー"),
	C_S_Minor("19","Cシャープマイナー"),
	D_F_Minor("20","Dフラットマイナー"),
	D_N_Minor("21","Dマイナー"),
	D_S_Minor("22","Dシャープマイナー"),
	E_F_Minor("23","Eフラットマイナー"),
	E_N_Minor("24","Eマイナー"),
	F_N_Minor("25","Fマイナー"),
	F_S_Minor("26","Fシャープマイナー"),
	G_F_Minor("27","Gフラットマイナー"),
	G_N_Minor("28","Gマイナー"),
	G_S_Minor("29","Gシャープマイナー"),
	A_F_Minor("30","Aフラットマイナー"),
	A_N_Minor("31","Aマイナー"),
	A_S_Minor("32","Aシャープマイナー"),
	B_F_Minor("33","Bフラットマイナー"),
	B_N_Minor("34","Bマイナー");
	
	private String id;
	private String label;
	
	private C0002(String id,String label) {
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
	public  static C0002 getById(String id) {
		
		for( C0002 C2:C0002.values()) {
			if(C2.getId().equals(id)) {
				return C2;
				
			}
		}
		return null;
	}


}
