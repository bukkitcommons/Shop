package cc.bukkit.shop.manager;

import java.util.UUID;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.action.ShopActionData;
import cc.bukkit.shop.action.ShopCreator;
import cc.bukkit.shop.action.ShopSnapshot;
import cc.bukkit.shop.buyer.BuyerShop;
import cc.bukkit.shop.seller.SellerShop;

public interface ShopActionManager {
  boolean hasAction(@NotNull UUID player);
  
  ShopActionData getAction(@NotNull UUID player);
  
  void setAction(@NotNull UUID player, @NotNull ShopActionData data);
  
  void removeAction(@NotNull UUID player);

  boolean buy(
      @NotNull Player p,
      @NotNull String message,
      @NotNull BuyerShop shop, int stackAmount,
      @NotNull ShopSnapshot info);

  void create(
      @NotNull Player p,
      @NotNull ShopCreator info,
      @NotNull String message,
      boolean bypassProtectionChecks);

  void sell(
      @NotNull Player p,
      @NotNull String message,
      @NotNull SellerShop shop, int stackAmount,
      @NotNull ShopSnapshot info);

  void trade(
      @NotNull Player p,
      @NotNull ShopSnapshot info,
      @NotNull String message);

  void handleChat(@NotNull Player p, @NotNull String msg, boolean sync);

  void handleChat(@NotNull Player p, @NotNull String msg, boolean bypassProtectionChecks, boolean sync);

  void clear();
}
