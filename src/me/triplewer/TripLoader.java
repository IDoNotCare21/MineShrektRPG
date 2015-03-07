package me.triplewer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.triplewer.MineShrektRPG;

public abstract class TripLoader
{
  public static final String MSG_NO_PERMS = ChatColor.YELLOW + "You do not have permission to use this command.";
  public static final String YOU_ARE_OP = ChatColor.YELLOW + "You are now op!";
  public static final String YOU_ARE_NOT_OP = ChatColor.YELLOW + "You are no longer op!";
  public static final String NOT_FROM_CONSOLE = "This command may not be used from the console.";
  public static final String PLAYER_NOT_FOUND = ChatColor.GRAY + "Player not found!.";
  public static final String SPECIFY_NAME = ChatColor.GRAY + "Please specify a name!";
  public static final String NO_COMMAND_PERMS = "Command {0} does not have permissions set!";
  protected MineShrektRPG plugin;
  protected Server server;
  private CommandSender commandSender;
  private Class<?> commandClass;
  
  public abstract boolean run(CommandSender paramCommandSender, Player paramPlayer, Command paramCommand, String paramString, String[] paramArrayOfString, boolean paramBoolean);
  
  public void setup(MineShrektRPG plugin, CommandSender commandSender, Class<?> commandClass)
  {
    this.plugin = plugin;
    this.server = plugin.getServer();
    this.commandSender = commandSender;
    this.commandClass = commandClass;
  }
  
  public static void bcastMsg(String message, ChatColor color)
  {
    Trip_Log.info(message, Boolean.valueOf(true));
    for (Player player : Bukkit.getOnlinePlayers()) {
      player.sendMessage((color == null ? "" : color) + message);
    }
  }
  
  public static void AdminChatMessage(CommandSender sender, String message, boolean senderIsConsole)
  {
      Trip_Log.info("[Admin] " + sender.getName() + ": " + message);

      for (Player player : Bukkit.getOnlinePlayers())
      {
    	  if ((player.hasPermission("mineshrektrpg.*")) && (player.hasPermission("mineshrektrpg.adminchat")) && (((Player)sender).isOp()))
          {
              player.sendMessage(ChatColor.YELLOW + "[" + ChatColor.BLUE + "Admin Chat" + ChatColor.YELLOW + "] " + ChatColor.DARK_RED + player.getName() + ": " + ChatColor.LIGHT_PURPLE + message);
          }
      }
  }
  public static void bcastMsg(String message)
  {
    bcastMsg(message, null);
  }
  
  public static void playerMsg(CommandSender sender, String message, ChatColor color)
  {
    sender.sendMessage(color + message);
  }
  
  public static void playerMsg(CommandSender sender, String message)
  {
    playerMsg(sender, message, ChatColor.GRAY);
  }
  
  public static void adminAction(String adminName, String action, boolean isRed)
  {
    bcastMsg(adminName + " - " + action, isRed ? ChatColor.RED : ChatColor.YELLOW);
  }
  
  public void playerMsg(String message)
  {
    playerMsg(this.commandSender, message);
  }
  
  public Player getPlayer(String partialName)
  {
    return getPlayer(partialName, false);
  }
  
  public Player getPlayer(String partialName, boolean exact)
  {
    if ((partialName == null) || (partialName.isEmpty())) {
      return null;
    }
    Player[] players = this.server.getOnlinePlayers();
    for (Player player : players) {
      if (partialName.equalsIgnoreCase(player.getName())) {
        return player;
      }
    }
    if (exact) {
      return null;
    }
    for (Player player : players) {
      if (player.getName().toLowerCase().contains(partialName.toLowerCase())) {
        return player;
      }
    }
    for (Player player : players) {
      if (player.getDisplayName().toLowerCase().contains(partialName.toLowerCase())) {
        return player;
      }
    }
    return null;
  }
}
