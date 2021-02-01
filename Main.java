package net.viewdns.applecraft;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin implements CommandExecutor{
	
	public String prefix = "§a♦ MCRealTime" + " §2v" + getDescription().getVersion() + " §a♦";
	
	//@SuppressWarnings("unused")
	@Override
	public void onEnable() {
		System.out.println("§2TimeServer started !");
		//Updater updater = new Updater(this, 286270, getFile(), Updater.UpdateType.DEFAULT, true);
		getCommand("mcrealtime").setExecutor(this);
		getCommand("mcrealtime").setTabCompleter(new ConstructTabCompleter());
		Bukkit.getPluginManager().registerEvents(new NoEnterBedEvent(), this);
		
		saveDefaultConfig();
		getConfig().options().copyDefaults(true);
		
		
		if(getConfig().getBoolean("enable", true)) {
			
		if(getConfig().getBoolean("global", true)) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(World global : Bukkit.getWorlds()) {
					global.setTime(getTime());
					global.setGameRuleValue("doDaylightCycle", "false");
				}
			}
		}.runTaskTimer(this, 0, 1);
		
		} else {
			
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
					List<String> arrayworlds = getConfig().getStringList("worlds");
					
					for(String name : arrayworlds) {
						Bukkit.getWorld(name).setTime(getTime());
						Bukkit.getWorld(name).setGameRuleValue("doDaylightCycle", "false");
					}
				}
			}.runTaskTimer(this, 0, 1);
		}
		}
	}
	
	@Override
	public void onDisable() {
		System.out.println("§cTimeServer stopped !");
		
		for(World w : Bukkit.getWorlds()) {
			w.setGameRuleValue("doDaylightCycle", "true");
		}
	}
	
	private static Integer getTime() {
		SimpleDateFormat time = new SimpleDateFormat("HH:mm");
		String[] args = time.format(new Date()).split(":");
		Integer hours = Integer.parseInt(args[0])*1000;
		Integer minutes = Integer.parseInt(args[1])*(100/60);
		return hours+minutes+18000;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		
		if(p.hasPermission("mcrealtime.use")) {
			if(args.length != 1) {
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("__________________________________________________");
				p.sendMessage(prefix);
				p.sendMessage("");
				p.sendMessage("§6Here is a list with all avaible commands: ");
				p.sendMessage("§c/mcrealtime info §r- §7You'll get important information to this plugin");
				p.sendMessage("§c/mcrealtime contact §r- §7You'll get my contact information");
				p.sendMessage("§c/mcrealtime changelog §r- §7You'll get all changes about the last update");
				p.sendMessage("§c/mcrealtime uninstall §r- §7Prepare MCRealTime for uninstallation");
				p.sendMessage("__________________________________________________");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
			}else if(args[0].equalsIgnoreCase("info")) {
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("__________________________________________________");
				p.sendMessage(prefix);
				p.sendMessage("");
				p.sendMessage("§6Authors: §a" + getDescription().getAuthors());
				p.sendMessage("§6Website: §a" + getDescription().getWebsite());
				p.sendMessage("§6You will get more information with this command:");
				p.sendMessage("§b/mcrealtime contact");
				p.sendMessage("§cYou can not type this command: /time set <time> !");
				p.sendMessage("§bThis plugin is an OpenSource project !");
				p.sendMessage("§bIt means, that you have all rights reserved !");
				p.sendMessage("§bYou have all rights reserved in case of the MIT License !");
				p.sendMessage("§6I wish you much fun with that. Yours Faithfuly Dev_Gabriel_M ! :)");
				p.sendMessage("");
				p.sendMessage("§bPS: Take a look in the config file for more settings.");
				p.sendMessage("__________________________________________________");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
			}else if(args[0].equalsIgnoreCase("contact")) {
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("__________________________________________________");
				p.sendMessage(prefix);
				p.sendMessage("");
				p.sendMessage("§6You have some problems or you want post a feedback ?");
				p.sendMessage("§6No problem ! You could me send your feedback on the issue site of:");
				p.sendMessage("§6https://dev.bukkit.org/projects/mcrealtime/issues");
				p.sendMessage("");
				p.sendMessage("§6If you have some problems, you can contact me under:");
				p.sendMessage("§6E-Mail: §agabriel.malaka@freenet.de");
				p.sendMessage("§bSometimes on my teamspeak 3: §ats.gabriel-malaka.ga");
				p.sendMessage("§6Thanks for understanding !");
				p.sendMessage("§6See ya around :)");
				p.sendMessage("__________________________________________________");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
			}else if(args[0].equalsIgnoreCase("uninstall")) {
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("__________________________________________________");
				p.sendMessage(prefix);
				p.sendMessage("§6Made by §a" + getDescription().getAuthors());
				p.sendMessage("§6Description of the plugin: §a" + getDescription().getDescription());
				p.sendMessage("");
				p.sendMessage("§6Uninstall:");
				p.sendMessage("§c1. Shutdown the local server.");
				p.sendMessage("§c2. Remove MCRealTime from the plugins folder of your server.");
				p.sendMessage("§c3. That's it ! The gamerule DoDayLightCycle is automatically set on true again !");
				p.sendMessage("");
				p.sendMessage("§6Thank you for download and please give me your opportunity as feedback at https://dev.bukkit.org/projects/mcrealtime for permitting me to get better.");
				p.sendMessage("__________________________________________________");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
			}else if(args[0].equalsIgnoreCase("changelog")) {
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("__________________________________________________");
				p.sendMessage(prefix);
				p.sendMessage("§6Made by §a" + getDescription().getAuthors());
				p.sendMessage("§6Description of the plugin: §a" + getDescription().getDescription());
				p.sendMessage("");
				p.sendMessage("§6Changelogs:");
				p.sendMessage("§2+ Added (Craftbukkit/Bukkit/Spigot) version support from 1.8 to 1.16.5");
				p.sendMessage("§2+ Added command tab completer");
				p.sendMessage("__________________________________________________");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
		}
} else {
	p.sendMessage("");
	p.sendMessage("");
	p.sendMessage("");
	p.sendMessage("");
	p.sendMessage("");
	p.sendMessage("__________________________________________________");
	p.sendMessage(prefix);
	p.sendMessage("");
	p.sendMessage("§cYou do not have permissions to perform this command !");
	p.sendMessage("__________________________________________________");
	p.sendMessage("");
	p.sendMessage("");
	p.sendMessage("");
	p.sendMessage("");
	p.sendMessage("");
}
		return true;
	}
}
