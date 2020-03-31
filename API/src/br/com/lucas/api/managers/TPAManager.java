package br.com.lucas.api.managers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.com.lucas.api.API;
import br.com.lucas.api.constructor.TPAConstructor;

public class TPAManager {

	private API instance;
	public HashMap<String, TPAConstructor> tpaPlayer;
	private String formatDate;


	public TPAManager(API instance) {
		this.instance = instance;
		this.tpaPlayer = new HashMap<String, TPAConstructor>();
	}

	public void sendTPA(String send, String to) {

		TPAConstructor tpa = new TPAConstructor(send, to, Bukkit.getPlayer(to).getLocation(), TimeUnit.MILLISECONDS.convert(60, TimeUnit.SECONDS) + System.currentTimeMillis());		
		Player psend = Bukkit.getPlayer(send); Player top = Bukkit.getPlayer(to);
		if(tpaPlayer.containsKey(to)) {
			if(tpaPlayer.get(to).getSend() == send) {
				tpa = new TPAConstructor(tpaPlayer.get(to).getSend(), tpaPlayer.get(to).getTo(), tpaPlayer.get(to).getToLoc(), tpaPlayer.get(to).getExpireDate());
				this.formatDate = new SimpleDateFormat("yyyy mm:ss").format(new Date(tpa.getExpireDate()));
				psend.sendMessage("§cVocê enviou um convite recentemente ele irá expirar em §8[§f"+this.formatDate+"§8] §cagurde.");
			}else {
				tpaPlayer.put(tpa.getTo(), tpa);
				this.formatDate = new SimpleDateFormat("yyyy mm:ss").format(new Date(tpa.getExpireDate()));
				psend.sendMessage("§aVocê enviou um pedido de §lTPA§a para o player §8(§f"+tpa.getTo()+"§8)§a com sucesso.");
				top.sendMessage("");
				top.sendMessage("§e§lTPA");
				top.sendMessage("");
				top.sendMessage("§7- §eUm pedido de §lTPA§e foi enviado para você!");
				top.sendMessage("§7- §eUtilize §2/TPA ACEITAR §e'"+tpa.getSend()+"'");
				top.sendMessage("§7- §eUtilize §c/TPA RECUSAR §e'"+tpa.getSend()+"'");
				top.sendMessage("§7- §eO pedido irá expirar em §8[§7"+formatDate+"§8]");
				top.sendMessage("");
			}
		}else {
			tpaPlayer.put(tpa.getTo(), tpa);
			this.formatDate = new SimpleDateFormat("yyyy mm:ss").format(new Date(tpa.getExpireDate()));
			psend.sendMessage("§aVocê enviou um pedido de §lTPA§a para o player §8(§f"+tpa.getTo()+"§8)§a com sucesso.");
			top.sendMessage("");
			top.sendMessage("§e§lTPA");
			top.sendMessage("");
			top.sendMessage("§7- §eUm pedido de §lTPA§e foi enviado para você!");
			top.sendMessage("§7- §eUtilize §2/TPA ACEITAR §e'"+tpa.getSend()+"'");
			top.sendMessage("§7- §eUtilize §c/TPA RECUSAR §e'"+tpa.getSend()+"'");
			top.sendMessage("§7- §eO pedido irá expirar em §8[§7"+formatDate+"§8]");
			top.sendMessage("");
		}
	}


	public void recuseTPA(String send, String to) {


			if(this.instance.tpaManager.tpaPlayer.containsKey(to)) {
				if(this.instance.tpaManager.tpaPlayer.get(to).getSend() == send) {
					this.tpaPlayer.remove(to);
					Bukkit.getPlayer(to).sendMessage("§aO pedido de §lTPA§a do player §8(§7" + send + "§8) §a foi cancelado.");
					Bukkit.getPlayer(send).sendMessage("§cO §8(§7" + to + "§8)§c cancelou seu pedido de §lTPA§c.");
				}else {
					Bukkit.getPlayer(to).sendMessage("§cO §8(§7"+send+"§8)§c não enviou nenhum pedido para você.");
				}
			}else {
				Bukkit.getPlayer(to).sendMessage("§cO §8(§7"+send+"§8)§c não enviou nenhum pedido para você.");
			}

	}
	public void acceptTPA(String send, String to) {


			if(this.instance.tpaManager.tpaPlayer.containsKey(to)) {
				if(this.instance.tpaManager.tpaPlayer.get(to).getSend() == send) {
					Bukkit.getPlayer(send).teleport(Bukkit.getPlayer(to).getLocation());
					this.tpaPlayer.remove(to);
					Bukkit.getPlayer(to).sendMessage("§aO pedido de §lTPA§a do player §8(§7" + send + "§8) §a foi aceitado.");
					Bukkit.getPlayer(send).sendMessage("§aO §8(§7" + to + "§8)§a aceitou seu pedido de §lTPA§c.");

				}else {
					Bukkit.getPlayer(to).sendMessage("§cO §8(§7"+send+"§8)§c não enviou nenhum pedido para você.");
				}
			}else {
				Bukkit.getPlayer(to).sendMessage("§cO §8(§7"+send+"§8)§c não enviou nenhum pedido para você.");

		}}

	@SuppressWarnings("unlikely-arg-type")
	public void checkAutomaticRemove() {
		for(Player ps : Bukkit.getOnlinePlayers()) {
			TPAConstructor top = this.tpaPlayer.get(ps.getName());
			if(this.tpaPlayer.containsKey(ps.getName())) {
				if(top.getTo() == ps.getName()) {
					if(top.getExpireDate() <= System.currentTimeMillis()) {
						this.tpaPlayer.remove(top);
					}
				}
			}
		}
	}



	public API getInstance() {
		return instance;
	}

}
