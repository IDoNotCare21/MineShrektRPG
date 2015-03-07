package me.triplewer.listener;

import java.util.logging.Logger;

import me.triplewer.MineShrektRPG;
import me.triplewer.TripLoader;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

public class PlayerListener
  implements Listener
{
  private final MineShrektRPG plugin;
  
  public PlayerListener(MineShrektRPG instance)
  {
    this.plugin = instance;
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event)
  {
    Player player = event.getPlayer();
    String username = event.getPlayer().getName();
    this.plugin.getLogger().info(event.getPlayer().getName() + " joined the server! :D");
    if (username.equalsIgnoreCase("Triplewer")) {
      TripLoader.bcastMsg(ChatColor.RED + "Triplewer" + ChatColor.AQUA + " is the " + ChatColor.BLUE + "Owner" + ChatColor.AQUA + " and the " + ChatColor.GREEN + "Creator of the MineShrektRPG Plugin");
    }
  }
  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent e) 
  {
    if(plugin.getConfig().getBoolean("Enable-SwearBlocker") == true) {
    for (String word : e.getMessage().split(" ")) {
    if (plugin.getConfig().getStringList("Swear Words").contains(word)) {
    e.setCancelled(true);
    e.getPlayer().sendMessage(ChatColor.RED + plugin.getConfig().getString("SwearMessage"));
    }
  }
 }
}
  @EventHandler
  public void onPlayerJoin1(PlayerJoinEvent event) 
  {
    if(plugin.getConfig().getBoolean("MOTD-Enable") == true) {
    Player p = event.getPlayer();
    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MOTD")));
    }
  }
  @EventHandler
  public void onPlayerJoin2(PlayerJoinEvent event) 
  {
	if(plugin.getConfig().getBoolean("Visit Player Count") == true) {
    TripLoader.bcastMsg(ChatColor.DARK_BLUE + plugin.getConfig().getString("prefix") + ChatColor.AQUA + "There Has Been " + ChatColor.GREEN + plugin.getConfig().getString("Player Visits") + "Unique players that have joined the server.");
    }
  }
}
