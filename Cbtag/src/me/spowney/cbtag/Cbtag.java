package me.spowney.cbtag;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Cbtag extends JavaPlugin{
	
	public String prefix = ChatColor.RED + "[Cbtag] " + ChatColor.WHITE;
	
	//hashmaps
	public Map<Player, Player> tagged = new HashMap<Player, Player>();
	
	//listeners
	private final CbtagEntityListener entityListener = new CbtagEntityListener(this);
	private final CbtagPlayerListener playerListener = new CbtagPlayerListener(this);

	@Override
	public void onEnable() {
		
		//Register PluginManager
		PluginManager plman = s.getPluginManager();
				
		//Register Listeners
		plman.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Normal, this);
		
		plman.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Normal, this);
		plman.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
		
		
		
		l.info(prefix + "CombatTag Enabled.");
	}
	
	@Override
	public void onDisable() {
		
		//persistance and cleanup
		
		l.info(prefix + "CombatTag Disabled.");
	}
	
	public void delayedQuitAction(Player p, Player p1)
	{
		
	}
	
	public void delayedTagRemove(Player p)
	{
		
	}
	
	Logger l = Logger.getLogger("Minecraft");
	Server s = getServer();
}
