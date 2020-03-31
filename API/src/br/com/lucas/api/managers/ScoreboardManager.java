package br.com.lucas.api.managers;

import org.bukkit.entity.Player;

import br.com.lucas.api.API;
import br.com.lucas.api.constructor.PlayerConstructor;
import br.com.lucas.api.constructor.ScoreboardConstructor;
import br.com.lucas.api.enums.RankEnum;

public class ScoreboardManager {

	public void setarBoard(Player p){
		ScoreboardConstructor score = new ScoreboardConstructor("§e§lREALM NETWORK");
		
		PlayerConstructor player = API.plugin.playerManager().getPlayer(p.getUniqueId());
		
		String format = player.playerRank()==RankEnum.MEMBRO ? "§7Membro":""+player.playerRank().getPrefix();
		
		score.addLinha("§a        ", 7);
		score.addLinha("§fGrupo: §6" + format, 6);
		score.addLinha("§fGold: §a"+player.playerCash(), 5);
		score.addLinha("§f    ", 4);
		score.addLinha("§fOnline: §a/80", 3);
		score.addLinha("§f ", 2);
		score.addLinha("§ewww.realmnetwork.com.br", 1);
		
		score.setScoreboard(p);
	}
	
	
}
