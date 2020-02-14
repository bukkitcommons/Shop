package cc.bukkit.shop;

import org.bukkit.plugin.Plugin;
import cc.bukkit.shop.action.ShopActionManager;
import cc.bukkit.shop.util.version.VersionFetcher;

public interface ShopPlugin extends Plugin {
  ShopManager getManager();
  
  ShopLoader getLoader();

  ShopActionManager getActions();

  ShopItemMatcher getItemMatcher();
  
  LocaleManager getLocaleManager();
  
  ShopMessager getMessager();
  
  PermissionManager getPermissions();

  String getVersion();
}
