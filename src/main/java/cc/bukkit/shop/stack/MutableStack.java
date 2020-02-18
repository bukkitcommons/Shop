package cc.bukkit.shop.stack;

import org.jetbrains.annotations.NotNull;

public class MutableStack extends GenericStack implements StackedMutable {
  public MutableStack(@NotNull Object object) {
    super(object);
  }

  public static MutableStack of(@NotNull Object object) {
    return new MutableStack(object);
  }

  @Override
  public void setStack(Object newStock) {
    stack = newStock;
  }
}
