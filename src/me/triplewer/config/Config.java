package me.triplewer.config;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import me.triplewer.MineShrektRPG;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Config
  extends YamlConfiguration
{
  private final Plugin plugin;
  private final File configFile;
  private final boolean copyDefaults;
  
  public Config(Plugin plugin, String fileName, boolean copyDefaults)
  {
    this(plugin, MineShrektRPG.getPluginFile(plugin, fileName), copyDefaults);
  }
  
  public Config(Plugin plugin, File file, boolean copyDefaults)
  {
    this.plugin = plugin;
    this.configFile = file;
    this.copyDefaults = copyDefaults;
  }
  
  public void save()
  {
    try
    {
      super.save(this.configFile);
    }
    catch (Exception ex)
    {
      this.plugin.getLogger().severe("Could not save configuration file: " + this.configFile.getName());
      this.plugin.getLogger().severe(ExceptionUtils.getStackTrace(ex));
    }
  }
  
  public void load()
  {
    try
    {
      if (this.copyDefaults)
      {
        if (!this.configFile.exists())
        {
          this.configFile.getParentFile().mkdirs();
          try
          {
            MineShrektRPG.copy(this.plugin.getResource(this.configFile.getName()), this.configFile);
          }
          catch (IOException ex)
          {
            this.plugin.getLogger().severe("Could not write default configuration file: " + this.configFile.getName());
            this.plugin.getLogger().severe(ExceptionUtils.getStackTrace(ex));
          }
          this.plugin.getLogger().info("Installed default configuration " + this.configFile.getName());
        }
        super.addDefaults(getDefaultConfig());
      }
      if (this.configFile.exists()) {
        super.load(this.configFile);
      }
    }
    catch (Exception ex)
    {
      this.plugin.getLogger().severe("Could not load configuration file: " + this.configFile.getName());
      this.plugin.getLogger().severe(ExceptionUtils.getStackTrace(ex));
    }
  }
  
  public YamlConfiguration getConfig()
  {
    return this;
  }
  
  public YamlConfiguration getDefaultConfig()
  {
    YamlConfiguration DEFAULT_CONFIG = new YamlConfiguration();
    try
    {
      DEFAULT_CONFIG.load(this.plugin.getResource(this.configFile.getName()));
    }
    catch (IOException ex)
    {
      this.plugin.getLogger().severe("Could not load default configuration: " + this.configFile.getName());
      this.plugin.getLogger().severe(ExceptionUtils.getStackTrace(ex));
      return null;
    }
    catch (InvalidConfigurationException ex)
    {
      this.plugin.getLogger().severe("Could not load default configuration: " + this.configFile.getName());
      this.plugin.getLogger().severe(ExceptionUtils.getStackTrace(ex));
      return null;
    }
    return DEFAULT_CONFIG;
  }
}
