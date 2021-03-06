/*
 * This file is a part of project QuickShop, the name is ShopUnloadEvent.java Copyright (C)
 * Ghost_chu <https://github.com/Ghost-chu> Copyright (C) Bukkit Commons Studio and contributors
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
import lombok.Getter;

/** Getting the unloading shop, Can't cancel. */
public class ShopUnloadEvent extends ShopEvent {

  @NotNull
  @Getter
  private ContainerShop shop;

  /**
   * Getting the unloading shop, Can't cancel.
   *
   * @param shop The shop to unload
   */
  public ShopUnloadEvent(@NotNull ContainerShop shop) {
    this.shop = shop;
  }
}
