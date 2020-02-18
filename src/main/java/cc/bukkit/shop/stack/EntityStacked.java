package cc.bukkit.shop.stack;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public abstract interface EntityStacked extends GenericStacked {
  /**
   * Get shop item's ItemStack
   *
   * @return The shop's ItemStack
   */
  @NotNull
  abstract Entity stack();
}
