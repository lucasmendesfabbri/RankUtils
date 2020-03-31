package br.com.lucas.api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandProfile implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if(!(sender instanceof Player))return true;
		
		if(cmd.getName().equalsIgnoreCase("profile")||cmd.getName().equalsIgnoreCase("perfil")) {
			
			
			
			return true;			
		}
		
		return false;
	}

	
	
}
