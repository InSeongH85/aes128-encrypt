package kr.inek.encrypt;

import kr.inek.encrypt.util.AES128;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class InekEncrypt {

  public static String encrypt(String target) throws Exception {
    return AES128.encrypt(target);
  }

  public static String decrypt(String target) throws Exception {
    return AES128.decrypt(target);
  }

  public static void main(String[] args) {
    try {
      String encStr = InekEncrypt.encrypt("HELLO WORLD !!");
      System.out.println(encStr);
      String encodeEncStr = URLEncoder.encode(encStr, "UTF-8");
      System.out.println(encodeEncStr);
      String decodeDecStr = URLDecoder.decode(encodeEncStr, "UTF-8");
      String decStr = InekEncrypt.decrypt(decodeDecStr);
      System.out.println(decStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
