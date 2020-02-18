package cc.bukkit.shop.action;

import cc.bukkit.shop.misc.ShopLocation;
import cc.bukkit.shop.stack.Stacked;

public interface ShopActionData {
  public ShopLocation location();
  
  public <S extends Stacked> S stack();
  
  public ShopAction action();
}
