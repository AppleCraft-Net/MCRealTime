package live.applecraft;

import java.util.List;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class NoEnterBedEvent implements Listener {
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onEnterBed(PlayerBedEnterEvent e) {
			if(Main.config.getBoolean("enable", true)) {
				
				if(Main.config.getBoolean("global", true)) {
					
						e.setCancelled(true);
				} else {
							List<String> affectedworlds = Main.config.getStringList("worlds");
							World playerWorld = e.getPlayer().getWorld();
							
							if (affectedworlds.contains(playerWorld.getName())) {
								e.setCancelled(true);
							} else {
								e.setCancelled(false);
							}
				}
				}
	}
}

