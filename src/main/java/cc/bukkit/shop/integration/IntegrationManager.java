package cc.bukkit.shop.integration;

import java.util.Set;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import com.google.common.collect.Sets;
import lombok.Getter;

@Getter
public class IntegrationManager {
  private Set<IntegratedPlugin> integrations = Sets.newHashSet();

  public void register(@NotNull IntegratedPlugin clazz) {
    if (!isIntegrationClass(clazz)) {
      throw new InvaildIntegratedException();
    }
    integrations.add(clazz);
  }

  public void unregister(@NotNull IntegratedPlugin clazz) {
    if (!isIntegrationClass(clazz)) {
      throw new InvaildIntegratedException();
    }
    integrations.remove(clazz);
  }

  public void callIntegrationsLoad(@NotNull IntegrateStage stage) {
    integrations.forEach(integratedPlugin -> {
      if (integratedPlugin.getClass().getDeclaredAnnotation(IntegrationStage.class)
          .loadStage() == stage) {
        integratedPlugin.load();
      }
    });
  }

  public void callIntegrationsUnload(@NotNull IntegrateStage stage) {
    integrations.forEach(integratedPlugin -> {
      if (integratedPlugin.getClass().getDeclaredAnnotation(IntegrationStage.class)
          .unloadStage() == stage) {
        integratedPlugin.unload();
      }
    });
  }

  public boolean callIntegrationsCanCreate(@NotNull Player player, @NotNull Location location) {
    for (IntegratedPlugin plugin : integrations) {
      if (!plugin.canCreateShopHere(player, location))
        return false;
    }
    return true;
  }

  public boolean callIntegrationsCanTrade(@NotNull Player player, @NotNull Location location) {
    for (IntegratedPlugin plugin : integrations) {
      if (!plugin.canTradeShopHere(player, location))
        return false;
    }
    return true;
  }

  private boolean isIntegrationClass(@NotNull IntegratedPlugin clazz) {
    return clazz.getClass().getDeclaredAnnotation(IntegrationStage.class) != null;
  }
}


class InvaildIntegratedException extends IllegalArgumentException {
  private static final long serialVersionUID = 1L;
}
