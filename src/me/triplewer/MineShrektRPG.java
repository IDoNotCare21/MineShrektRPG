package me.triplewer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.logging.Logger;

import me.triplewer.commands.*;
import me.triplewer.listener.Announcer;
import me.triplewer.listener.PlayerListener;

import org.bukkit.Bukkit;

import me.triplewer.listener.*;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MineShrektRPG
  extends JavaPlugin implements Listener
{
	
	
  private final PlayerListener playerListener = new PlayerListener(this);
  private final HashMap<Player, Boolean> debugees = new HashMap();
  public static Server server;
  public static MineShrektRPG plugin;
  public static String pluginName;
  public static String pluginVersion;
  
  public void onLoad()
  {
    plugin = this;
    server = plugin.getServer();
    pluginName = plugin.getDescription().getName();
    pluginVersion = plugin.getDescription().getVersion();
  }
  
  public void onDisable()
  {
    getLogger().info("MineShrektRPG Disabled!");
  }
  
  public void onEnable()
  {
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(this.playerListener, this);
    getLogger().info("Enabling Classes");
    getLogger().info("Enabled Classes");
    getLogger().info("Commands Inititalizing");
    getLogger().info("Enabling Events");
    getLogger().info("Enabled Events");
    getLogger().info("Announcer Enabled");
    getLogger().info("Config Updated");
    getLogger().info("/************************************/");
    getLogger().info("MineShrektRPG Was Created by Triplewer");
    getLogger().info("Plugin was created in Eclipse");
    getLogger().info("/************************************/");
    Bukkit.getServer().getPluginManager().registerEvents(this, this);
    
    
    Announcer.load();

    
    getCommand("cc").setExecutor(new ClearChatCommand());
    getCommand("gban").setExecutor(new BanCommand());
    getCommand("gunban").setExecutor(new UnbanCommand());
    
    PluginDescriptionFile pdfFile = getDescription();
    getLogger().info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
  }
  public static void copy(InputStream in, File file)
    throws IOException
  {
    if (!file.exists()) {
      file.getParentFile().mkdirs();
    }
    OutputStream out = new FileOutputStream(file);
    byte[] buf = new byte[1024];
    int len;
    while ((len = in.read(buf)) > 0) {
      out.write(buf, 0, len);
    }
    out.close();
    in.close();
  }
  
  public static void playerMsg(CommandSender sender, String message, ChatColor color)
  {
    sender.sendMessage(color + message);
  }
  
  public static String colorize(String string)
  {
    return ChatColor.translateAlternateColorCodes('&', string);
  }
  
  public static void playerMsg(CommandSender sender, String message)
  {
    playerMsg(sender, message, ChatColor.GRAY);
  }
  
  public static File getPluginFile(Plugin plugin, String name)
  {
    return new File(plugin.getDataFolder(), name);
  }
  
  public void setDebugging(Player player, boolean value)
  {
    this.debugees.put(player, Boolean.valueOf(value));
  }
}
