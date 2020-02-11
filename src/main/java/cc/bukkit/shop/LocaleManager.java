package cc.bukkit.shop;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import cc.bukkit.shop.ContainerShop;
import cc.bukkit.shop.action.data.ShopSnapshot;

public interface LocaleManager {
  void load() throws InvalidConfigurationException;
  
  LocaleFile getLocale();
  
  String translateBoolean(boolean bool);

  String getLocalizedName(@NotNull Enchantment enchantment);

  String getLocalizedName(@NotNull String itemBukkitName);
  
  String getLocalizedName(@NotNull PotionEffectType potion);

  String getMessage(@NotNull String loc, @Nullable Object player, @NotNull String... args);
  
  String getMessage(@NotNull String loc, @NotNull String... args);

  void sendItemHologram(@NotNull Player player, @NotNull String left, @NotNull ItemStack itemStack, @NotNull String right);

  void sendControlPanelInfo(@NotNull Player sender, @NotNull ContainerShop shop);

  void sendGlobalAlert(@NotNull String content);

  void sendItemholochat(@NotNull ContainerShop shop, @NotNull ItemStack itemStack, @NotNull Player player, @NotNull String normalText);

  void sendMessageToOps(@NotNull String message);

  void sendPurchaseSuccess(@NotNull Player p, @NotNull ContainerShop shop, int amount, @NotNull ShopSnapshot info);

  void sendSellSuccess(@NotNull Player p, @NotNull ContainerShop shop, int amount);

  void sendShopInfo(@NotNull Player p, @NotNull ContainerShop shop);
}
