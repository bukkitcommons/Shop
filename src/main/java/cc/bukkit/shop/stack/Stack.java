package cc.bukkit.shop.stack;

import org.jetbrains.annotations.NotNull;

public class Stack extends GenericStack implements GenericStacked {
  public Stack(@NotNull Object object) {
    super(object);
  }

  public static Stack of(@NotNull Object object) {
    return new Stack(object);
  }
}
