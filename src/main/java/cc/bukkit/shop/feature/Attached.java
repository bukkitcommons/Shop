package cc.bukkit.shop.feature;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.jetbrains.annotations.NotNull;

public interface Attached extends Located {
  /**
   * Checks whether that sign is attached in the shop block.
   * @param sign a block that may be sign
   * @return whether attached
   */
  boolean isAttached(Block sign);
  
  /**
   * Gets signs that attached on this shop.
   * @return attached signs
   */
  @NotNull
  List<Sign> getAttached();
  
  void setSignText();
}
