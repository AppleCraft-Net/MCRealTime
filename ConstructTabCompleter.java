package net.viewdns.applecraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

public class ConstructTabCompleter implements TabCompleter{

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<String> completions = new ArrayList<>();
		List<String> commands = new ArrayList<>();
		
		if(cmd.getName().equalsIgnoreCase("mcrealtime")) {
			
				Player p = (Player) sender;
				
				if(p.hasPermission("mcrealtime.use")) {
					commands.add("info");
					commands.add("contact");
					commands.add("update");
					commands.add("uninstall");
					commands.add("changelog");
				}
		StringUtil.copyPartialMatches(args[0], commands, completions);
	}
		Collections.sort(completions);
		return completions;

}
}
