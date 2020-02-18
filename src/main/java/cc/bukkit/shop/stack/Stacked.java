package cc.bukkit.shop.stack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Stacked {
  /**
   * Get shop item's ItemStack
   *
   * @return The shop's ItemStack
   */
  @NotNull
  abstract <S> S stack();
  
  default boolean isStack(@Nullable Object that) {
    return stack().equals(that);
  }
}
