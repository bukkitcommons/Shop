package cc.bukkit.shop.stack;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class StackItem extends GenericStack implements ItemStacked {
  public StackItem(ItemStack stack) {
    super(stack);
  }
  
  public static StackItem of(@NotNull ItemStack object) {
    return new StackItem(object);
  }

  @NotNull
  @Override
  public ItemStack stack() {
    return (ItemStack) stack;
  }
}
