package me.spowney.cbtag;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

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
	public Map<Player, Integer> tagid = new HashMap<Player, Integer>();
	
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
		plman.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this);
		
		//ReadConfigs
		taggedtime = getConfig().getLong("taggedtime");
		respawntime = getConfig().getLong("respawntime");
		
		
		l.info(prefix + "CombatTag Enabled.");
	}
	
	@Override
	public void onDisable() {
		
		//persistance and cleanup
		
		l.info(prefix + "CombatTag Disabled.");
	}
	
	public void delayedQuitAction(Player p, final Player p1)
	{
		s.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

		    public void run() {
		    	if(!p.isOnline())
		    	{
		    		s.broadcastMessage(p.getDisplayName() + " was assasinated for combat logging");
		    		
		    	}
		    }
		}, respawntime);
	}
	
	public void delayedTagRemove(final Player p, final int id) //
	{
		
		s.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

		    public void run() {
		    	if(id == tagid.get(p))
		    	{
		        tagged.remove(p);
		    	}
		    }
		}, taggedtime);
	}
	
	Logger l = Logger.getLogger("Minecraft");
	Server s = getServer();
	long taggedtime;
	long respawntime;
}
