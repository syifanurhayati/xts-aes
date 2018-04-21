/**
 * 
 * @author user
 *
 */

public class ConverterBytesHex {
	
	public static String bytesToHex(byte[] byteArray) {
		String hexString = "";
		StringBuilder sb = new StringBuilder();
		for (byte b: byteArray) {
			sb.append(String.format("%02X", b));
		}
		hexString = sb.toString();
		return hexString;
	}
	
	public static byte[] hexToBytes(String hexString) {
		int arrLength = hexString.length() / 2;
		byte[] byteArray = new byte[arrLength];
		
		for (int i = 0; i < arrLength; i++) {
			int idx = i * 2;
			int v = Integer.parseInt(hexString.substring(idx, idx + 2), 16);
			byteArray[i] = (byte) v;
		}
		return byteArray;
		
	}
}