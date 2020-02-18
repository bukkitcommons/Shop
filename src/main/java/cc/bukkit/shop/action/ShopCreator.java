package cc.bukkit.shop.action;

import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import cc.bukkit.shop.misc.ShopLocation;
import cc.bukkit.shop.stack.Stacked;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@RequiredArgsConstructor(staticName = "create")
public class ShopCreator implements ShopActionData {
  @NotNull
  private final ShopLocation location;
  @Nullable
  private final Block sign;
  @NotNull
  private final Stacked stack;

  @Override
  public ShopAction action() {
    return ShopAction.CREATE;
  }
}
