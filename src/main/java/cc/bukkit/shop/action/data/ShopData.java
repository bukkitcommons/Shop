package cc.bukkit.shop.action.data;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.google.gson.JsonSyntaxException;
import cc.bukkit.shop.ShopType;
import cc.bukkit.shop.moderator.ShopModerator;
import cc.bukkit.shop.util.ShopLocation;
import cc.bukkit.shop.util.Utils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@RequiredArgsConstructor
@Accessors(fluent = true)
public class ShopData implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Nullable
  private final String item;
  @Nullable
  private final String moderators;
  @Nullable
  private final String world;
  @Nullable
  private final ShopType type;
  private final double price;
  private final boolean unlimited;
  private final int x;
  private final int y;
  private final int z;
  
  @Nullable
  private transient ShopModerator manager;
  
  @NotNull
  public ShopModerator moderators() {
    return manager == null ?
        deserializeModerator(moderators) : manager;
  }
  
  @NotNull
  private static ShopModerator deserializeModerator(@NotNull String moderatorJson) {
    ShopModerator shopModerator;
    if (Utils.isUUID(moderatorJson)) {
      shopModerator = new ShopModerator(UUID.fromString(moderatorJson));
    } else {
      try {
        shopModerator = ShopModerator.deserialize(moderatorJson);
      } catch (JsonSyntaxException ex) {
        moderatorJson = Bukkit.getOfflinePlayer(moderatorJson).getUniqueId().toString();
        shopModerator = new ShopModerator(UUID.fromString(moderatorJson));
      }
    }
    return shopModerator;
  }
  
  @Nullable
  public String serializedModerators() {
    return moderators;
  }
  
  @Nullable
  private transient ShopLocation location;
  
  @Nullable
  public ShopLocation location() {
    return location == null ? ShopLocation.from(world, x, y, z) : location;
  }
  
  public ShopData(@NotNull ResultSet rs) throws SQLException {
    this.x = rs.getInt("x");
    this.y = rs.getInt("y");
    this.z = rs.getInt("z");
    this.price = rs.getDouble("price");
    this.unlimited = rs.getBoolean("unlimited");
    this.type = ShopType.fromID(rs.getInt("type"));
    this.world = rs.getString("world");
    this.item = rs.getString("itemConfig");
    this.moderators = rs.getString("owner");
  }
}
