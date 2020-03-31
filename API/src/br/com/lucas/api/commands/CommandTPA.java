package br.com.lucas.api.commands;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.lucas.api.API;

public class CommandTPA implements CommandExecutor{


	private API instance;

	public CommandTPA(API instance) {

		this.instance = instance;
		this.instance.getCommand("tpa").setExecutor(this);

	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {

		if(cmd.getName().equalsIgnoreCase("tpa")) {

			Player p = (Player)sender;

			if(args.length == 0) {

				p.sendMessage("");
				p.sendMessage("§7- §bUse /tpa enviar §8<§7user§8> §b- §7Utilize para enviar um pedido de TPA.");
				p.sendMessage("§7- §bUse /tpa aceitar §8<§7user§8> §b- §7Utilize parar aceitar um pedido de TPA.");
				p.sendMessage("§7- §bUse /tpa recusar §8<§7user§8> §b- §7Utilize para remover o pedido de TPA.");
				p.sendMessage("");

				return true;
			}

			if(args[0].equalsIgnoreCase("enviar")) {

				Player target = Bukkit.getPlayer(args[1]); 
				this.instance.tpaManager.sendTPA(p.getName(), target.getName());
			
				return true;
			}
			else if(args[0].equalsIgnoreCase("recusar")) {

				Player target = Bukkit.getPlayer(args[1]);
					this.instance.tpaManager.recuseTPA(target.getName(), p.getName());
				
				return true;

			}
			else if(args[0].equalsIgnoreCase("aceitar")) {
				Player target = Bukkit.getPlayer(args[1]);
				this.instance.tpaManager.acceptTPA(target.getName(), p.getName());
				
				return true;
			
			}else {

				p.sendMessage("");
				p.sendMessage("§7- §bUse /tpa enviar §8<§7user§8> §b- §7Utilize para enviar um pedido de TPA.");
				p.sendMessage("§7- §bUse /tpa aceitar §8<§7user§8> §b- §7Utilize parar aceitar um pedido de TPA.");
				p.sendMessage("§7- §bUse /tpa recusar §8<§7user§8> §b- §7Utilize para remover o pedido de TPA.");
				p.sendMessage("");
				return true;
			}

		}

		return false;
	}

}
