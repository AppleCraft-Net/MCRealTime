package net.viewdns.applecraft;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import net.viewdns.applecraft.Updater.ReleaseType;

public class Main extends JavaPlugin implements CommandExecutor{

	public static FileConfiguration config;
	
	public static String prefix;
	public static List<String> authors;
	public static String website;
	public static String description;
	public static String dversion;
	
	public static String timezoneconfig;
	
	public static int x_update = 0;
	public static Updater updater;
	public static boolean update;
	public static Updater autoupdater;
	public static boolean autoupdate;
	public static String name;
	public static ReleaseType type;
	public static String version;
	public static String link;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		config = getConfig();
		config.options().copyDefaults(true);
		
		timezoneconfig = config.getString("timezone");
		dversion = getDescription().getVersion();
		authors = getDescription().getAuthors();
		website = getDescription().getWebsite();
		description = getDescription().getDescription();
		prefix = "§a♦ MCRealTime" + " §2v" + dversion + " §a♦ ";
		
		
		if(config.getBoolean("auto_update", true)){
			autoupdater = new Updater(this, 286270, getFile(), Updater.UpdateType.DEFAULT, true);
			autoupdate = autoupdater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE;
			
			if(autoupdate) {
				Bukkit.getConsoleSender().sendMessage("__________________________________________________");
				Bukkit.getConsoleSender().sendMessage(prefix + " " + ChatColor.GREEN + "by " + Main.authors);
				Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "The Update " + Main.name + ", Type: " + Main.type + " for " + Main.version + "(+) was updated successfully!");
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Please restart your minecraft server!");
				Bukkit.getConsoleSender().sendMessage("__________________________________________________");
			}
		}else {
			updater = new Updater(this, 286270, getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
			update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE;
			
			if(update){
				Bukkit.getConsoleSender().sendMessage("__________________________________________________");
				Bukkit.getConsoleSender().sendMessage(prefix + " " + ChatColor.GREEN + "by " + Main.authors);
				Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "An update is avaible: " + Main.name + ", Type: " + Main.type + " for " + Main.version + "(+)" + " avaible at " + Main.link);
				Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Type /mcrealtime update if you would like to automatically update.");
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Before you run the command, please open your server console.");
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "After the update finsish, please restart your minecraft server!");
				Bukkit.getConsoleSender().sendMessage("__________________________________________________");
			}
		}
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + "TimeServer started!");
		updater = new Updater(this, 286270, getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
		name = updater.getLatestName();
		version = updater.getLatestGameVersion();
		type = updater.getLatestType();
		link = updater.getLatestFileLink();
		
		getCommand("mcrealtime").setExecutor(this);
		getCommand("mcrealtime").setTabCompleter(new ConstructTabCompleter());
		
		Bukkit.getPluginManager().registerEvents(new NoEnterBedEvent(), this);
		Bukkit.getPluginManager().registerEvents(new UpdateNotifyJoinEvent(), this);
		
		if(config.getBoolean("enable", true)) {
			
		if(config.getBoolean("global", true)) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(World mcworlds : Bukkit.getWorlds()) {
					mcworlds.setTime(getTime());
					mcworlds.setGameRuleValue("doDaylightCycle", "false");
					mcworlds.setGameRuleValue("doInsomnia", "false");
				}
			}
		}.runTaskTimer(this, 0, 1);
		
		} else {
			
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
					List<String> arrayworlds = config.getStringList("worlds");
					
					for(String mcworlds : arrayworlds) {
						Bukkit.getWorld(mcworlds).setTime(getTime());
						Bukkit.getWorld(mcworlds).setGameRuleValue("doDaylightCycle", "false");
						Bukkit.getWorld(mcworlds).setGameRuleValue("doInsomnia", "false");
					}
				}
			}.runTaskTimer(this, 0, 1);
		}
		}
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "TimeServer stopped!");
		
		for(World mcworlds : Bukkit.getWorlds()) {
			mcworlds.setGameRuleValue("doDaylightCycle", "true");
			mcworlds.setGameRuleValue("doInsomnia", "true");
		}
	}
	
	private static Integer getTime() {
		SimpleDateFormat time = new SimpleDateFormat("HH:mm");
		TimeZone tz = TimeZone.getTimeZone(timezoneconfig);
		time.setTimeZone(tz);
		String[] args = time.format(new Date()).split(":");
		Integer hours = Integer.parseInt(args[0])*1000;
		Integer minutes = Integer.parseInt(args[1])*(100/60);
		return hours+minutes+18000;
	}
	
	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		
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
				p.sendMessage("§c/mcrealtime update §r- §7ATTENTION! BEFORE RUN, TAKE A LOOK IN YOUR SERVER CONSOLE! Running an auto update.");
				p.sendMessage("§c/mcrealtime changelog §r- §7You'll get all changes about the last update");
				p.sendMessage("§c/mcrealtime uninstall §r- §7Prepare MCRealTime for uninstallation");
				p.sendMessage("__________________________________________________");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
			}else if(args[0].equalsIgnoreCase("info")) {
				if(!p.hasPermission(config.getString("Permissions.info"))) {
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
				}else {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("__________________________________________________");
					p.sendMessage(prefix);
					p.sendMessage("");
					p.sendMessage("§6Authors: §a" + authors);
					p.sendMessage("§6Website: §a" + website);
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
				}
			}else if(args[0].equalsIgnoreCase("contact")) {
				if(!p.hasPermission(config.getString("Permissions.contact"))) {
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
				}else {
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
				p.sendMessage("§6E-Mail: §acrashkilleryt@freenet.de");
				p.sendMessage("§6Thanks for understanding !");
				p.sendMessage("§6See ya around :)");
				p.sendMessage("__________________________________________________");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				}
			}else if(args[0].equalsIgnoreCase("uninstall")) {
				if(!p.hasPermission(config.getString("Permissions.uninstall"))) {
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
				}else {
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("__________________________________________________");
				p.sendMessage(prefix);
				p.sendMessage("§6Made by §a" + authors);
				p.sendMessage("§6Description of the plugin: §a" + description);
				p.sendMessage("");
				p.sendMessage("§6Uninstall:");
				p.sendMessage("§c1. Shutdown the local server.");
				p.sendMessage("§c2. Remove MCRealTime from the plugins folder of your server.");
				p.sendMessage("§c3. That's it ! The gamerules DoDayLightCycle and doInsomnia are automatically set on true again !");
				p.sendMessage("");
				p.sendMessage("§6Thank you for download and please give me your opportunity as feedback at https://dev.bukkit.org/projects/mcrealtime for permitting me to get better.");
				p.sendMessage("__________________________________________________");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				}
			}else if(args[0].equalsIgnoreCase("changelog")) {
				if(!p.hasPermission(config.getString("Permissions.changelog"))) {
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
				}else {
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("__________________________________________________");
				p.sendMessage(prefix);
				p.sendMessage("§6Made by §a" + authors);
				p.sendMessage("§6Description of the plugin: §a" + description);
				p.sendMessage("");
				p.sendMessage("§6Changelogs:");
				p.sendMessage("§a+ Added version support 1.8 - 1.17");
				p.sendMessage("§a+ Added update to cmd /mcrealtime update");
				p.sendMessage("__________________________________________________");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				}
			}else if(args[0].equalsIgnoreCase("update")) {
				if(!p.hasPermission(config.getString("Permissions.update"))) {
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
				}else {
					if(update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE) {
						if(config.getBoolean("auto_update", true)){
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("__________________________________________________");
							p.sendMessage(prefix);
							p.sendMessage("§6Made by §a" + authors);
							p.sendMessage("§cYou can not do that!");
							p.sendMessage("§cAuto update is activated!");
							p.sendMessage("__________________________________________________");
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("");
						}else if(x_update == 0){
							x_update = 1;
							Updater updater = new Updater(this, 286270, getFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("__________________________________________________");
							p.sendMessage(prefix);
							p.sendMessage("§6Made by §a" + authors);
							p.sendMessage("§6The update is running!");
							p.sendMessage("§6Look in your console. If the update is finished, please restart your minecraft server.");
							p.sendMessage("__________________________________________________");
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("");
						} else {
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage("__________________________________________________");
							p.sendMessage(prefix);
							p.sendMessage("§6Made by §a" + authors);
							p.sendMessage("§aUpdate is running... Check your console!");
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
						p.sendMessage("§6Made by §a" + authors);
						p.sendMessage("§aMCRealTime is up to date!");
						p.sendMessage("__________________________________________________");
						p.sendMessage("");
						p.sendMessage("");
						p.sendMessage("");
						p.sendMessage("");
						p.sendMessage("");
					}
				}
				}
		return true;
	}
}
