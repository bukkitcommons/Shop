package cc.bukkit.shop.stack;

import org.jetbrains.annotations.NotNull;

public interface StackedMutable extends GenericStacked {
  void setStack(@NotNull Object newStack);
}
