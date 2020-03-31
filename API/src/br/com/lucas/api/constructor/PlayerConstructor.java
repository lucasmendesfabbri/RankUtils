package br.com.lucas.api.constructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


import br.com.lucas.api.enums.RankEnum;
import br.com.lucas.api.managers.PlayerManager;

public class PlayerConstructor {

	private UUID id;
	private int cash;
	private RankEnum rankEnum, oldRank;
	private long actived, expire, firstLogin;
	
	public PlayerConstructor(UUID id, int cash, RankEnum rankEnum, RankEnum oldRankEnum, long actived, long expire, long firstLogin) {
		this.id = id; this.cash = cash; this.rankEnum = rankEnum; this.oldRank = oldRankEnum; this.actived = actived; this.expire = expire - System.currentTimeMillis(); this.firstLogin = firstLogin;
	}
	public PlayerConstructor(UUID id, int cash, RankEnum rankEnum, RankEnum oldRankEnum, long actived, long expire, long firstLogin, PlayerManager playerManager) {
		this.id = id; this.cash = cash; this.rankEnum = rankEnum; this.oldRank = oldRankEnum; this.actived = actived; this.expire = expire - System.currentTimeMillis(); this.firstLogin = firstLogin;
	}
	
	public UUID playerUniqueId() {
		return id;
	}
	public int playerCash() {
		return cash;
	}
	public RankEnum playerRank() {
		return rankEnum;
	}
	public long playerRankActivate() {
		return actived;
	}
	public long playerRankExpire() {
		return expire;
	}
	public long playerFirstLogin() {
		return firstLogin;
	}
	public RankEnum playerOldRank() {
		return oldRank;
	}
	public void setActived(long actived) {
		this.actived = actived;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public void setExpire(long expire) {
		this.expire = expire;
	}
	public void setFirstLogin(long firstLogin) {
		this.firstLogin = firstLogin;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public void setOldRank(RankEnum oldRank) {
		this.oldRank = oldRank;
	}
	public void setRankEnum(RankEnum rankEnum) {
		this.rankEnum = rankEnum;
	}
	public String formatDateExpire() {
		SimpleDateFormat format = new SimpleDateFormat("EEEE, (dd)/(MMMMMMMM)/(yyyy) | HH:mm:ss )");
		String date = format.format(new Date(expire));
		if(this.expire <= -1) return "§7Permamente";
		return "§7[" + date + "]";
	}
	
	
}
