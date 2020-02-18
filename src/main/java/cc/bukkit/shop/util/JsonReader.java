package cc.bukkit.shop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.error.YAMLException;
import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

public class JsonReader extends FileConfiguration {
  protected static final String BLANK_CONFIG = "{}\n";
  
  private JsonReader() {
    ;
  }
  
  private static class JSONConfigurationOptions extends FileConfigurationOptions {
    protected JSONConfigurationOptions(@NotNull MemoryConfiguration configuration) {
      super(configuration);
    }
  }
  
  /**
   * Parses through the input map to deal with serialized objects a la
   * {@link ConfigurationSerializable}.
   *
   * <p>
   * Called recursively first on Maps and Lists before passing the parsed input over to
   * {@link ConfigurationSerialization#deserializeObject(java.util.Map)}. Basically this means it
   * will deserialize the most nested objects FIRST and the top level object LAST.
   *
   * @param input the input
   * @return the object that deserialize
   */
  private static Object deserialize(@NotNull final Map<?, ?> input) {
    final Map<String, Object> output = new LinkedHashMap<String, Object>(input.size());
    for (final Map.Entry<?, ?> e : input.entrySet()) {
      if (e.getValue() instanceof Map) {
        output.put(e.getKey().toString(), deserialize((Map<?, ?>) e.getValue()));
      } else if (e.getValue() instanceof List) {
        output.put(e.getKey().toString(), deserialize((List<?>) e.getValue()));
      } else {
        output.put(e.getKey().toString(), e.getValue());
      }
    }
    if (output.containsKey(ConfigurationSerialization.SERIALIZED_TYPE_KEY)) {
      try {
        return ConfigurationSerialization.deserializeObject(output);
      } catch (IllegalArgumentException ex) {
        throw new YAMLException("Could not deserialize object", ex);
      }
    }
    return output;
  }

  /**
   * Parses through the input list to deal with serialized objects a la
   * {@link ConfigurationSerializable}.
   *
   * <p>
   * Functions similarly to {@link #deserialize(java.util.Map)} but only for detecting lists within
   * lists and maps within lists.
   */
  private static Object deserialize(@NotNull final List<?> input) {
    final List<Object> output = new ArrayList<Object>(input.size());
    for (final Object o : input) {
      if (o instanceof Map) {
        output.add(deserialize((Map<?, ?>) o));
      } else if (o instanceof List) {
        output.add(deserialize((List<?>) o));
      } else {
        output.add(o);
      }
    }
    return output;
  }
  
  @NotNull
  public static FileConfiguration read(@NotNull File file) {
    JsonReader config = new JsonReader();

    try {
      config.load(new InputStreamReader(new FileInputStream(file), Charsets.UTF_8));
    } catch (FileNotFoundException ignored) {
      return config;
    } catch (IOException | InvalidConfigurationException ex) {
      Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file, ex);
    }

    return config;
  }
  
  @Override
  public void loadFromString(@NotNull String contents) throws InvalidConfigurationException {
    if (contents.isEmpty()) {
      return;
    }

    final Map<?, ?> input;

    try {
      final Gson gson =
          new GsonBuilder().registerTypeAdapter(new TypeToken<Map<String, Object>>() {}.getType(),
              new MapDeserializerDoubleAsIntFix()).create();
      input = gson.fromJson(contents, new TypeToken<Map<String, Object>>() {}.getType());
    } catch (JsonSyntaxException e) {
      throw new InvalidConfigurationException("Invalid JSON detected.", e);
    } catch (ClassCastException e) {
      throw new InvalidConfigurationException("Top level is not a Map.", e);
    }

    if (input != null) {
      convertMapsToSections(input, this);
    } else {
      throw new InvalidConfigurationException(
          "An unknown error occurred while attempting to parse the json.");
    }
  }

  @NotNull
  @Override
  protected String buildHeader() {
    return "";
  }

  protected void convertMapsToSections(@NotNull Map<?, ?> input,
      @NotNull ConfigurationSection section) {
    final Object result = deserialize(input);

    if (result instanceof Map) {
      input = (Map<?, ?>) result;

      for (Map.Entry<?, ?> entry : input.entrySet()) {
        final String key = entry.getKey().toString();
        final Object value = entry.getValue();

        if (value instanceof Map) {
          convertMapsToSections((Map<?, ?>) value, section.createSection(key));
        } else {
          section.set(key, value);
        }
      }
    } else {
      section.set("", result);
    }
  }

  @NotNull
  @Override
  public FileConfigurationOptions options() {
    if (options == null) {
      options = new JSONConfigurationOptions(this);
    }

    return (JSONConfigurationOptions) options;
  }
  
  private static class MapDeserializerDoubleAsIntFix
      implements JsonDeserializer<Map<String, Object>> {

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> deserialize(JsonElement json, Type typeOfT,
        JsonDeserializationContext context) throws JsonParseException {
      return (Map<String, Object>) read(json);
    }

    public Object read(JsonElement in) {
      if (in.isJsonArray()) {
        List<Object> list = new ArrayList<Object>();
        JsonArray arr = in.getAsJsonArray();
        for (JsonElement anArr : arr) {
          list.add(read(anArr));
        }
        return list;
      } else if (in.isJsonObject()) {
        Map<String, Object> map = new LinkedTreeMap<String, Object>();
        JsonObject obj = in.getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entitySet = obj.entrySet();
        for (Map.Entry<String, JsonElement> entry : entitySet) {
          map.put(entry.getKey(), read(entry.getValue()));
        }
        return map;
      } else if (in.isJsonPrimitive()) {
        JsonPrimitive prim = in.getAsJsonPrimitive();
        if (prim.isBoolean()) {
          return prim.getAsBoolean();
        } else if (prim.isString()) {
          return prim.getAsString();
        } else if (prim.isNumber()) {
          Number num = prim.getAsNumber();
          // here you can handle double int/long values
          // and return any type you want
          // this solution will transform 3.0 float to long values
          if (Math.ceil(num.doubleValue()) == num.longValue())
            return num.longValue();
          else {
            return num.doubleValue();
          }
        }
      }
      return null;
    }
  }

  @Override
  public String saveToString() {
    return "";
  }
}
