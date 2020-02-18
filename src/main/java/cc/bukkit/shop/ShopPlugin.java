package cc.bukkit.shop;

import org.bukkit.plugin.Plugin;
import cc.bukkit.shop.manager.LocaleManager;
import cc.bukkit.shop.manager.PermissionManager;
import cc.bukkit.shop.manager.ShopActionManager;
import cc.bukkit.shop.manager.ShopLoader;
import cc.bukkit.shop.manager.ShopManager;
import cc.bukkit.shop.manager.ShopMessager;
import cc.bukkit.shop.misc.StackMatcher;

public interface ShopPlugin extends Plugin {
  ShopManager getManager();
  
  ShopLoader getLoader();

  ShopActionManager getActions();

  StackMatcher getItemMatcher();
  
  LocaleManager getLocaleManager();
  
  ShopMessager getMessager();
  
  PermissionManager getPermissions();

  String getVersion();
}
