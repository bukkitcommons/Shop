package cc.bukkit.shop;

import org.bukkit.plugin.Plugin;
import cc.bukkit.shop.action.ShopActionManager;

public interface ShopPlugin extends Plugin {
  ShopManager getManager();
  
  ShopLoader getLoader();

  ShopActionManager getActions();

  ShopItemMatcher getItemMatcher();
  
  LocaleManager getLocaleManager();
  
  ShopMessager getMessager();

  String getVersion();
}
