import java.io.BufferedReader;
import java.io.FileReader;
import java.io.RandomAccessFile;

/**
 * 
 * @author user
 *
 */
public class XTS {
	private final int BLOCK_SIZE = 16;
	private final int KEY_LENGTH_HEX = 64;
	private byte[] key1;
	private byte[] key2;
	private int m;
	private String file;
	private String output;
	private byte[][] t_all_blocks;
	private int usedLastBlockSpace;
	private boolean needStealing;
	private String tweak = "0123456789ABCDF0123456789ABCD";
	
	
	/**
	 * 
	 * @param file
	 * @param key
	 * @param output
	 * @throws Exception
	 */
	public XTS(String file, String key, String output) throws Exception {
		// inisialization
		this.file = file;
		this.output = output;
		
		// read the key
		BufferedReader key_br = new BufferedReader(new FileReader(key));
		String key_str = key_br.readLine();
		this.key1 = ConverterBytesHex.hexToBytes(key_str.substring(0,KEY_LENGTH_HEX / 2));
		this.key2 = ConverterBytesHex.hexToBytes(key_str.substring
				(KEY_LENGTH_HEX / 2, key_str.length()));
		key_br.close();
		
		// read file input
		RandomAccessFile file_br = new RandomAccessFile(file, "r");
		long file_size = file_br.length();
		file_br.close();
		
		// check if last block is full or not
		if (file_size % BLOCK_SIZE == 0) {
			this.m = (int) (file_size / BLOCK_SIZE);
			this.needStealing = false;
		} else {
			this.m = (int) (file_size / BLOCK_SIZE) + 1;
			this.needStealing = true;
			this.usedLastBlockSpace = (int) (file_size % BLOCK_SIZE);
		}
		
		AES aes = new AES();
		
		// encrypt tweak
		byte[] tweakEncrypted = aes.encrypt(ConverterBytesHex.hexToBytes(tweak), key2   );
		
		//	calculate T for all blocks
		calculate_t_matrix(tweakEncrypted);
	}
	
	public void encrypt() throws Exception {
		RandomAccessFile file_br = new RandomAccessFile(file, "r");
		
		byte[][] plaintext = new byte[m + 1][BLOCK_SIZE];
		plaintext[m] = new byte[usedLastBlockSpace];
		
		byte[][] ciphertext = new byte[m + 1][BLOCK_SIZE];
		ciphertext[m] = new byte[usedLastBlockSpace];
		
		for (int i = 0; i < plaintext.length; i++) {
			file_br.read(plaintext[i]);
		}
		
		for (int i = 0; i < m - 2; i++) {
			ciphertext[i] = encryption_per_block(key1, plaintext[i], i);
		}
		
		if(!needStealing) {
			ciphertext[m - 1] = encryption_per_block(key1, plaintext[m - 1], m - 1);
			ciphertext[m] = new byte[0];
		} else {
			byte[] cc = encryption_per_block(key1, plaintext[m - 1], m - 1);
			System.arraycopy(cc, 0, ciphertext[m], 0, usedLastBlockSpace);
			byte[] cp = new byte[BLOCK_SIZE - usedLastBlockSpace];
			
			for (int i = (usedLastBlockSpace); i < BLOCK_SIZE; i++) {
				cp[i - usedLastBlockSpace] = cc[i];
			}
			
			byte[] pp = new byte[plaintext[m].length + cp.length];
			System.arraycopy(plaintext[m], 0, pp, 0, plaintext[m].length);
			System.arraycopy(cp, 0, pp, plaintext[m].length, cp.length);
			
			ciphertext[m - 1] = encryption_per_block(key1, pp, m - 1);
		}
		
		file_br.close();
		
		RandomAccessFile out_br = new RandomAccessFile(output, "rw");
		for (int i = 0; i < ciphertext.length; i++) {
			for (int j = 0; j < ciphertext[i].length; j++) {
				out_br.write(ciphertext[i][j]);
			}
		}
		out_br.close();
	}

	
	public void decrypt() throws Exception {
		RandomAccessFile file_br = new RandomAccessFile(file, "r");
		
		byte[][] ciphertext = new byte[m + 1][BLOCK_SIZE];
		ciphertext[m] = new byte[usedLastBlockSpace];
		
		byte[][] plaintext = new byte[m + 1][BLOCK_SIZE];
		plaintext[m] = new byte[usedLastBlockSpace];
		
		for (int i = 0; i < ciphertext.length; i++) {
			file_br.read(ciphertext[i]);
		}
		
		for (int i = 0; i < m - 2; i++) {
			plaintext[i] = decryption_per_block(key1, ciphertext[i], i);
		}
		
		if(!needStealing) {
			plaintext[m - 1] = decryption_per_block(key1, ciphertext[m - 1], m - 1);
			plaintext[m] = new byte[0];
		} else {
			byte[] pp = decryption_per_block(key1, plaintext[m - 1], m - 1);
			System.arraycopy(pp, 0, plaintext[m], 0, usedLastBlockSpace);
			byte[] cp = new byte[BLOCK_SIZE - usedLastBlockSpace];
			
			for (int i = (usedLastBlockSpace); i < BLOCK_SIZE; i++) {
				cp[i - usedLastBlockSpace] = pp[i];
			}
			
			byte[] cc = new byte[ciphertext[m].length + cp.length];
			System.arraycopy(ciphertext[m], 0, cc, 0, ciphertext[m].length);
			System.arraycopy(cp, 0, cc, ciphertext[m].length, cp.length);
			
			plaintext[m - 1] = decryption_per_block(key1, pp, m - 1);
		}
		
		file_br.close();
		
		RandomAccessFile out_br = new RandomAccessFile(output, "rw");
		for (int i = 0; i < ciphertext.length; i++) {
			for (int j = 0; j < ciphertext[i].length; j++) {
				out_br.write(ciphertext[i][j]);
			}
		}
		out_br.close();
	}

	
	/**
	 * 
	 * @param key1
	 * @param plaintext
	 * @param j
	 * @return
	 * @throws Exception
	 */
	public byte[] encryption_per_block(byte[] key1, byte[] plaintext, int j) throws Exception {
		AES aes = new AES();
		byte[] t = t_all_blocks[j];
		byte[] pp = xorMessageWithT(t, plaintext);
		aes = new AES();
		byte[] cc = aes.encrypt(pp, key1);
		byte[] c = xorMessageWithT(t, cc);
		
		return c;
	}
	
