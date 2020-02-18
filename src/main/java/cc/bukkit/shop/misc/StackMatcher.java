package cc.bukkit.shop.misc;

import org.jetbrains.annotations.Nullable;
import cc.bukkit.shop.stack.Stacked;

public interface StackMatcher {
  boolean matches(@Nullable Stacked requireStack, @Nullable Stacked givenStack);

  boolean matches(@Nullable Stacked requireStack, @Nullable Stacked givenStack, boolean matchesAmount);
}
