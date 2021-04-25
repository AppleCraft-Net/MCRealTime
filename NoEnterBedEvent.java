package net.viewdns.applecraft;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class NoEnterBedEvent implements Listener {
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onEnterBed(PlayerBedEnterEvent e) {
			Player p = e.getPlayer();
			e.setCancelled(true);
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("__________________________________________________");
			p.sendMessage("§a♦ MCRealTime ♦");
			p.sendMessage("");
			p.sendMessage("§cYou can't sleep while real time simulation. Comming maybe soon !");
			p.sendMessage("__________________________________________________");
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage("");
		
	}

}

