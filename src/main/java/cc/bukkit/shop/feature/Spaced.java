package cc.bukkit.shop.feature;

public interface Spaced extends Spaceable {
  /**
   * Set shop is or not Unlimited Mode (Admin Shop)
   *
   * @param paramBoolean status
   */
  void setUnlimited(boolean paramBoolean);
  
  void fill(int paramInt);
}
