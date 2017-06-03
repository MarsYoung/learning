package com.marsyoung.algorithm.hash;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 把任意长度的输入（又叫做预映射， pre-image）， 通过散列算法，变换成固定长度的输出，该输出就是散列值
 * 
 * @author zhiyuma
 *
 */
public class Hash {

	int maxValue = 100000;

	/**
	 * 直接取余法
	 * 
	 * @param x
	 * @return
	 */
	public int hash0(int x) {
		return x % maxValue;
	}

	public String MD2() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return new String(DigestUtils.getDigest("MD2").digest("hello".getBytes()),"UTF-8");

	}

	/**
	 * 1. java.security.MessageDigest 中已经定义了 MD5 的计算，我们只需要简单地调用即可得到 MD5 的128
	 * 位整数，然后将此 128 位计 16 个字节转换成 16 进制表示即可。 2.
	 * 或者引用一些现有的包，如org.apache.commons.codec.digest;
	 * 
	 * @return
	 */
	public static String getMD5(byte[] source) {
		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
			// 用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
			// 所以表示成 16 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
				// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
				// >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	

	public String sha1() {
		return DigestUtils.sha1Hex("hello");
	}

	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Hash h=new Hash();
		System.out.println(h.MD2());
		System.out.println(h.sha1());
		System.out.println(getMD5("hello".getBytes()));
	}
}
