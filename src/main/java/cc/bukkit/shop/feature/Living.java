package cc.bukkit.shop.feature;

/**
 * A shop which capable of living,
 * the loading and unloading decide its behavior.
 */
public interface Living {
  /**
   * Checks whether the shop is living.
   * @return the status
   */
  boolean isLoaded();
  
  /**
   * Loads the shop, brings it living.
   */
  void load();

  /**
   * Unloads the shop, brings it not living anymore.
   */
  void unload();
}
