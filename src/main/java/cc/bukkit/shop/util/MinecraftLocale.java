package cc.bukkit.shop.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import cc.bukkit.shop.MinecraftLocaleProvider;
import cc.bukkit.shop.logger.ShopLogger;
import cc.bukkit.shop.util.file.ResourceAccessor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MinecraftLocale implements MinecraftLocaleProvider {
  @NotNull
  private final Map<String, String> locales = Maps.newHashMap();
  @NotNull
  private final File container;
  
  public void load(@NotNull String locale) throws IOException {
    File cacheFile = new File(container, "lang.cache");
    Files.createParentDirs(cacheFile);
    boolean refetch = cacheFile.createNewFile();

    /*
     * Lookup cache
     */
    YamlConfiguration cache = YamlConfiguration.loadConfiguration(container);
    String cachedVersion = cache.getString("ver", "");
    String cachedLocale = cache.getString("lang", "");
    String cachedHash = cache.getString("hash", "");
    
    String serverVersion = Reflections.getServerVersion();
    
    refetch = refetch ||
        !serverVersion.equals(cachedVersion) ||
        locale.equals(cachedLocale) ||
        cachedHash.isEmpty();
    
    String[] info = StringUtils.split(locale, '_');
    locale = info[0].toLowerCase(Locale.ROOT).concat("_").concat(info[1].toLowerCase(Locale.ROOT));
    
    /*
     * Fetch lang file
     */
    if (refetch) {
      MojangAPI mojangAPI = new MojangAPI();
      String assetJson = mojangAPI.getAssetIndexJson(serverVersion);

      if (assetJson != null) {
        AssetJson versionJson = new AssetJson(assetJson);
        String jsonLangHash = versionJson.getLanguageHash(locale);

        if (jsonLangHash != null) {
          String langJson = mojangAPI.downloadTextFileFromMojang(container, jsonLangHash);

          if (langJson != null) {
            ResourceAccessor.save(new ByteArrayInputStream(langJson.getBytes(StandardCharsets.UTF_8)), new File(container, jsonLangHash));

            cache.set("ver", serverVersion);
            cache.set("hash", jsonLangHash);
            cache.set("lang", locale);
            cache.save(container);
          } else {
            ShopLogger.instance().warning("Cannot download texts for " + jsonLangHash);
          }
          
          cachedHash = jsonLangHash;
        } else {
          ShopLogger.instance().warning("Cannot find hash for " + locale);
        }
      } else {
        ShopLogger.instance().warning("Cannot download asset json for " + serverVersion);
      }
      
      ShopLogger.instance().warning(
          "Cannot download required files, names of items may will display in the default locale: " + locale);
    }
    
    /*
     * Loads lang file
     */
    @NotNull String cachedLang = Utils.readToString(new File(container, cachedHash));
    
    if (!cachedLang.isEmpty()) {
      try {
        JsonObject cachedJson = new JsonParser().parse(cachedLang).getAsJsonObject();
        for (Entry<String, JsonElement> e : cachedJson.entrySet())
          locales.put(parseKey(e.getKey()), e.getValue().getAsString());

      } catch (JsonSyntaxException e) {
        /*
         * Legacy (.lang) files
         * As 'key'='value' primitive format in <= 1.12.2
         */
        new BufferedReader(new StringReader(cachedLang)).lines().forEach(line -> {
          int separatorAt = line.indexOf('=');
          locales.put(parseKey(line.substring(0, separatorAt)), line.substring(separatorAt));
        });
      }
    }
  }
  
  /**
   * Converts raw key in lang file to its runtime type name
   * @param rawKey the name key in the lang file
   * @return the runtime name key of it
   */
  private String parseKey(String rawKey) {
    rawKey = rawKey.toLowerCase(Locale.ROOT);
    
    /*
     * Modern json format for > 1.12.2
     */
    if (rawKey.startsWith("enchantment.minecraft.")) {
      rawKey = rawKey.substring(22);
    }
    
    else if (rawKey.startsWith("effect.minecraft.")) {
      rawKey = rawKey.substring(17);
    }
    
    else if (rawKey.startsWith("entity.minecraft.")) {
      rawKey = rawKey.substring(17);
    }
    
    else if (rawKey.startsWith("block.minecraft.")) {
      rawKey = rawKey.substring(16);
    }
    
    else if (rawKey.startsWith("item.minecraft.")) {
      rawKey = rawKey.substring(15);
    }
    
    /*
     * Legacy lang format for <= 1.12.2
     */
    else if (rawKey.startsWith("enchantment.")) {
      rawKey = StringUtils.replace(rawKey, "protect.", "protection_");
      rawKey = StringUtils.replace(rawKey, "damage.", "damage_");
      rawKey = StringUtils.replace(rawKey, "arrow.", "arrow_");
      
      rawKey = StringUtils.replace(rawKey, "Worker", "worker");
      
      rawKey = StringUtils.replace(rawKey, "sweeping", "sweeping_edge");
      rawKey = StringUtils.replace(rawKey, "digging", "dig_speed");
      rawKey = StringUtils.replace(rawKey, "fire", "fire_aspect");
      rawKey = StringUtils.replace(rawKey, "lootBonus", "loot_bonus_mobs");
      
      rawKey = StringUtils.replace(rawKey, "lootBonusDigger", "loot_bonus_blocks");
      rawKey = StringUtils.replace(rawKey, "untouching", "silk_touch");
      rawKey = StringUtils.replace(rawKey, "explosion", "explosions");
      rawKey = StringUtils.replace(rawKey, "all", "environmental");
      rawKey = StringUtils.replace(rawKey, "fishingSpeed", "lure");
      rawKey = StringUtils.replace(rawKey, "lootBonusFishing", "luck");
    }
    
    return rawKey.toUpperCase(Locale.ROOT);
  }

  /**
   * Get item and block translations, if not found, it will both call getBlock()
   *
   * @param material The material
   * @return The translations for material
   */
  @NotNull
  public String get(@NotNull Material material) {
    String key = material.name();
    return locales.getOrDefault("T;".concat(key), key);
  }

  /**
   * Get entity translations.
   *
   * @param entity The entity name
   * @return The translations for entity
   */
  @NotNull
  public String get(@NotNull Entity entity) {
    String key = entity.getType().name();
    return locales.getOrDefault("E;".concat(key), key);
  }

  /**
   * Get potion/effect translations.
   *
   * @param effect The potion effect name
   * @return The translations for potion effect
   */
  @NotNull
  public String get(@NotNull PotionEffectType effect) {
    String key = effect.getName();
    return locales.getOrDefault("P;".concat(key), key);
  }

  /**
   * Get enchantment translations.
   *
   * @param enchantmentName The enchantment name
   * @return The translations for enchantment
   */
  @NotNull
  public String get(@NotNull Enchantment enchantment) {
    String key = enchantment.getName();
    return locales.getOrDefault("N;".concat(key), key);
  }

  /**
   * Get custom translations.
   *
   * @param key The target node path
   * @return The translations for you custom node path
   */
  @NotNull
  public Optional<String> get(@NotNull String key) {
    return Optional.ofNullable(locales.getOrDefault(key, key));
  }
}
