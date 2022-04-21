package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Encoder (REQUIRED because '.', ... are not Supported by default).
 */
public class Base64Encoder {

    public static String encode(String string) {
        if (string == null) {
            return null;
        } else {
            return Base64.getEncoder().encodeToString(string.getBytes(StandardCharsets.UTF_8));
        }
    }

    public static String decode(String string) {
        if (string == null) {
            return null;
        } else {
            return new String(Base64.getDecoder().decode(string.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        }
    }
}
