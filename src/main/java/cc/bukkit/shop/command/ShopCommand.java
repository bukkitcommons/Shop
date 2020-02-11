package cc.bukkit.shop.command;

public interface ShopCommand extends CommandData, CommandProcesser {
  @Override
  default CommandProcesser executor() {
    return this;
  }
  
  @Override
  default boolean hidden() {
    return false;
  }
}
