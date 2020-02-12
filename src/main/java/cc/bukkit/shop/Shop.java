package cc.bukkit.shop;

import cc.bukkit.shop.action.ShopActionManager;

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
    Shop.plugin = plugin;
    return true;
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
  
  public static ShopItemMatcher getItemMatcher() {
    return plugin.getItemMatcher();
  }
  
  public static ShopMessager getMessager() {
    return plugin.getMessager();
  }
  
  public static LocaleManager getLocaleManager() {
    return plugin.getLocaleManager();
  }
  
  public static String getVersion() {
    return plugin.getVersion();
  }
}
