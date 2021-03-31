package kr.inek.encrypt.util;

import java.nio.charset.Charset;

public class HexEncoder {

  public static String encode(String target) {
    byte[] inputBytes = target.getBytes(Charset.forName("UTF-8"));
    String hexString = "";
    for (byte b : inputBytes) {
      hexString += Integer.toString((b & 0xF0) >> 4, 16);
      hexString += Integer.toString(b & 0x0F, 16);
    }
    return hexString;
  }

  public static String decode(String target) {
    //hex string -> byte array
    byte[] bytes = new java.math.BigInteger(target, 16).toByteArray();

    //byte array -> hex string
    String hexStrin2 = new java.math.BigInteger(bytes).toString(16);

    //hex string -> string
    byte [] hexBytes = new byte [hexStrin2.length() / 2];
    int j = 0;
    for (int i = 0; i < hexStrin2.length(); i += 2) {
      hexBytes[j++] = Byte.parseByte(hexStrin2.substring(i, i + 2), 16);
    }
    String hr = new String(hexBytes);

    return hr;
  }
}
