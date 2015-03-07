package me.triplewer.commands;

import me.triplewer.TripLoader;
import me.triplewer.Trip_Log;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class AdminChatCommand
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
	  Player player = (Player)sender;
	  if(sender instanceof Player)
      {
		  if ((player.hasPermission("mineshrektrpg.*")) && (player.hasPermission("mineshrektrpg.clearchat")) && (((Player)sender).isOp()))
          {
              TripLoader.playerMsg(sender, TripLoader.MSG_NO_PERMS, ChatColor.RED);
              return true;
          }
      }
      if (args.length == 0)
      {
          return false;
      }
      else
      {
          TripLoader.AdminChatMessage(sender, StringUtils.join(args, " "), senderIsConsole);
      }
      return true;
  }
}