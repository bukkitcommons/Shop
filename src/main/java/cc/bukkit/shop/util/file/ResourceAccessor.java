package cc.bukkit.shop.util.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.Shop;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ResourceAccessor {
  public static InputStream get(@NotNull String injar) {
    return Shop.instance().getResource(injar);
  }
  
  public static void save(@NotNull String injar, @NotNull String to) throws IOException {
    File target = new File(Shop.instance().getDataFolder(), to);
    target.createNewFile();
    save(injar, to);
  }
  
  public static void save(@NotNull String path) throws IOException {
    File target = new File(Shop.instance().getDataFolder(), path);
    target.createNewFile();
    save(path, path);
  }
  
  public static void save(@NotNull String injar, @NotNull File to) throws IOException {
    InputStream is = get(injar);
    save(is, to);
    is.close();
  }
  
  public static void save(@NotNull InputStream inputStream, @NotNull File file) throws IOException {
    OutputStream out = new FileOutputStream(file);
    byte[] buf = new byte[1024];
    int len;

    while ((len = inputStream.read(buf)) > 0)
      out.write(buf, 0, len);

    out.close();
    inputStream.close();
  }
}
