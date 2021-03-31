package kr.inek.encrypt.generator;

import kr.inek.encrypt.InekEncrypt;
import kr.inek.encrypt.util.Base64Encoder;
import kr.inek.encrypt.util.HexEncoder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class KeyGenerator {
  public static void main(String[] args) {
    try {
      String originKey = "INEK_ENCRYPT$2#!";
      String hexStr = HexEncoder.encode(originKey);
      String baseStr = Base64Encoder.encode(hexStr.getBytes("UTF-8"));
      String encTarget = "HelloWorld!";
      String enc = InekEncrypt.encrypt(encTarget);
      String encoderEnc = URLEncoder.encode(enc, "UTF-8");
      String decoderEnc = URLDecoder.decode(encoderEnc, "UTF-8");
      String dec = InekEncrypt.decrypt(decoderEnc);

      System.out.println("hexStr : " + hexStr);
      System.out.println("baseStr : " + baseStr);
      System.out.println("enc : " + enc);
      System.out.println("encoderEnc : " + encoderEnc);
      System.out.println("decoderEnc : " + decoderEnc);
      System.out.println("dec : " + dec);

    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
