package me.triplewer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MSRPG_Log
{
  private static final Logger FALLBACK_LOGGER = Logger.getLogger("Minecraft-Server");
  private static Logger serverLogger = null;
  private static Logger pluginLogger = null;
  
  private MSRPG_Log()
  {
    throw new AssertionError();
  }
  
  public static void info(String message)
  {
    info(message, Boolean.valueOf(false));
  }
  
  public static void info(String message, Boolean raw)
  {
    log(Level.INFO, message, raw.booleanValue());
  }
  
  public static void info(Throwable ex)
  {
    log(Level.INFO, ex);
  }
  
  public static void warning(String message)
  {
    info(message, Boolean.valueOf(false));
  }
  
  public static void warning(String message, Boolean raw)
  {
    log(Level.WARNING, message, raw.booleanValue());
  }
  
  public static void warning(Throwable ex)
  {
    log(Level.WARNING, ex);
  }
  
  public static void severe(String message)
  {
    info(message, Boolean.valueOf(false));
  }
  
  public static void severe(String message, Boolean raw)
  {
    log(Level.SEVERE, message, raw.booleanValue());
  }
  
  public static void severe(Throwable ex)
  {
    log(Level.SEVERE, ex);
  }
  
  private static void log(Level level, String message, boolean raw)
  {
    getLogger(raw).log(level, message);
  }
  
  private static void log(Level level, Throwable throwable)
  {
    getLogger(false).log(level, null, throwable);
  }
  
  public static void setServerLogger(Logger logger)
  {
    serverLogger = logger;
  }
  
  public static void setPluginLogger(Logger logger)
  {
    pluginLogger = logger;
  }
  
  private static Logger getLogger(boolean raw)
  {
    if ((raw) || (pluginLogger == null)) {
      return serverLogger != null ? serverLogger : FALLBACK_LOGGER;
    }
    return pluginLogger;
  }
}
