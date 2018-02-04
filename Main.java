package net.viewdns.applecraft;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.gravitydevelopment.updater.Updater;
import net.gravitydevelopment.updater.Updater.UpdateType;

public class Main extends JavaPlugin implements Listener{
	
	@SuppressWarnings("unused")
	@Override
	public void onEnable() {
		Updater updater = new Updater(this, 286270, getFile(), UpdateType.DEFAULT, true);
		
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
			p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 3, 1);
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("__________________________________________________");
			p.sendMessage("§a[TimeServer]");
			p.sendMessage("§6Version: alpha-1.2");
			p.sendMessage("§6Author: CrashKillerYT");
			p.sendMessage("§6Website:http://applecraft.viewdns.net/");
			p.sendMessage("§6If you have some problems, inform me at:");
			p.sendMessage("§6crashkillerlps.de@gmail.com");
			p.sendMessage("§cATTENTION : Do first /gamerule dodaylightcycle false !!!");
			p.sendMessage("§cYou can not type this command: /time set <time> !");
			p.sendMessage("§bThis plugin is an OpenSource project !");
			p.sendMessage("§bIt means, that you have all rights reserved !");
			p.sendMessage("§bYou have all rights reserved, except of the MIT License !");
			p.sendMessage("§k...§r§b We are now in the beta ! §r§k...");
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
