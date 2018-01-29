package de.gamingchain;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		System.out.println("§2Time Server started");
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(World w : Bukkit.getWorlds()) {
					w.setTime(getTime());
				}
			}
		}.runTaskTimer(this, 0, 1);
	}
	
	@Override
	public void onDisable() {
		System.out.println("§cTime Server stopped");
	}
	
	private static Integer getTime() {
		SimpleDateFormat time = new SimpleDateFormat("HH:mm");
		String[] args = time.format(new Date()).split(":");
		Integer hours = Integer.parseInt(args[0])*1000;
		Integer minutes = Integer.parseInt(args[1])*(100/60);
		return hours+minutes+18000;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		if (p.isOp() || p.hasPermission("*")) {
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("__________________________________________________");
			p.sendMessage("§a[TimeServer]");
			p.sendMessage("§6Version: alpha-1.2");
			p.sendMessage("§6Author: CrashKillerYT");
			p.sendMessage("§6Website:http://applecraft.viewdns.net/ & http://www.gamingchain.de/");
			p.sendMessage("§6If you have some problems, inform me at:");
			p.sendMessage("§6crashkillerlps.de@gmail.com");
			p.sendMessage("§cATTENTION : Do first /gamerule dodaylightcycle false !!!");
			p.sendMessage("§bThis plugin is an OpenSource project !");
			p.sendMessage("§bIt means, that you have all rights reserved !");
			p.sendMessage("§bYou have all rights reserved, except of the MIT License !");
			p.sendMessage("§6I wish you much fun with that. Your Faithful CrashKillerYT ! :)");
			p.sendMessage("__________________________________________________");
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("");
		}
		
	}
	
}
