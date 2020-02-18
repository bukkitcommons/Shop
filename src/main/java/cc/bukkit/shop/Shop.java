package cc.bukkit.shop;

import cc.bukkit.shop.manager.LocaleManager;
import cc.bukkit.shop.manager.PermissionManager;
import cc.bukkit.shop.manager.ShopActionManager;
import cc.bukkit.shop.manager.ShopLoader;
import cc.bukkit.shop.manager.ShopManager;
import cc.bukkit.shop.manager.ShopMessager;
import cc.bukkit.shop.misc.StackMatcher;

public abstract class Shop {
  private static ShopPlugin plugin;
  
  public static ShopPlugin instance() {
    return plugin;
  }
  
  /**
   * Set the plugin instance as the global accessor.
   * @param plugin the shop plugin.
   * @return success if it haven't been set.
   */
  public synchronized static boolean setPlugin(ShopPlugin plugin) {
    if (Shop.plugin == null) {
      Shop.plugin = plugin;
      return true;
    } else {
      return false;
    }
  }
  
  public static ShopManager getManager() {
    return plugin.getManager();
  }
  
  public static ShopLoader getLoader() {
    return plugin.getLoader();
  }
  
  public static ShopActionManager getActions() {
    return plugin.getActions();
  }
  
  public static StackMatcher getItemMatcher() {
    return plugin.getItemMatcher();
  }
  
  public static ShopMessager getMessager() {
    return plugin.getMessager();
  }
  
  public static LocaleManager getLocaleManager() {
    return plugin.getLocaleManager();
  }
  
  public static PermissionManager getPermissions() {
    return plugin.getPermissions();
  }
  
  public static String getVersion() {
    return plugin.getVersion();
  }
}
