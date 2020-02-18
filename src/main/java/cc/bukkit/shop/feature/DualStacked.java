package cc.bukkit.shop.feature;

import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.stack.Stacked;

public interface DualStacked extends Stacked {
  boolean isBuying(Object stack);
  
  boolean isSelling(Object stack);

  @NotNull
  <I> I getBuying();
  
  @NotNull
  <O> O getSelling();
}
