package br.com.lucas.api.database;

import java.util.UUID;

import br.com.lucas.api.constructor.PlayerConstructor;

public class FunctionsDatabase {

	private String 
	SELECT_FROM_PLAYERS = "", 
	SELECT_FROM_PLAYER = "",
	INSERT_INTO_PLAYER = "",
	UPDATE_PLAYER = "";
	
	public FunctionsDatabase() {
		this.SELECT_FROM_PLAYERS = "SELECT * FROM `players`";
		this.SELECT_FROM_PLAYER = "SELECT * FROM `players` WHERE `playerID`='%id%';";
		this.INSERT_INTO_PLAYER = "INSERT INTO `players` (`playerID`,`playerCash`,`playerRank`,`oldRank`, `rankActived`, `rankExpire`, `firstLogin`) VALUES ('%id%','%pc%','%pr%','%or%','%ra%', '%re%', '%fl%')";
		this.UPDATE_PLAYER = "UPDATE `players` SET `playerCash`='%pc%', `playerRank`='%pr%', `oldRank`='%or%', `rankActived`='%ra%', `rankExpire`='%re%', `firstLogin`='%fl%' WHERE `playerID`='%id%'";
	}
	
	public String SELECT_FROM_PLAYERS() {
		return SELECT_FROM_PLAYER;
	}
	public String SELECT_FROM_PLAYER() {
		return SELECT_FROM_PLAYERS;
	}
	public String INSERT_INTO_PLAYER() {
		return INSERT_INTO_PLAYER;
	}
	public String UPDATE_PLAYER() {
		return UPDATE_PLAYER;
	}
	public String replaceAllPlayer(UUID id) {
		return this.SELECT_FROM_PLAYER().replaceAll("%id%", String.valueOf(id).toString());
	}
	public String replaceAllInsertIntoPlayer(PlayerConstructor ps) {
		return this.INSERT_INTO_PLAYER().replaceAll("%id%", String.valueOf(ps.playerUniqueId().toString()).replaceAll("%pc%", String.valueOf(ps.playerCash())).replaceAll("%pr%", String.valueOf(ps.playerRank())).replaceAll("%or%", String.valueOf(ps.playerOldRank())).replaceAll("%ra%", String.valueOf(ps.playerRankActivate())).replaceAll("%re%", String.valueOf(ps.playerRankExpire())).replaceAll("%fl%", String.valueOf(ps.playerFirstLogin())));
	}
	public String replaceAllUpdatePlayer(PlayerConstructor ps) {
		return this.UPDATE_PLAYER.replaceAll("%pc%", String.valueOf(ps.playerCash())).replaceAll("%pr%", String.valueOf(ps.playerRank())).replaceAll("%or%", String.valueOf(ps.playerOldRank())).replaceAll("%ra%", String.valueOf(ps.playerRankActivate())).replaceAll("%re%", String.valueOf(ps.playerRankExpire())).replaceAll("%fl%", String.valueOf(ps.playerFirstLogin())).replaceAll("%id%", String.valueOf(ps.playerUniqueId().toString()));
	}
}
