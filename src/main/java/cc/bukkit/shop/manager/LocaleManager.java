package cc.bukkit.shop.manager;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import cc.bukkit.shop.BasicShop;
import cc.bukkit.shop.MinecraftLocaleProvider;
import cc.bukkit.shop.action.ShopSnapshot;

public interface LocaleManager extends MinecraftLocaleProvider {
  void load() throws InvalidConfigurationException;
  
  /*
   * Common getters
   */
  String get(@NotNull String key);
  
  /**
   * Translate boolean value to String, the symbon is changeable by language file.
   *
   * @param bool The boolean value
   * @return The result of translate.
   */
  default String get(boolean bool) {
    return get(bool ? "booleanformat.success" : "booleanformat.failed", String.valueOf(bool));
  }
  
  default String get(@NotNull String key, @NotNull String defaultValue) {
    String value = get(key);
    return value == null ? defaultValue : value;
  }

  String get(@NotNull String loc, @Nullable OfflinePlayer player, @NotNull String... args);
  
  String get(@NotNull String loc, @NotNull String... args);

  /*
   * Misc utils
   */
  void sendItemHologram(@NotNull Player player, @NotNull String left, @NotNull ItemStack itemStack, @NotNull String right);

  void sendControlPanelInfo(@NotNull Player sender, @NotNull BasicShop shop);

  void sendGlobalAlert(@NotNull String content);

  void sendItemholochat(@NotNull BasicShop shop, @NotNull ItemStack itemStack, @NotNull Player player, @NotNull String normalText);

  void sendMessageToOps(@NotNull String message);

  void sendPurchaseSuccess(@NotNull Player p, @NotNull BasicShop shop, int amount, @NotNull ShopSnapshot info);

  void sendSellSuccess(@NotNull Player p, @NotNull BasicShop shop, int amount);

  void sendShopInfo(@NotNull Player p, @NotNull BasicShop shop);
  
  /*
   * Minecraft locales
   */
  MinecraftLocaleProvider getMinecraftLocale();
  
  default String get(@NotNull Enchantment enchantment) {
    return getMinecraftLocale().get(enchantment);
  }
  
  default String get(@NotNull PotionEffectType potion) {
    return getMinecraftLocale().get(potion);
  }
  
  default String get(@NotNull Entity entity) {
    return getMinecraftLocale().get(entity);
  }
  
  default String get(@NotNull Material material) {
    return getMinecraftLocale().get(material);
  }
}
