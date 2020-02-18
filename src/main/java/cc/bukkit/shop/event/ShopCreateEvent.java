package cc.bukkit.shop.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.BasicShop;
import lombok.Getter;

public class ShopCreateEvent extends ShopEvent implements Cancellable {

  @Getter
  @NotNull
  private final Player player;

  @Getter
  @NotNull
  private final BasicShop shop;

  private boolean cancelled;

  /**
   * Call when have a new shop was createing.
   *
   * @param shop Target shop
   * @param player The player creaing the shop
   */
  public ShopCreateEvent(@NotNull BasicShop shop, @NotNull Player player) {
    this.shop = shop;
    this.player = player;
  }

  @Override
  public boolean isCancelled() {
    return cancelled;
  }

  @Override
  public void setCancelled(boolean cancelled) {
    this.cancelled = cancelled;
  }
}
