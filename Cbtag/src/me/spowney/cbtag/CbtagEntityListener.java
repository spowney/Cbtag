package me.spowney.cbtag;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

public class CbtagEntityListener extends EntityListener{

	public static Cbtag plugin;
	
	public CbtagEntityListener(Cbtag instance) {
        plugin = instance;
	}
	
	public void onEntityDamage(EntityDamageEvent e)
	{
		if(e instanceof EntityDamageByEntityEvent)
		{
			EntityDamageByEntityEvent e1 = (EntityDamageByEntityEvent) e;
			
			if(e.getEntity() instanceof Player)
			{
				Player p = (Player) e.getEntity();
				
				if(e1.getDamager() instanceof Player) //Player damaged by other player
				{
					Player p1 = (Player) e1.getDamager();
					plugin.tagged.put(p, p1);
					
					p.sendMessage(plugin.prefix + "You were tagged by " + p1.getDisplayName());
					p1.sendMessage(plugin.prefix + "You tagged " + p.getDisplayName());
					
					plugin.delayedTagRemove(p);
				}
				else if(e1.getDamager() instanceof Projectile) //Player damaged by Arrow
				{
					Projectile pr = (Projectile) e1.getDamager();
					if(pr.getShooter() instanceof Player) //Arrow shot by a Player
					{
						Player p1 = (Player) pr.getShooter();
						plugin.tagged.put(p, p1);
						
						p.sendMessage(plugin.prefix + "You were tagged by " + p1.getDisplayName());
						p1.sendMessage(plugin.prefix + "You tagged " + p.getDisplayName());
						
						plugin.delayedTagRemove(p);
					}
				}
			}
		}
		
	}
	
	public void onEntityDeath(EntityDeathEvent e)
	{
		if(e.getEntity() instanceof Player)
		{
			Player p = (Player) e.getEntity();
			
			plugin.tagged.remove(p);  //remove player from tagged map
		}
	}
	
}
