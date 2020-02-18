package cc.bukkit.shop.hologram;

import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.stack.GenericSampled;

public interface GenericDisplay extends Display, GenericSampled {
  @NotNull
  @Override
  @SuppressWarnings("unchecked")
  default Object stack() {
    return GenericSampled.super.stack();
  }
}
