package cc.bukkit.shop.action;

import org.bukkit.inventory.ItemStack;
import cc.bukkit.shop.util.ShopLocation;

public interface ShopActionData {
  public ShopLocation location();
  
  public ItemStack item();
  
  public ShopAction action();
}