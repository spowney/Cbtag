package me.spowney.cbtag;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class CbtagPlayerListener extends PlayerListener{
	
	public static Cbtag plugin;
	
	public CbtagPlayerListener(Cbtag instance) {
        plugin = instance;
	}
	
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		//assasinate players who log in from combat logging.
	}
	
	public void onPlayerQuit(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		if(!p.hasPermission("cbtag.exempt") || !p.isOp())
		{
			if(plugin.tagged.containsKey(p))
			{
				plugin.delayedQuitAction(p, plugin.tagged.get(p));
			}
		}
	}

}
