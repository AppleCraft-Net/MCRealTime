package net.viewdns.applecraft;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateNotifyJoinEvent implements Listener{
	
	public static boolean autoupdate;
	public static boolean update;
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
			if(Main.config.getBoolean("auto_update", true)){
				autoupdate = Main.autoupdater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE;
				if(p.hasPermission("mcrealtime.use") && autoupdate){
					p.sendMessage("__________________________________________________");
					p.sendMessage(Main.prefix + " " + ChatColor.GREEN + "by " + Main.authors);
					p.sendMessage("§6The Update " + Main.name + ", Type: " + Main.type + " for " + Main.version + "(+) was updated successfully!");
					p.sendMessage("§cPlease restart your minecraft server!");
					p.sendMessage("__________________________________________________");
				}
			}else{
				update = Main.updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE;
				if(p.hasPermission("mcrealtime.use") && update){
					p.sendMessage("__________________________________________________");
					p.sendMessage(Main.prefix + " " + ChatColor.GREEN + "by " + Main.authors);
					p.sendMessage("§6An update is avaible: " + Main.name + ", Type: " + Main.type + " for " + Main.version + "(+)" + " avaible at " + Main.link);
					p.sendMessage("§6Type /mcrealtime update if you would like to automatically update.");
					p.sendMessage("§cBefore you run the command, please open your server console.");
					p.sendMessage("§cAfter the update finsish, please restart your minecraft server!");
					p.sendMessage("__________________________________________________");
				}
			}
	}
}
