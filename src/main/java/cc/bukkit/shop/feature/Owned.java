package cc.bukkit.shop.feature;

import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.moderator.Managed;
import cc.bukkit.shop.moderator.ShopModerator;

public interface Owned extends Managed {
  /**
   * Get shop's owner name, it will return owner name or Admin Shop(i18n) when it is unlimited
   *
   * @return owner name
   */
  @NotNull
  String ownerName();
  
  @NotNull
  ShopModerator moderator();
}
