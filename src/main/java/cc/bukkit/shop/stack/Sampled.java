package cc.bukkit.shop.stack;

import org.jetbrains.annotations.NotNull;

public interface Sampled extends Stacked {
  @NotNull
  abstract <S> S sample();
  
  @NotNull
  default <S> S stack() {
    return sample();
  }
}
