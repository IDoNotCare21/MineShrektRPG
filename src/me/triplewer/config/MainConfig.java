package me.triplewer.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.List;

import me.triplewer.MineShrektRPG;
import net.minecraft.util.org.apache.commons.io.FileUtils;
import me.triplewer.MSRPG_Log;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class MainConfig
{
  public static final String CONFIG_FILENAME = "config.yml";
  public static final File CONFIG_FILE = new File(MineShrektRPG.plugin.getDataFolder(), "config.yml");
  private static final EnumMap<ConfigEntry, Object> ENTRY_MAP = new EnumMap(ConfigEntry.class);
  
  static
  {
    try
    {
      try
      {
        InputStream defaultConfig = getDefaultConfig();
        TFM_Config_DefaultsLoader defaultsLoader = new TFM_Config_DefaultsLoader(defaultConfig);
        for (ConfigEntry entry : ConfigEntry.values()) {
          ENTRY_MAP.put(entry, defaultsLoader.get(entry.getConfigName()));
        }
        defaultConfig.close();
      }
      catch (IOException ex) {}
      copyDefaultConfig(CONFIG_FILE);
      
      load();
    }
    catch (Exception ex) {}
  }
  
  private MainConfig()
  {
    throw new AssertionError();
  }
  
  public static final void load()
  {
    try
    {
      YamlConfiguration config = new YamlConfiguration();
      
      config.load(CONFIG_FILE);
      for (ConfigEntry entry : ConfigEntry.values())
      {
        String path = entry.getConfigName();
        if (config.contains(path))
        {
          Object value = config.get(path);
          if ((value == null) || (entry.getType().isAssignableFrom(value.getClass()))) {
            ENTRY_MAP.put(entry, value);
          }
        }
      }
    }
    catch (FileNotFoundException ex) {}catch (IOException ex) {}catch (InvalidConfigurationException ex) {}
  }
  
  public static String getString(ConfigEntry entry)
  {
    try
    {
      return (String)get(entry, String.class);
    }
    catch (IllegalArgumentException ex) {}
    return null;
  }
  
  public static void setString(ConfigEntry entry, String value)
  {
    try
    {
      set(entry, value, String.class);
    }
    catch (IllegalArgumentException ex) {}
  }
  
  public static Double getDouble(ConfigEntry entry)
  {
    try
    {
      return (Double)get(entry, Double.class);
    }
    catch (IllegalArgumentException ex) {}
    return null;
  }
  
  public static void setDouble(ConfigEntry entry, Double value)
  {
    try
    {
      set(entry, value, Double.class);
    }
    catch (IllegalArgumentException ex) {}
  }
  
  public static Boolean getBoolean(ConfigEntry entry)
  {
    try
    {
      return (Boolean)get(entry, Boolean.class);
    }
    catch (IllegalArgumentException ex) {}
    return null;
  }
  
  public static void setBoolean(ConfigEntry entry, Boolean value)
  {
    try
    {
      set(entry, value, Boolean.class);
    }
    catch (IllegalArgumentException ex) {}
  }
  
  public static Integer getInteger(ConfigEntry entry)
  {
    try
    {
      return (Integer)get(entry, Integer.class);
    }
    catch (IllegalArgumentException ex) {}
    return null;
  }
  
  public static void setInteger(ConfigEntry entry, Integer value)
  {
    try
    {
      set(entry, value, Integer.class);
    }
    catch (IllegalArgumentException ex) {}
  }
  
  public static List getList(ConfigEntry entry)
  {
    try
    {
      return (List)get(entry, List.class);
    }
    catch (IllegalArgumentException ex) {}
    return null;
  }
  
  public static <T> T get(ConfigEntry entry, Class<T> type)
    throws IllegalArgumentException
  {
    Object value = ENTRY_MAP.get(entry);
    try
    {
      return type.cast(value);
    }
    catch (ClassCastException ex)
    {
      throw new IllegalArgumentException(entry.name() + " is not of type " + type.getSimpleName());
    }
  }
  
  public static <T> void set(ConfigEntry entry, T value, Class<T> type)
    throws IllegalArgumentException
  {
    if (!type.isAssignableFrom(entry.getType())) {
      throw new IllegalArgumentException(entry.name() + " is not of type " + type.getSimpleName());
    }
    if ((value != null) && (!type.isAssignableFrom(value.getClass()))) {
      throw new IllegalArgumentException("Value is not of type " + type.getSimpleName());
    }
    ENTRY_MAP.put(entry, value);
  }
  
  private static void copyDefaultConfig(File targetFile)
  {
    if (targetFile.exists()) {
      return;
    }
    try
    {
      InputStream defaultConfig = getDefaultConfig();
      FileUtils.copyInputStreamToFile(defaultConfig, targetFile);
      defaultConfig.close();
    }
    catch (IOException ex) {}
  }
  
  private static InputStream getDefaultConfig()
  {
    return MineShrektRPG.plugin.getResource("config.yml");
  }
  
  private static class TFM_Config_DefaultsLoader
  {
    private YamlConfiguration defaults = null;
    
    private TFM_Config_DefaultsLoader(InputStream defaultConfig)
    {
      try
      {
        this.defaults = new YamlConfiguration();
        this.defaults.load(defaultConfig);
      }
      catch (IOException ex) {}catch (InvalidConfigurationException ex) {}
    }
    
    public Object get(String path)
    {
      return this.defaults.get(path);
    }
  }
}
