package cc.bukkit.shop.action.data;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import cc.bukkit.shop.action.ShopAction;
import cc.bukkit.shop.action.ShopActionData;
import cc.bukkit.shop.util.ShopLocation;
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
  private final ItemStack item;

  @Override
  public ShopAction action() {
    return ShopAction.CREATE;
  }
}