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

public class UnbanCommand
  implements CommandExecutor
  {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
	  Player player = (Player)sender;
	  if (args.length == 0) {
          sender.sendMessage(ChatColor.RED + "Please specify a player!");
          return true;
 }
	  Player target = Bukkit.getServer().getPlayer(args[0]);
	  if (target == null) {
	          sender.sendMessage(ChatColor.RED + "Could not find player in the ban list" + args[0] + "!");
	          return true;
 {
  player.chat("/gunban" + target.getName());
  target.setBanned(false);
  Bukkit.getServer().broadcastMessage(ChatColor.RED + sender.getName() + ChatColor.YELLOW + " has unbanned " + target.getName());
  }
 }
}