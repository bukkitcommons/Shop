package cc.bukkit.shop.event;

import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.BasicShop;
import cc.bukkit.shop.stack.Stacked;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Called when the price of a shop changes for some reason.
 */
@Getter
@AllArgsConstructor
public class ShopPriceChangeEvent extends ShopEvent implements Cancellable {

  /**
   * The new price that the shop will be set to.
   */
  @Setter
  private Stacked newPrice;

  /**
   * The old price now and before this change.
   */
  private Stacked oldPrice;

  @Setter
  private boolean cancelled;

  @NotNull
  private final BasicShop shop;
  
  @NotNull
  private final Reason reason;

  public enum Reason {
    UNKNOWN,
    RESTRICT;
  }
}
