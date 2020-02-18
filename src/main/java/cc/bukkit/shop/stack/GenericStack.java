package cc.bukkit.shop.stack;

import org.jetbrains.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(fluent = true)
public abstract class GenericStack implements GenericStacked {
  @NotNull
  protected Object stack;
}
