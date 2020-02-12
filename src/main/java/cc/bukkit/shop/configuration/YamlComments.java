package cc.bukkit.shop.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConstructor;
import org.bukkit.configuration.file.YamlRepresenter;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import lombok.NonNull;

public class YamlComments {
  @NotNull
  private final static DumperOptions YAML_OPTIONS = new DumperOptions();
  @NotNull
  private final static Representer YAML_REPRESENTER = new YamlRepresenter();

  public static void save(@NotNull File file, @NonNull YamlConfiguration config) throws IOException {
    Files.createParentDirs(file);
    String data = saveToString(config, file);
    Writer writer = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8);

    try {
      writer.write(data);
    } finally {
      writer.close();
    }
  }

  @NotNull
  private static String saveToString(YamlConfiguration config, File file) {
    /*
     * Lookups
     */
    // Whole line comments and empty lines
    HashMap<String, String> inserts = Maps.newHashMap();
    // Part comments following a section
    HashMap<String, String> insertsAfter = Maps.newHashMap();

    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String insertBefore = "";
      String line;
      
      LINES:
      while ((line = reader.readLine()) != null) {
        char[] chars = line.toCharArray();
        if (chars.length == 0) {
          insertBefore = insertBefore.concat("\n");
          continue LINES;
        }
        
        boolean hasSection = false;
        for (char character : chars) {
          if (character == ' ')
            continue;
          
          if (character == '#')
            if (hasSection) {
              // Part comments
              String section = StringUtils.substringBefore(line, " # ");
              insertsAfter.put(section, " # ".concat(StringUtils.substringAfter(line, " # ")));
            } else {
              break; // Pure comment line continues
            }
          
          // A config section line
          hasSection = true;
        }
        
        if (hasSection) {
          if (!insertBefore.isEmpty()) 
            // Flush the comment prepared before
            inserts.put(line, insertBefore);
          
          insertBefore = "";
          continue LINES;
        }
        
        // Empty line with blank spaces & comment lines
        insertBefore = insertBefore.concat(line).concat("\n");
      }
      
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    /*
     * Serializer
     */
    YAML_OPTIONS.setIndent(config.options().indent());
    YAML_OPTIONS.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    YAML_OPTIONS.setAllowUnicode(true);
    YAML_REPRESENTER.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    
    Yaml yaml = new Yaml(new YamlConstructor(), YAML_REPRESENTER, YAML_OPTIONS);
    String rawYaml = yaml.dump(config.getValues(false));
    
    /*
     * Inserts
     */
    for (Entry<String, String> insert : inserts.entrySet()) {
      int pos = rawYaml.indexOf(insert.getKey()); // Next line start pos
      if (pos != -1) {
        String beforeInsert = rawYaml.substring(0, pos);
        String afterInsert  = rawYaml.substring(pos);
        
        rawYaml = beforeInsert.concat(insert.getValue()).concat(afterInsert);
      }
    }
    
    for (Entry<String, String> insert : insertsAfter.entrySet()) {
      int pos = rawYaml.indexOf(insert.getKey()) + insert.getKey().length(); // Comment start pos
      if (pos != -1) {
        String beforeInsert = rawYaml.substring(0, pos);
        String afterInsert  = rawYaml.substring(pos);
        
        rawYaml = beforeInsert.concat(insert.getValue()).concat(afterInsert);
      }
    }

    return rawYaml;
  }
}
