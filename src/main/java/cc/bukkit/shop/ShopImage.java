package cc.bukkit.shop;

import cc.bukkit.shop.feature.Owned;
import cc.bukkit.shop.feature.Priced;
import cc.bukkit.shop.feature.Spaceable;
import cc.bukkit.shop.stack.Stacked;

/**
 * Image of shops, or a shop-like object, which performs like a shop.
 */
public interface ShopImage extends Stacked, Priced, Owned, Spaceable {}
