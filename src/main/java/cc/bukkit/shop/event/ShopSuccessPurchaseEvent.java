package cc.bukkit.shop.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.BasicShop;
import lombok.Getter;

public class ShopSuccessPurchaseEvent extends ShopEvent implements Cancellable {
  @Getter
  @NotNull
  public final BasicShop shop;
  @Getter
  private final int stackAmount;
  @Getter
  @NotNull
  private final Player player;
  @Getter
  private final double tax;

  private final double total; // Don't use getter, we have important notice need told dev in
                              // javadoc.

  private boolean cancelled;

  /**
   * Builds a new shop purchase event This time, purchase not start, please listen the
   * ShopSuccessPurchaseEvent.
   *
   * @param shop The shop bought from
   * @param player The player buying
   * @param amount The amount they're buying
   * @param tax The tax in this purchase
   * @param total The money in this purchase
   */
  public ShopSuccessPurchaseEvent(@NotNull BasicShop shop, @NotNull Player player, int stackAmount,
      double total, double tax) {
    this.shop = shop;
    this.player = player;
    this.stackAmount = stackAmount;
    this.tax = tax;
    this.total = total;
  }

  /**
   * The total money changes in this purchase. Calculate tax, if you want get total without tax,
   * please use getBalanceWithoutTax()
   *
   * @return the total money with calculate tax
   */
  public double getBalance() {
    return this.total * (1 - tax);
  }

  /**
   * The total money changes in this purchase. No calculate tax, if you want get total with tax,
   * please use getBalance()
   *
   * @return the total money without calculate tax
   */
  public double getBalanceWithoutTax() {
    return this.total;
  }

  @Override
  public boolean isCancelled() {
    return this.cancelled;
  }

  @Override
  public void setCancelled(boolean cancelled) {
    this.cancelled = cancelled;
  }
}
