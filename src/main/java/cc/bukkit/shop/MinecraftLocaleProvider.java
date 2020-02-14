package cc.bukkit.shop;

import java.io.IOException;
import java.util.Optional;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public interface MinecraftLocaleProvider {
  void load(@NotNull String locale) throws IOException;
  
  /**
   * Get item and block translations, if not found, it will both call getBlock()
   *
   * @param material The material
   * @return The translations for material
   */
  @NotNull
  String get(@NotNull Material material);

  /**
   * Get entity translations.
   *
   * @param entity The entity name
   * @return The translations for entity
   */
  @NotNull
  String get(@NotNull Entity entity);

  /**
   * Get potion/effect translations.
   *
   * @param effect The potion effect name
   * @return The translations for potion effect
   */
  @NotNull
  String get(@NotNull PotionEffectType effect);

  /**
   * Get enchantment translations.
   *
   * @param enchantmentName The enchantment name
   * @return The translations for enchantment
   */
  @NotNull
  String get(@NotNull Enchantment enchantment);
}
