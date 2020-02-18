package cc.bukkit.shop.util.mojang;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import cc.bukkit.shop.util.Utils;

public class AssetJson {
  final String pathTemplate = "minecraft/lang/{0}.json";
  final String pathTemplateLegacy = "minecraft/lang/{0}.lang";
  @NotNull
  String gameAssets;

  public AssetJson(@NotNull String json) {
    this.gameAssets = json;
  }

  @Nullable
  public String getLanguageHash(@NotNull String languageCode) {
    languageCode = languageCode.replace("-", "_").toLowerCase().trim();
    JsonObject json = new JsonParser().parse(this.gameAssets).getAsJsonObject();
    if (json == null || json.isJsonNull()) {
      return null;
    }
    JsonElement obje = json.get("objects");
    if (obje == null) {
      return null;
    }
    JsonObject objs = obje.getAsJsonObject();
    if (objs == null || objs.isJsonNull()) {
      return null;
    }
    JsonObject langObj = objs.getAsJsonObject(Utils.fillArgs(pathTemplate, languageCode));
    if (langObj == null || langObj.isJsonNull()) {
      langObj = objs.getAsJsonObject(Utils.fillArgs(pathTemplateLegacy, languageCode));
      
      if (langObj == null || langObj.isJsonNull()) {
        return null;
      }
    }
    JsonPrimitive hashObj = langObj.getAsJsonPrimitive("hash");
    if (hashObj == null || hashObj.isJsonNull()) {
      return null;
    }
    return hashObj.getAsString();
  }
}
