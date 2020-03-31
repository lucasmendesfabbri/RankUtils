package br.com.lucas.api.commands;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.lucas.api.API;
import br.com.lucas.api.constructor.PlayerConstructor;
import br.com.lucas.api.enums.RankEnum;
import br.com.lucas.api.packets.PacketsAction;
import br.com.lucas.api.packets.PacketsTitle;

public class CommandRank implements CommandExecutor{

	private API instance;

	public CommandRank(API instance) {
		this.instance = instance;
		this.instance.getCommand("rank").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("rank")) {
			Player p = (Player)sender;
			PlayerConstructor pc = instance.playerManager().getPlayer(p.getUniqueId());

			if(pc.playerRank().equals(RankEnum.MASTER)||p.isOp()) {
				if(args.length == 0) {
					sender.sendMessage("§bUse /rank <player> <cargo> (MENSAL | ANUAL | PERMAMENTE)");
					return true;
				}
				if(args.length > 3) {
					sender.sendMessage("§bUse /rank <player> <cargo> (MENSAL | ANUAL | PERMAMENTE)");
					return true;
				}
				long timer = 0;
				long total = 0;
				long duration = 0;
				Player target = Bukkit.getPlayer(args[0]);
				PlayerConstructor pt = this.instance.playerManager().getPlayer(target.getUniqueId());

				if(!target.isOnline()) {
					sender.sendMessage("§cO jogador está offline!");
					return true;
				}else {

					if(this.instance.playerManager().players.contains(pt)) {
						for(@SuppressWarnings("unused") RankEnum ranks : RankEnum.values()) {
							if(args[1].equalsIgnoreCase(RankEnum.valueOf(args[1].toUpperCase()).toString())) {
								RankEnum prank = RankEnum.valueOf(args[1].toUpperCase());
								if(pt.playerRank()==prank) {
									sender.sendMessage("§cO jogador já pertence ao grupo!");
								}else
									if(prank == RankEnum.MASTER||prank == RankEnum.ADMIN||prank == RankEnum.MEMBRO) {
										if(prank == RankEnum.MASTER&&!p.isOp()) {
											p.setOp(true);
										}
										pt.setOldRank(RankEnum.MEMBRO);
										pt.setActived(System.currentTimeMillis());
										pt.setExpire(-1);
										pt.setRankEnum(prank);
										this.instance.playerManager().hasExpired(pt);
										p.sendMessage("§aVocê adicionou o cargo '"+pt.playerRank()+"' para o player '"+target.getName()+"' que irá expirar " + pt.formatDateExpire()+"§a!" );
										this.instance.playerManager().updateProfile(pt.playerUniqueId());
									}else if(args[2].equalsIgnoreCase("mensal")) {
										if(!prank.isVip()&&prank.isStaff()) {
											sender.sendMessage("§cO Rank não poderá ser MENSAL/ANUAL/PERMAMENTE.");
										}else
											if(prank.isVip()) {

												timer = TimeUnit.MILLISECONDS.convert(12, TimeUnit.SECONDS);
												total = System.currentTimeMillis() + timer; duration = total;
												pt.setOldRank(RankEnum.MEMBRO);
												pt.setRankEnum(prank);
												pt.setActived(System.currentTimeMillis());
												pt.setExpire(duration);
												this.instance.playerManager().updateProfile(pt.playerUniqueId());
												this.instance.playerManager().hasExpired(pt);
												p.sendMessage("§aVocê adicionou o cargo '"+pt.playerRank()+"' para o player '"+target.getName()+"' que irá expirar " + pt.formatDateExpire()+"§a!" );
												for(Player players : Bukkit.getServer().getOnlinePlayers()) {
													new PacketsTitle(players, pt.playerRank().getPrefix() + " " + target.getName(), "§ftornou-se "+pt.playerRank().getPrefix()+"");
													new PacketsAction(pt.playerRank().getPrefix() + " " + target.getName()+" §ftornou-se " +pt.playerRank().getPrefix()+"").sendToPlayer(players);
												}
											}
										return true;

									}else if(args[2].equalsIgnoreCase("anual")) {
										if(!prank.isVip()&&prank.isStaff()) {
											sender.sendMessage("§cO Rank não poderá ser MENSAL/ANUAL/PERMAMENTE.");
										}else {
											if(prank.isVip()) {
												timer = TimeUnit.MILLISECONDS.convert(30, TimeUnit.DAYS)*12;
												total = System.currentTimeMillis() + timer; duration = total;
												pt.setOldRank(RankEnum.MEMBRO);
												pt.setRankEnum(prank);
												pt.setActived(System.currentTimeMillis());
												pt.setExpire(duration);
												this.instance.playerManager().updateProfile(pt.playerUniqueId());
												this.instance.playerManager().hasExpired(pt);
												p.sendMessage("§aVocê adicionou o cargo '"+pt.playerRank()+"' para o player '"+target.getName()+"' que irá expirar " + pt.formatDateExpire()+"§a!" );
												for(Player players : Bukkit.getServer().getOnlinePlayers()) {
													new PacketsTitle(players, pt.playerRank().getPrefix() + " " + target.getName(), "§ftornou-se "+pt.playerRank().getPrefix()+"");
													new PacketsAction(pt.playerRank().getPrefix() + " " + target.getName()+" §ftornou-se " +pt.playerRank().getPrefix()+"").sendToPlayer(players);
												}

											}
										}

									}else if(args[2].equalsIgnoreCase("permamente")) {
										if(!prank.isVip()&&prank.isStaff()) {
											sender.sendMessage("§cO Rank não poderá ser MENSAL/ANUAL/PERMAMENTE.");
										}else {
											if(prank.isVip()) {
												pt.setOldRank(RankEnum.MEMBRO);
												pt.setRankEnum(prank);
												pt.setActived(System.currentTimeMillis());
												pt.setExpire(-1);
												this.instance.playerManager().updateProfile(pt.playerUniqueId());
												this.instance.playerManager().hasExpired(pt);
												p.sendMessage("§aVocê adicionou o cargo '"+pt.playerRank()+"' para o player '"+target.getName()+"' que irá expirar " + pt.formatDateExpire()+"§a!" );
												for(Player players : Bukkit.getServer().getOnlinePlayers()) {
													new PacketsTitle(players, pt.playerRank().getPrefix() + " " + target.getName(), "§ftornou-se "+pt.playerRank().getPrefix()+"");
													new PacketsAction(pt.playerRank().getPrefix() + " " + target.getName()+" §ftornou-se " +pt.playerRank().getPrefix()+"").sendToPlayer(players);
												}
											}
										}
									}else {
										sender.sendMessage("§bUse /rank <player> <cargo> (MENSAL | ANUAL | PERMAMENTE)");
									}
								return true;
							}
						}

						return true;
					}else {
						sender.sendMessage("§cO jogador '"+target.getName()+"' nunca foi registrado.");
						return true;
					}
				}

			}else {
				sender.sendMessage("§cVocê não possui permissão para utilizar o comando /rank!");
			}


		}

		return false;
	}
}
