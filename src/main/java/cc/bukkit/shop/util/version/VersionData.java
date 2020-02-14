package cc.bukkit.shop.util.version;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class VersionData {
  private final boolean beta;
  private final String version;
  
  public static VersionData create(String version) {
    return new VersionData(version.toLowerCase().contains("beta"), version);
  }
}
