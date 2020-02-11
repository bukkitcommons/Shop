package cc.bukkit.shop.util.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResourceAccessor {
  private final Plugin plugin;
  
  /**
   * Get target language's type file.
   *
   * @param language The target language
   * @param type The file type for you want get. e.g. messages
   * @return The target file's InputStream.
   */
  public InputStream getFile(@Nullable String language, @Nullable String type) {
    if (language == null)
      language = "en";
    
    if (type == null || type.isEmpty())
      throw new IllegalArgumentException("Type cannot be null or empty");
    
    InputStream inputStream = plugin.getResource(type + "/" + language + ".json");
    if (inputStream == null)
      inputStream = plugin.getResource(type + "/" + "en" + ".json");
    
    return inputStream;
    // File name should call type-language.yml ---> config-zh.yml
  }

  // Write file under plugin folder

  /**
   * Save the target language's type file to the datafolder
   *
   * @param language Target language
   * @param type Target type
   * @param fileName The filename you want write to the plugin datafolder.
   */
  public void saveFile(@NotNull String language, @NotNull String type, @NotNull String fileName) {
    File targetFile = new File(plugin.getDataFolder(), fileName);
    if (!targetFile.exists()) {
      try {
        targetFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      InputStream is = getFile(language, type);
      new Rewriter(targetFile).accept(is);
      is.close();
    } catch (Exception err) {
      err.printStackTrace();
    }
  }
}
