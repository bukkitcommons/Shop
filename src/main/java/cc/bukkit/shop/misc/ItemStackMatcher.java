package cc.bukkit.shop.misc;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;
import cc.bukkit.shop.stack.ItemStacked;
import cc.bukkit.shop.stack.Stacked;

public interface ItemStackMatcher extends StackMatcher {
  boolean matches(@Nullable ItemStack requireStack, @Nullable ItemStack givenStack);

  boolean matches(@Nullable ItemStack requireStack, @Nullable ItemStack givenStack, boolean matchesAmount);

  boolean equalsCommon(ItemMeta meta, ItemMeta that);
  
  boolean compareCustomModelData(ItemMeta meta, ItemMeta that);
  
  boolean comparePersistentDataContainer(ItemMeta meta, ItemMeta that);
  
  boolean matches(ItemMeta originMeta, ItemMeta testMeta);

  boolean compareUncommon(ItemMeta origin, ItemMeta test);
  
  /*
   * Overriding
   */
  @Override
  default boolean matches(@Nullable Stacked requireStack, @Nullable Stacked givenStack) {
    if (requireStack instanceof ItemStacked && givenStack instanceof ItemStacked)
      return matches(requireStack.<ItemStack>stack(), givenStack.<ItemStack>stack());
    else
      return false;
  }

  @Override
  default boolean matches(@Nullable Stacked requireStack, @Nullable Stacked givenStack, boolean matchesAmount) {
    if (requireStack instanceof ItemStacked && givenStack instanceof ItemStacked)
      return matches(requireStack.<ItemStack>stack(), givenStack.<ItemStack>stack(), matchesAmount);
    else
      return false;
  }
}
