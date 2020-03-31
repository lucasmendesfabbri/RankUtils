package br.com.lucas.api.constructor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardConstructor {

	private Objective obj;
	private Scoreboard sb;

	private String gerarTimeNome(Scoreboard sb) {
		String timeNome = "time";
		timeNome = timeNome + sb.getTeams().size() + 1;
		return timeNome;
	}
	
	public ScoreboardConstructor(String titulo) {
		this.sb = Bukkit.getScoreboardManager().getNewScoreboard();
		this.obj = sb.registerNewObjective("scoreboardutils", "dummy");
		obj.setDisplayName(titulo);
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
	}
	
	@SuppressWarnings("deprecation")
	public void addLinha(String texto, int linha) {
		int n = texto.length();
		Score s = null;
		Team t = null;
		if (n <= 16) {
			s = obj.getScore(Bukkit.getOfflinePlayer(texto));
		} else if (n > 16 && n < 32) {
			t = sb.registerNewTeam(gerarTimeNome(sb));
			t.setPrefix(texto.substring(0, 16));
			String nome = texto.substring(16);
			t.addPlayer(Bukkit.getOfflinePlayer(nome));
			s = obj.getScore(Bukkit.getOfflinePlayer(nome));
		} else if (n > 32) {
			t = sb.registerNewTeam(gerarTimeNome(sb));
			t.setPrefix(texto.substring(0, 16));
			t.setSuffix(texto.substring(32));
			String nome = texto.substring(16, 32);
			t.addPlayer(Bukkit.getOfflinePlayer(nome));
			s = obj.getScore(Bukkit.getOfflinePlayer(nome));
		}
		s.setScore(linha);
	}
	
	public Scoreboard getScoreboard() {
		return sb;
	}
	
	public void setScoreboard(Player p) {
		p.setScoreboard(sb);
	}
	
	public void setScoreboard(Player... p) {
		for (Player pl : p)
			pl.setScoreboard(sb);
	}
}
