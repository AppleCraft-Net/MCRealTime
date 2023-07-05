package live.applecraft;

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
				if(p.hasPermission(Main.config.getString("Permissions.admin")) && autoupdate){
					p.sendMessage("__________________________________________________");
					p.sendMessage(Main.prefix + " " + ChatColor.GREEN + "by " + Main.authors);
					p.sendMessage(ChatColor.GOLD + "The Update " + Main.name + ", Type: " + Main.type + " for " + Main.version + "(+) was updated successfully!");
					p.sendMessage(ChatColor.RED + "Please restart your minecraft server!");
					p.sendMessage("__________________________________________________");
				}
			}else{
				update = Main.updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE;
				if(p.hasPermission(Main.config.getString("Permissions.admin")) && update){
					p.sendMessage("__________________________________________________");
					p.sendMessage(Main.prefix + " " + ChatColor.GREEN + "by " + Main.authors);
					p.sendMessage(ChatColor.GOLD + "An update is avaible: " + Main.name + ", Type: " + Main.type + " for " + Main.version + "(+)" + " avaible at " + Main.link);
					p.sendMessage(ChatColor.GOLD + "Type /mcrealtime update if you would like to automatically update.");
					p.sendMessage(ChatColor.RED + "Before you run the command, please open your server console.");
					p.sendMessage(ChatColor.RED + "After the update finsish, please restart your minecraft server!");
					p.sendMessage("__________________________________________________");
				}
			}
	}
}
