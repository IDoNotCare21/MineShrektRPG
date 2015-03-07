package me.triplewer.commands;

import me.triplewer.listener.*;
import me.triplewer.TripLoader;
import me.triplewer.listener.EnforcerEvent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
	  if (args.length == 0) {
          sender.sendMessage(ChatColor.RED + "Please specify a player!");
          return true;
  }
  Player target = Bukkit.getServer().getPlayer(args[0]);
  if (target == null) {
          sender.sendMessage(ChatColor.RED + "Could not find player " + args[0] + "!");
          return true;
  }
  target.kickPlayer(ChatColor.RED + "You have been banned!");
  target.setBanned(true);
  Bukkit.getServer().getPluginManager().callEvent(new EnforcerEvent(target, Type.BAN));
  Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "Player " + target.getName() + " has been banned by " + sender.getName() + "!");
  return true;
  }
}