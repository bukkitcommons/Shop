package cc.bukkit.shop.hologram;

import cc.bukkit.shop.ChestShop;

public interface DisplayScheme {
  DisplayType type();
  
  <S> S scheme(ChestShop stack);
  
  <T> T attribute(DisplayAttribute attr, T defaultValue);
}
