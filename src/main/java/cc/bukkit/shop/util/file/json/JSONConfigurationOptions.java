/*
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of
 * the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package cc.bukkit.shop.util.file.json;

import org.bukkit.configuration.file.FileConfigurationOptions;
import org.jetbrains.annotations.NotNull;

public class JSONConfigurationOptions extends FileConfigurationOptions {
  protected JSONConfigurationOptions(@NotNull final JSONConfiguration configuration) {
    super(configuration);
  }

  @Override
  public JSONConfiguration configuration() {
    return (JSONConfiguration) super.configuration();
  }

  @Override
  public JSONConfigurationOptions copyDefaults(final boolean value) {
    super.copyDefaults(value);
    return this;
  }

  @Override
  public JSONConfigurationOptions pathSeparator(final char value) {
    super.pathSeparator(value);
    return this;
  }

  @Override
  public JSONConfigurationOptions header(final String value) {
    super.header(value);
    return this;
  }

  @Override
  public JSONConfigurationOptions copyHeader(final boolean value) {
    super.copyHeader(value);
    return this;
  }
}
