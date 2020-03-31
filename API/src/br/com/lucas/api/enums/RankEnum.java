package br.com.lucas.api.enums;

public enum RankEnum {

	MEMBRO("Membro", "§7", false, false),
	VIP("VIP", "§a[VIP]", true, false),
	MVP("MVP", "§b[§bMVP§6+§b]", true, false),
	ADMIN("ADMINISTRATOR", "§c[Admin]", false, true),
	MASTER("MASTER", "§6[Master]", false, true);
	
	private String name, prefix;
	private boolean  vip, staff;
	
	private RankEnum(String name, String prefix, boolean VIP, boolean STAFF) {
		this.name = name; this.prefix = prefix; this.vip = VIP; this.staff = STAFF;
	}
	public String getName() {
		return name;
	}
	public String getPrefix() {
		return prefix;
	}
	public boolean isVip() {
		return vip;
	}
	public boolean isStaff() {
		return staff;
	}
	
}
