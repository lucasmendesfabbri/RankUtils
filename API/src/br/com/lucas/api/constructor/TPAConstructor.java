package br.com.lucas.api.constructor;


import org.bukkit.Location;

public class TPAConstructor {

	private String send, to;
	private Location toLoc;
	private long sendDate, expireDate;
	private boolean toggle;
	
	public TPAConstructor(String send, String to, Location toLoc, long expire) {
		this.send = send;
		this.to = to;
		this.toLoc = toLoc;
		this.sendDate = System.currentTimeMillis();
		this.expireDate = expire;
		this.toggle = true;
	}
	public boolean isToggle() {
		return toggle;
	}
	public void setToggle(boolean toggle) {
		this.toggle = toggle;
	}
	
	public long getExpireDate() {
		return expireDate;
	}
	public String getSend() {
		return send;
	}
	public long getSendDate() {
		return sendDate;
	}
	public String getTo() {
		return to;
	}
	public Location getToLoc() {
		return toLoc;
	}
	public void setExpireDate(long expireDate) {
		this.expireDate = expireDate;
	}
	public void setSend(String send) {
		this.send = send;
	}
	public void setSendDate(long sendDate) {
		this.sendDate = sendDate;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public void setToLoc(Location toLoc) {
		this.toLoc = toLoc;
	}

	
}
