package de.janscheel.paper;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.janscheel.paper.command.CMD_Home;
import de.janscheel.paper.mysql.MySQL;

public class Homes extends JavaPlugin {

	public static Homes main;
	public MySQL mysql;

	@Override
	public void onEnable() {
		main = this;
		mysql = new MySQL("", "", "", "");
		mysql.Connect();
		Bukkit.getPluginCommand("home").setExecutor(new CMD_Home());
	}
	
	@Override
	public void onDisable() {
		mysql.Disconnect();
	}
	
}
