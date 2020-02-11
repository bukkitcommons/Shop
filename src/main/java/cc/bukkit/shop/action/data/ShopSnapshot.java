package cc.bukkit.shop.action.data;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.ContainerShop;
import cc.bukkit.shop.Shop;
import cc.bukkit.shop.ShopType;
import cc.bukkit.shop.action.ShopAction;
import cc.bukkit.shop.action.ShopActionData;
import cc.bukkit.shop.moderator.ShopModerator;
import cc.bukkit.shop.util.ShopLocation;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class ShopSnapshot implements ShopActionData {
  private final ItemStack item;
  private final ShopLocation location;
  private final ShopModerator moderator;
  private final double price;
  private final ShopType shopType;
  private final boolean unlimited;
  
  public ShopSnapshot(@NotNull ContainerShop shop) {
    item = shop.getItem();
    location = shop.getLocation();
    moderator = shop.getModerator();
    price = shop.getPrice();
    shopType = shop.getShopType();
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
        shopType != shop.getShopType() ||
        price != shop.getPrice() ||
        !moderator.equals(shop.getModerator()) ||
        !shop.getLocation().equals(shop.getLocation()) ||
        !Shop.getItemMatcher().matches(item, shop.getItem());
  }

  @Override
  public ShopAction action() {
    return ShopAction.TRADE;
  }
}
