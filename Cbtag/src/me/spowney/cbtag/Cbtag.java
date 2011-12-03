package me.spowney.cbtag;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Cbtag extends JavaPlugin{
	
	public String prefix = "[Cbtag] ";
	
	//hashmaps
	public Map<Player, Player> tagged = new HashMap<Player, Player>();
	public Map<Player, Integer> tagid = new HashMap<Player, Integer>();
	public Map<String, Boolean> punish = new HashMap<String, Boolean>();
	public Map<Player, Boolean> warn = new HashMap<Player, Boolean>();
	
	//listeners
	private final CbtagEntityListener entityListener = new CbtagEntityListener(this);
	private final CbtagPlayerListener playerListener = new CbtagPlayerListener(this);

	@Override
	public void onEnable() {
		
		//Register PluginManager
		PluginManager plman = s.getPluginManager();
				
		//Register Listeners
		plman.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Monitor, this);
		plman.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
		plman.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this);
		
		//ReadConfigs
	//	taggedtime = getConfig().getLong("taggedtime");
	//	respawntime = getConfig().getLong("respawntime");
		
		//buffered read here
		//try {
    		
			//BufferedReader reader = new BufferedReader(new FileReader("combatloggers.txt"));
		///	 String line = null;
			 
	       //     while ((line = reader.readLine()) != null) {
	         //   	punish.put(line, true);
	         //   }
			
			
		//} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		
		l.info(prefix + "CombatTag Enabled.");
	}
	
	@Override
	public void onDisable() {
		
		//persistance and cleanup
		
		l.info(prefix + "CombatTag Disabled.");
	}
	
	public void delayedQuitAction(final Player p, final Player p1)
	{
		s.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

		    public void run() {
		    	if(!p.isOnline())
		    	{
		    		s.broadcastMessage(ChatColor.RED + prefix + ChatColor.GOLD + p.getDisplayName() + ChatColor.GREEN + " was bitch slapped to death by Fuzzy_bot for combat logging");
		    		punish.put(p.getDisplayName(), true);
		    		
		    		//buffered write here
		    		
		    		//get players inventory and give it all to player p1.
		    		for(ItemStack is : p.getInventory().getContents())
		    		{
		    			if(is != null)
		    			{
		    			p1.getLocation().getWorld().dropItemNaturally(p1.getLocation(), is);
		    			}
		    		}
		    		
		    		
		    		
		    		
		    	}
		    	else
		    	{
		    		//disable player for approx 5 seconds
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
	
	public void tagPlayer(Player p, Player p1)
	{
		
			if(tagid.containsKey(p))
			{
			tagid.put(p, tagid.get(p) + 1);
			id = tagid.get(p);
			}
			else
			{
				id = 0;
				tagid.put(p, id);
			}
			if(tagged.get(p) != p1)
			{		
			p.sendMessage(ChatColor.RED + prefix + ChatColor.WHITE + "You were tagged by " + p1.getDisplayName());
			p1.sendMessage(ChatColor.RED + prefix + ChatColor.WHITE + "You tagged " + p.getDisplayName());
			}
			tagged.put(p, p1);
			delayedTagRemove(p, id);
		}
	
	
	Logger l = Logger.getLogger("Minecraft");
	Server s = Bukkit.getServer();
	long taggedtime = 300;
	long respawntime = 200;
	int id;
}
