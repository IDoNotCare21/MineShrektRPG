package me.triplewer.listener;

import me.triplewer.MineShrektRPG;
import me.triplewer.TripLoader;
import me.triplewer.config.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.scheduler.BukkitRunnable;

public class Announcer
{
  private static final List<String> ANNOUNCEMENTS = new ArrayList();
  private static boolean enabled;
  private static long interval;
  private static String prefix;
  private static BukkitRunnable announcer;
  
  private Announcer()
  {
    throw new AssertionError();
  }
  
  public static boolean isEnabled()
  {
    return enabled;
  }
  
  public static List<String> getAnnouncements()
  {
    return Collections.unmodifiableList(ANNOUNCEMENTS);
  }
  
  public static long getTickInterval()
  {
    return interval;
  }
  
  public static String getPrefix()
  {
    return prefix;
  }
  
  public static void load()
  {
    stop();
    
    ANNOUNCEMENTS.clear();
    for (Object announcement : ConfigEntry.ANNOUNCER_ANNOUNCEMENTS.getList()) {
      ANNOUNCEMENTS.add(MineShrektRPG.colorize((String)announcement));
    }
    enabled = ConfigEntry.ANNOUNCER_ENABLED.getBoolean().booleanValue();
    interval = ConfigEntry.ANNOUNCER_INTERVAL.getInteger().intValue() * 20L;
    prefix = MineShrektRPG.colorize(ConfigEntry.ANNOUNCER_PREFIX.getString());
    if (enabled) {
      start();
    }
  }
  
  public static boolean isStarted()
  {
    return announcer != null;
  }
  
  public static void start()
  {
    if (isStarted()) {
      return;
    }
    announcer = new BukkitRunnable()
    {
      private int current = 0;
      
      public void run()
      {
        this.current += 1;
        if (this.current >= Announcer.ANNOUNCEMENTS.size()) {
          this.current = 0;
        }
        TripLoader.bcastMsg(Announcer.prefix + (String)Announcer.ANNOUNCEMENTS.get(this.current));
      }
    };
    announcer.runTaskTimer(MineShrektRPG.plugin, interval, interval);
  }
  
  public static void stop()
  {
    if (announcer == null) {
      return;
    }
    try
    {
      announcer.cancel();
      


      announcer = null;
    }
    finally
    {
      announcer = null;
    }
  }
}