	/**
	 * 
	 * @param key1
	 * @param ciphertext
	 * @param j
	 * @return
	 * @throws Exception
	 */
	public byte[] decryption_per_block(byte[] key1, byte[] ciphertext, int j) throws Exception {
		AES aes = new AES();
		byte[] t = t_all_blocks[j];
		byte[] cc = xorMessageWithT(t, ciphertext);
		aes = new AES();
		byte[] pp = aes.decrypt(cc, key1);
		byte[] p = xorMessageWithT(t, pp);
		
		return p;
	}
	
	/**
	 * 
	 * @param tText T in selected block
	 * @param messageText message plaintext or ciphertext selected block
	 * @return
	 */
	public byte[] xorMessageWithT(byte[] tText, byte[] messageText) {
		byte[] result = new byte[BLOCK_SIZE];
		for (int i = 0; i < tText.length; i++) {
			result[i] = (byte) (tText[i] ^ messageText[i]);
		}
		return result;
	}
	
	/**
	 * 
	 * @param tweakEncrypted
	 */
	public void calculate_t_matrix(byte[] tweakEncrypted) {
		byte[][] mul = new byte[m + 1][BLOCK_SIZE];
		mul[0] = tweakEncrypted;
		for(int i = 0; i < m; i++) {
			for(int k = 0; k < BLOCK_SIZE; k++) {
				if(k == 0) {
					mul[i+1][k] = 
							(byte) ((2*(mul[i][k] % 128)) ^ 
									(135*(mul[i][15]/128)));
				} else {
					mul[i+1][k] = 
							(byte) ((2*(mul[i][k] % 128)) ^ 
									((mul[i][k-1]/128)));
				}	
			}
		}
		this.t_all_blocks = mul;
	}
	
	
	public static void main(String[] args) {
		byte [] arrBytes = new byte[] {-1, 0, 1, 2, 3, 10, 15, 16, 20, 25};
		System.out.println(ConverterBytesHex.bytesToHex(arrBytes));
		System.out.println(ConverterBytesHex.hexToBytes("FF000102030A0F101419"));
	}
	
}
