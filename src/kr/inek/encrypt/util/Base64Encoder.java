package kr.inek.encrypt.util;

public final class Base64Encoder {
  private static final String BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
  
  public static String encode(byte[] value) {
    if ((value == null) || (value.length == 0)) {
      return "";
    }
    int requiredSize = value.length / 3 * 4;
    if (value.length % 3 > 0) {
      requiredSize += 4;
    }
    char[] output = new char[requiredSize];
    
    int outputIndex = 0;int bufferIndex = 0;
    int[] buffer = new int[3];
    for (int i = 0; i < value.length; i++) {
      buffer[(bufferIndex++)] = (0xFF & value[i]);
      if ((bufferIndex == 3) || (i == value.length - 1)) {
        int base64CharIndex = buffer[0] >>> 2;
        output[(outputIndex++)] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(base64CharIndex);
        
        base64CharIndex = (buffer[0] & 0x3) << 4 | buffer[1] >>> 4;
        output[(outputIndex++)] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(base64CharIndex);
        
        base64CharIndex = (buffer[1] & 0xF) << 2 | buffer[2] >>> 6;
        output[(outputIndex++)] = (bufferIndex > 1 ? "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(base64CharIndex) : '=');
        
        base64CharIndex = buffer[2] & 0x3F;
        output[(outputIndex++)] = (bufferIndex > 2 ? "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(base64CharIndex) : '=');
        
        bufferIndex = 0;
        for (int j = 0; j < buffer.length; j++) {
          buffer[j] = 0;
        }
      }
    }
    return new String(output);
  }
  
  public static byte[] decode(String value) throws Exception {
    if ((value == null) || (value.length() == 0)) {
      return new byte[0];
    }
    if (value.length() % 4 != 0) {
      throw new Exception("The length of a Base64 string must be a multiple of 4.");
    }
    for (int i = 0; i < value.length(); i++)
    {
      char chr = value.charAt(i);
      if ((i == value.length() - 1) && (chr == '=')) {
        break;
      }
      if ((i == value.length() - 2) && (chr == '=') && 
        (value.charAt(i + 1) == '=')) {
        break;
      }
      if ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(chr) == -1) {
        throw new Exception("An invalid character is found string.");
      }
    }
    int requiredSize = value.length() / 4 * 3;
    if (value.charAt(value.length() - 1) == '=') {
      requiredSize--;
    }
    if (value.charAt(value.length() - 2) == '=') {
      requiredSize--;
    }
    byte[] output = new byte[requiredSize];
    
    int outputIndex = 0;int bufferIndex = 0;
    char[] buffer = new char[4];
    for (int i = 0; i < value.length(); i++) {
      buffer[(bufferIndex++)] = value.charAt(i);
      if (bufferIndex == 4) {
        int base64CharIndex = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(buffer[0]);
        output[outputIndex] = ((byte)(base64CharIndex << 2));
        
        base64CharIndex = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(buffer[1]);
        int tmp249_246 = outputIndex++;
        byte[] tmp249_244 = output;tmp249_244[tmp249_246] = ((byte)(tmp249_244[tmp249_246] | base64CharIndex >>> 4));
        if (buffer[2] == '=') {
          break;
        }
        output[outputIndex] = ((byte)((base64CharIndex & 0xF) << 4));
        
        base64CharIndex = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(buffer[2]);
        int tmp297_294 = outputIndex++;
        byte[] tmp297_292 = output;tmp297_292[tmp297_294] = ((byte)(tmp297_292[tmp297_294] | base64CharIndex >>> 2));
        if (buffer[3] == '=') {
          break;
        }
        output[outputIndex] = ((byte)((base64CharIndex & 0x3) << 6));
        
        base64CharIndex = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(buffer[3]);
        int tmp345_342 = outputIndex++;
        byte[] tmp345_340 = output;tmp345_340[tmp345_342] = ((byte)(tmp345_340[tmp345_342] | base64CharIndex));
        
        bufferIndex = 0;
      }
    }
    return output;
  }
}
