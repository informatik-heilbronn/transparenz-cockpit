package de.hhn.seb.labsw.transparentcockpit.backend.models.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * System Settings.
 */
public class SystemSetting {
  private final String key;

  private final String value;


  /**
   * Constructor.
   *
   * @param key   Key of the Setting
   * @param value Value of the Setting
   */
  public SystemSetting(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
