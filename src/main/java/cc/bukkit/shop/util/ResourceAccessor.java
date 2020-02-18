package cc.bukkit.shop.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.Shop;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ResourceAccessor {
  public static Optional<InputStream> get(@NotNull String injar) {
    return Optional.ofNullable(Shop.instance().getResource(injar));
  }
  
  public static void save(@NotNull String injar, @NotNull String to) throws IOException {
    File target = new File(Shop.instance().getDataFolder(), to);
    target.createNewFile();
    save(injar, target);
  }
  
  public static void save(@NotNull String path) throws IOException {
    File target = new File(Shop.instance().getDataFolder(), path);
    target.createNewFile();
    save(path, target);
  }
  
  public static void save(@NotNull String injar, @NotNull File to) throws IOException {
    Optional<InputStream> is = get(injar);
    if (is.isPresent())
      save(is.get(), to);
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
