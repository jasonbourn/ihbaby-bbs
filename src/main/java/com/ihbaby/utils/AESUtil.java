package com.ihbaby.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AESUtil {

	private final static String dataPassword = "weitaixincw12345"; // 密钥

	private final static byte[] KEY_VI = { '1', '2', '3', '4', '5', '6', '7', '8', '1', '2', '3', '4', '5', '6', '7',
			'8' };

	public static final String bm = "UTF-8";

	private static final Logger log = LoggerFactory.getLogger(AESUtil.class);

	private static final String salt = "p3XnKm";

	/**
	 * 
	 * decrypt: 解密加密字符串 . <br/>
	 *
	 * @param decstr
	 * @return
	 * @author wangchunxi
	 * @date 2016年4月26日 下午2:03:37
	 */
	public static String decrypt(String decstr) {
		return decrypt(decstr, dataPassword);
	}

	public static String decrypt(String decstr, String dataPassword) {
		if (StringUtils.isBlank(decstr)) {
			return "";
		}
		try {
			byte[] byteMi = parseHexStr2Byte(decstr);
			IvParameterSpec zeroIv = new IvParameterSpec(KEY_VI);
			SecretKeySpec key = new SecretKeySpec(dataPassword.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			byte[] decryptedData = cipher.doFinal(byteMi);
			return new String(decryptedData, bm);
		} catch (Exception e) {
			log.error("AesEncodeUtil decrypt error, decstr : {}", decstr, e);
			return decstr;
		}
	}

	/**
	 * 
	 * encrypt: 对字符串加密 . <br/>
	 *
	 * @param encstr
	 * @return
	 * @author wangchunxi
	 * @date 2016年4月26日 下午2:12:34
	 */
	public static String encrypt(String encstr) {
		return encrypt(encstr, dataPassword);
	}

	public static String encrypt(String encstr, String dataPassword) {
		if (StringUtils.isBlank(encstr)) {
			return "";
		}
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(KEY_VI);
			SecretKeySpec key = new SecretKeySpec(dataPassword.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			byte[] encryptedData = cipher.doFinal(encstr.getBytes(bm));
			return new String(parseByte2HexStr(encryptedData));
		} catch (Exception e) {
			log.error("AesEncodeUtil encrypt error, decstr : {}", encstr, e);
			return encstr;
		}
	}

	/**
	 * 将二进制转换成16进制
	 *
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 *
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static String getSha256Token(String serialnum, String genotype, String result_type) {
		String strSrc = serialnum + genotype + result_type + salt;
		String encName = "SHA-256";
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance(encName);
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return strDes;
	}

	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des.toUpperCase();
	}

	public static void main(String[] args) {
		String encStr = AESUtil.encrypt("13426004699");
		System.out.println("加密：" + encStr);
		String decStr = AESUtil.decrypt("F47CE3C39A8E9B5C04A6CE7BF527D6237FED303E7C150BA6267FCC1173BFF0A7B5B58AB3E18C402C5A7BED8D8AF5B05489F70DD56B69B2CC67F98420CDC19029A3E889A81F1BB1A63820C7E0F437FA9C14F5E522D3D3B3CCA67D74E3423F34B7C58B69A7F955C00830FF1CDE0FD5DB5A8A2245C15A166AE1C5324C3303CF5588AF6632DD0B33425733863740CFC891DF2EE9D6AA9C94A3135284607A1EC19A91ADDA916009689A117E567535F250869D3FDA7EB815EE47ECC612B3E911C6882AA85C994134D332F415F57D2573CFB1E5AC35E6D8CB64C7BF3741E49088B004DCD67091D0D460366FC0ECA9DA0A13B5157096CF50ED9527F59D94DE2591A4548DEA00CC66D98AEC1ED9D6D033A6F5AFC2A6718A8672C712CCDEBEEF1890ACFEB43E602CBFCCA7FC8AA9FF518F404B83CB3D9E943757151A6DC5D7A0CE3C9929C98676AA7EE52A510AECACD57BD4534702C2A40F448D715D2B564C1B14EA0AE2CE98B4C7EB8220787F66E6D9529917B6C3E3119FA04D608020836FD817687A66116D6D70C7CAD657BB459633D30B61A683CE7DE352E1C725F351FDFC2445D744AEEAFC71091602F71B8722CEAFA7356F5C9DFC19C3FFB5792C65E63CCDD2063CE5DEAE7FA3AAEB48EF1070E69FCBEFA62D21994127B009D790D495F9CFE1777AD6D8EB3ECBE18FD2DFCF102676E06BA966AA2EF13BEFBDC8BAE0E943EF7B6DC28790208431006653057BF51ABBB323A174C97EE59526717DC3A824887A95F973A9");
		System.out.println("解密：" + decStr);
	}
}
