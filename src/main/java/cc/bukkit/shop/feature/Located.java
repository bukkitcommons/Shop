package cc.bukkit.shop.feature;

import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.misc.ShopLocation;

public interface Located {
  /**
   * Gets the location of the shop block.
   * @return the shop location
   */
  @NotNull
  ShopLocation location();
}
