package br.com.lucas.api.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.avaje.ebeaninternal.server.cluster.mcast.Message;

import br.com.lucas.api.API;
import br.com.lucas.api.constructor.PlayerConstructor;
import br.com.lucas.api.database.FunctionsDatabase;
import br.com.lucas.api.enums.RankEnum;

public class PlayerManager {

	private API instance;
	private FunctionsDatabase functionsDatabase;

	public ArrayList<PlayerConstructor> players; int playerInt = 0;

	public PlayerManager(API instance) {
		this.instance = instance; this.functionsDatabase = new FunctionsDatabase(); this.players  = new ArrayList<PlayerConstructor>(); 
		try {
			PreparedStatement ps = this.instance.database.connection.prepareStatement("SELECT * FROM `players`");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				players.add(new PlayerConstructor(UUID.fromString(rs.getString("playerID")), rs.getInt("playerCash"), RankEnum.valueOf(rs.getString("playerRank")), RankEnum.valueOf(rs.getString("oldRank")), rs.getLong("rankActived"), rs.getLong("rankExpire"), rs.getLong("firstLogin")));
				playerInt++;
			}
			Bukkit.getLogger().info("LOADING TOTALLY OF ("+playerInt+") PLAYERS REGISTERED IN DATABASE!");
			ps.close();rs.close();
		} catch (SQLException e) {e.printStackTrace();}

	}

	public PlayerConstructor getPlayer(UUID uuid) {
		for(PlayerConstructor playerConstructor : players) {
			if(playerConstructor == null)return null;
			if(playerConstructor.playerUniqueId().toString() ==  uuid.toString() || Bukkit.getOfflinePlayer(uuid).getName() == Bukkit.getOfflinePlayer(playerConstructor.playerUniqueId()).getName()) return playerConstructor;
		}
		return null;
	}

	public void createProfile(UUID id) {
		try {
			PlayerConstructor p = new PlayerConstructor(id, 0, RankEnum.MEMBRO, RankEnum.MEMBRO, System.currentTimeMillis(), -1, System.currentTimeMillis(), this);
			PreparedStatement ps = this.instance.database.connection.prepareStatement("INSERT INTO `players` (`playerID`,`playerCash`,`playerRank`,`oldRank`, `rankActived`, `rankExpire`, `firstLogin`) VALUES ('"+id.toString()+"','"+p.playerCash()+"','"+p.playerRank().toString()+"','"+p.playerOldRank().toString()+"','"+p.playerRankActivate()+"', '"+p.playerRankExpire()+"', '"+p.playerFirstLogin()+"')");
			ps.executeUpdate();
			ps.close();
			this.players.add(p);
			saveProfile(p.playerUniqueId());
		}catch (SQLException e) { e.printStackTrace(); 


		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void updateProfile(UUID id) {
		PlayerConstructor p = getPlayer(id);
		try {
			PreparedStatement ps = this.instance.database.connection.prepareStatement("UPDATE `players` SET `playerCash`='"+p.playerCash()+"', `playerRank`='"+p.playerRank()+"', `oldRank`='"+p.playerOldRank().toString()+"', `rankActived`='"+p.playerRankActivate()+"', `rankExpire`='"+p.playerRankExpire()+"', `firstLogin`='"+p.playerFirstLogin()+"' WHERE `playerID`='"+p.playerUniqueId()+"'");
			ps.executeUpdate();
			this.players.remove(p);this.players.add(p);
			ps.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveProfile(UUID id) {
		PlayerConstructor p = getPlayer(id);
		try {

			PreparedStatement ps = this.instance.database.connection.prepareStatement(this.functionsDatabase.replaceAllPlayer(id));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				p.setRankEnum(RankEnum.valueOf(rs.getString("playerRank")));p.setCash(rs.getInt("playerCash"));p.setActived(rs.getLong("rankActived"));p.setExpire(rs.getLong("rankExpire"));p.setOldRank(RankEnum.valueOf(rs.getString("oldRank")));p.setFirstLogin(rs.getLong("firstLogin"));updateProfile(p.playerUniqueId());
				this.players.remove(p);this.players.add(p);
			}
			ps.close();
			rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void shutdownAutoSave() {
		for(Player ps : Bukkit.getOnlinePlayers()) {
			saveProfile(ps.getUniqueId());
		}
	}
	public void hasExpired(PlayerConstructor p) {

		if(p.playerRankExpire() <= System.currentTimeMillis() && p.playerRankExpire() > 0) {
			p.setOldRank(p.playerRank());
			p.setRankEnum(RankEnum.MEMBRO);
			p.setActived(System.currentTimeMillis());
			p.setExpire(-1);
			updateProfile(p.playerUniqueId());
			saveProfile(p.playerUniqueId());
		}
	}

	public API getInstance() {
		return instance;
	}

}
