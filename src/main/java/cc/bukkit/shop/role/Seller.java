package cc.bukkit.shop.role;

import org.bukkit.entity.Player;

public interface Seller {
  /**
   * Performs the selling action for the player with specific amount of stacks. 
   * @param player the player to sell stacks to
   * @param stackAmount the amount of stacks to sell
   */
  void sell(Player player, int stackAmount);
}
