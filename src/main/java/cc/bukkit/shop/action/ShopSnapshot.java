package cc.bukkit.shop.action;

import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.BasicShop;
import cc.bukkit.shop.ContainerShop;
import cc.bukkit.shop.Shop;
import cc.bukkit.shop.ShopType;
import cc.bukkit.shop.misc.ShopLocation;
import cc.bukkit.shop.moderator.ShopModerator;
import cc.bukkit.shop.stack.Stack;
import cc.bukkit.shop.stack.Stacked;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class ShopSnapshot implements ShopActionData {
  private final Stacked stack;
  private final ShopLocation location;
  private final ShopModerator moderator;
  private final Stacked price;
  private final ShopType shopType;
  private final boolean unlimited;
  
  public ShopSnapshot(@NotNull BasicShop shop) {
    stack = Stack.of(shop.stack());
    location = shop.location();
    moderator = shop.moderator();
    price = Stack.of(shop.price());
    shopType = shop.type();
    unlimited = shop.isUnlimited();
  }

  /**
   * Get shop is or not has changed.
   *
   * @param shop, The need checked with this shop.
   * @return hasChanged
   */
  public boolean hasChanged(@NotNull ContainerShop shop) {
    return
        unlimited != shop.isUnlimited() ||
        shopType != shop.type() ||
        !price.equals(shop.price()) ||
        !moderator.equals(shop.moderator()) ||
        !shop.location().equals(shop.location()) ||
        !Shop.getItemMatcher().matches(stack.stack(), Stack.of(shop.stack())); // FIXME weird
  }

  @Override
  public ShopAction action() {
    return ShopAction.TRADE;
  }
}
