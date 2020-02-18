package cc.bukkit.shop.hologram;

import cc.bukkit.shop.ChestShop;

public interface GenericDisplayScheme {
  DisplayType type();
  
  Object scheme(ChestShop stack);
  
  <T> T attribute(DisplayAttribute attr, T defaultValue);
}
