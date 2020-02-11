package cc.bukkit.shop;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

public interface ShopItemMatcher {
  boolean matches(@Nullable ItemStack requireStack, @Nullable ItemStack givenStack);

  boolean matches(@Nullable ItemStack requireStack, @Nullable ItemStack givenStack, boolean matchesAmount);

  boolean equalsCommon(ItemMeta meta, ItemMeta that);
  
  boolean compareCustomModelData(ItemMeta meta, ItemMeta that);
  
  boolean comparePersistentDataContainer(ItemMeta meta, ItemMeta that);
  
  boolean matches(ItemMeta originMeta, ItemMeta testMeta);

  boolean compareUncommon(ItemMeta origin, ItemMeta test);
}
