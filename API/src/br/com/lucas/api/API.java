package br.com.lucas.api;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.lucas.api.commands.CommandRank;
import br.com.lucas.api.commands.CommandTPA;
import br.com.lucas.api.database.Database;
import br.com.lucas.api.listeners.AsyncPlayer;
import br.com.lucas.api.listeners.PlayerJoin;
import br.com.lucas.api.managers.PlayerManager;
import br.com.lucas.api.managers.TPAManager;


public class API extends JavaPlugin
{
	public Database database;
	public PlayerManager pm;
	public TPAManager tpaManager;
	public static API plugin;

	public void onEnable() {
		plugin = this;
		for(Entity e : Bukkit.getWorld("world").getEntities())e.remove();Bukkit.getServer().getWorld("world").setDifficulty(Difficulty.PEACEFUL);Bukkit.getServer().getWorld("world").setPVP(false);
		this.database = new Database();	 
		this.pm = new PlayerManager(this);
		this.tpaManager = new TPAManager(this);
		new CommandRank(this);
		new PlayerJoin(this);
		new AsyncPlayer(this);
		new CommandTPA(this);
	}
	public void onDisable() {

		this.pm.shutdownAutoSave();
		this.database.closeConnection();	
	}
	public PlayerManager playerManager() {
		return pm;
	}

	public boolean deleteWorld(File path) {
	      if(path.exists()) {
	          File files[] = path.listFiles();
	          for(int i=0; i<files.length; i++) {
	              if(files[i].isDirectory()) {
	                  deleteWorld(files[i]);
	              } else {
	                  files[i].delete();
	              }
	          }
	      }
	      return(path.delete());
	}
	

}
