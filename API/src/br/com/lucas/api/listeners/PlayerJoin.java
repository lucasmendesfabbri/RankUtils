package br.com.lucas.api.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.lucas.api.API;
import br.com.lucas.api.constructor.PlayerConstructor;
import br.com.lucas.api.managers.PlayerManager;
import br.com.lucas.api.managers.ScoreboardManager;

public class PlayerJoin implements Listener{

	private API instance;
	private PlayerManager playerManager;
	public HashMap<Player, String> score = new HashMap<Player, String>();

	public PlayerJoin(API instance) {
		this.instance = instance;
		this.playerManager = this.instance.pm;
		Bukkit.getPluginManager().registerEvents(this, this.instance);
	}

	@EventHandler
	void playerRegistered(PlayerJoinEvent event) {

		Player ep = event.getPlayer();
		event.setJoinMessage(null);
		PlayerConstructor p = this.playerManager.getPlayer(ep.getUniqueId());
		if(!this.playerManager.players.contains(p)) {
			this.instance.playerManager().createProfile(ep.getUniqueId());
		}else {
			this.instance.playerManager().hasExpired(p);
		}
		score.put(event.getPlayer(), "ativado");
		new ScoreboardManager().setarBoard(event.getPlayer());
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

}
