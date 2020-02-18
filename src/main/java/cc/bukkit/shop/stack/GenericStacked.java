package cc.bukkit.shop.stack;

import org.jetbrains.annotations.NotNull;

public abstract interface GenericStacked extends Stacked {
  /**
   * Get shop item's ItemStack
   *
   * @return The shop's ItemStack
   */
  @NotNull
  @SuppressWarnings("unchecked")
  abstract Object stack();
}
