/*
 * This file is a part of project QuickShop, the name is ShopModeratorChangedEvent.java Copyright
 * (C) Ghost_chu <https://github.com/Ghost-chu> Copyright (C) Bukkit Commons Studio and contributors
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package cc.bukkit.shop.event;

import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.ContainerShop;
import cc.bukkit.shop.moderator.ShopModerator;
import lombok.Getter;

/** Calling when moderator was changed, Can't cancel */
public class ShopModeratorChangedEvent extends ShopEvent {

  @Getter
  @NotNull
  private final ShopModerator moderator;

  @Getter
  @NotNull
  private final ContainerShop shop;

  /**
   * Will call when shop price was changed.
   *
   * @param shop Target shop
   * @param shopModerator The shop moderator
   */
  public ShopModeratorChangedEvent(@NotNull ContainerShop shop, @NotNull ShopModerator shopModerator) {
    this.shop = shop;
    this.moderator = shopModerator;
  }
}
