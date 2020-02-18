package cc.bukkit.shop.stack;

import org.jetbrains.annotations.NotNull;

public interface GenericSampled extends GenericStacked {
  @NotNull
  abstract Object sample();
  
  @NotNull
  default Object stack() {
    return sample();
  }
}
