package cc.bukkit.shop.feature;

public interface Spaceable {
  /**
   * Get shop remaining space.
   *
   * @return Remaining space.
   */
  int getRemainingSpace();

  /**
   * Get shop remaining stock.
   *
   * @return Remaining stock.
   */
  int getRemainingStock();

  /**
   * Get shop is or not in Unlimited Mode (Admin Shop)
   *
   * @return yes or not
   */
  boolean isUnlimited();
}
