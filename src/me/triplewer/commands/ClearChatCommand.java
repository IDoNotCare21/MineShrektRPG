package me.triplewer.commands;

import me.triplewer.TripLoader;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    Player player = (Player)sender;
    if ((player.hasPermission("mineshrektrpg.*")) && (player.hasPermission("mineshrektrpg.clearchat")) && (((Player)sender).isOp()))
    {
      for (int i = 0; i <= 80; i++) {
        TripLoader.bcastMsg("");
      }
      TripLoader.bcastMsg(ChatColor.BLUE + "Chat has " + ChatColor.AQUA + "been cleared by " + ChatColor.DARK_GREEN + sender.getName());
    }
    else
    {
      sender.sendMessage("You do not have permission to perform this command.");
    }
    return true;
  }
}
