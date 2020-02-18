package cc.bukkit.shop.role;

import org.bukkit.entity.Player;

public interface Buyer {
  /**
   * Performs the buying action for the player with specific amount of stacks. 
   * @param player the player to buy stacks from
   * @param stackAmount the amount of stacks to buy
   */
  void buy(Player player, int stackAmount);
}
