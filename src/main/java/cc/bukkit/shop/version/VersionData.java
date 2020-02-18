package cc.bukkit.shop.version;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class VersionData {
  private final boolean beta;
  private final String version;
  
  public static VersionData create(String version) {
    String lowercase = version.toLowerCase();
    boolean beta = lowercase.contains("beta") ||
                   lowercase.contains("snapshot") ||
                   lowercase.contains("nightly") ||
                   lowercase.contains("preview") ||
                   lowercase.contains("unstable") ||
                   lowercase.contains("alpha");
    return new VersionData(beta, version);
  }
}
