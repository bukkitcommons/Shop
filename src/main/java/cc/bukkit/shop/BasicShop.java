package cc.bukkit.shop;

import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.feature.Concrete;

/**
 * The parent interface for shops, providing basic trading capable.
 */
public interface BasicShop extends ShopImage, Concrete {
  @NotNull
  ShopType type();

  /**
   * Helper to check shop type.
   * @param shopType type
   * @return whether is that type.
   */
  default boolean is(@NotNull ShopType shopType) {
    return type() == shopType;
  }
}
