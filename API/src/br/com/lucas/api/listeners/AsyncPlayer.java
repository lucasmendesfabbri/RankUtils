package br.com.lucas.api.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import br.com.lucas.api.API;
import br.com.lucas.api.constructor.PlayerConstructor;
import br.com.lucas.api.enums.RankEnum;
import br.com.lucas.api.packets.JSON_CHAT;
import net.md_5.bungee.api.chat.ClickEvent.Action;

public class AsyncPlayer implements Listener{

	private API instance;

	public AsyncPlayer(API instance) {
		this.instance = instance;
		Bukkit.getPluginManager().registerEvents(this, this.instance);
	}

	@EventHandler
	void playerChat(AsyncPlayerChatEvent e) {
		e.setCancelled(true);

		PlayerConstructor p = this.instance.playerManager().getPlayer(e.getPlayer().getUniqueId());
		for(Player ps : Bukkit.getServer().getOnlinePlayers()) {
				String rankFormat = (p.playerRank() == RankEnum.MEMBRO ? "§7Membro" : "" + p.playerRank().getPrefix());
				String formatMessage = (p.playerRank() == RankEnum.MEMBRO ? "§7"+e.getPlayer().getName()+": " +e.getMessage() : "" + p.playerRank().getPrefix().toUpperCase()+" " + e.getPlayer().getName()+ "§7: §f" + e.getMessage());
				new JSON_CHAT().chatMessage(ps, formatMessage, "§7Cargo: "+rankFormat.toUpperCase(), "/profile " + e.getPlayer().getName(), Action.RUN_COMMAND, net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT);
		}
	}

	public API getInstance() {
		return instance;
	}

}
