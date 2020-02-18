package cc.bukkit.shop.feature;

import cc.bukkit.shop.stack.Stack;
import cc.bukkit.shop.stack.Stacked;

public interface Priced {
  Stacked price();

  void setPrice(Stack newPrice);
}
