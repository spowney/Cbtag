package me.spowney.cbtag;

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
		
	}
	
	public void onPlayerQuit(PlayerQuitEvent e)
	{
		
	}

}
