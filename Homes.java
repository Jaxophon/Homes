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
		mysql = new MySQL("193.34.145.203", "kd46679_myplugins", "kd46679_myplugins", "FloNoq29ZOzx");
		mysql.Connect();
		Bukkit.getPluginCommand("home").setExecutor(new CMD_Home());
	}
	
	@Override
	public void onDisable() {
		mysql.Disconnect();
	}
	
}