package com.fantagame.be.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;


import com.fantagame.be.business.service.exception.CryptoException;

public class CryptoUtils {

	public static String shaSpring(String s,String salt){
		ShaPasswordEncoder sha = new ShaPasswordEncoder(256);
		return sha.encodePassword(s, salt);
	}
	
	public static String sha1(String s, String keyString)
			throws UnsupportedEncodingException, NoSuchAlgorithmException,
			InvalidKeyException {

		SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"),
				"HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(key);

		byte[] bytes = mac.doFinal(s.getBytes("UTF-8"));

		return new String(Base64.encode(bytes));

	}

	public static byte[] byteDigest(byte[] data) throws CryptoException {

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			throw new CryptoException(e);
		}
		md.reset();
		md.update(data);
		return md.digest();

	}

	/**
	 * Creates a Base64 encoded SHA Digest of a byte[]
	 * 
	 * @param data
	 *            the data to digest
	 * @return Base64 encoded digest of the data
	 * @throws CryptoException
	 */
	public static String digest(byte[] data) throws CryptoException {

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			throw new CryptoException(e);
		}
		md.reset();
		md.update(data);
		byte[] digest = md.digest();

		return new String(Base64.encode(digest));

	}

	/**
	 * Generates a SecretKey of a specified bit length
	 * 
	 * @param bitSize
	 *            length of key
	 * @return the key as a byte[]
	 * @throws CryptoException
	 */
	public static byte[] genKey(int bitSize) throws CryptoException {

		KeyGenerator keygen;
		try {
			keygen = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {

			throw new CryptoException(e);
		}

		keygen.init(bitSize);
		SecretKey key = keygen.generateKey();
		return key.getEncoded();

	}

	public static void generatePubAndPrivateKey(boolean saveFile) {

		KeyPairGenerator kpg = null;
		try {
			kpg = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kpg.initialize(512);
		KeyPair kp = kpg.genKeyPair();
		KeyFactory fact;
		RSAPublicKeySpec pub = null;
		RSAPrivateKeySpec priv = null ;
		try {
			fact = KeyFactory.getInstance("RSA");
			pub = fact.getKeySpec(kp.getPublic(),
					RSAPublicKeySpec.class);
			 priv = fact.getKeySpec(kp.getPrivate(),
					RSAPrivateKeySpec.class);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}

		if (saveFile) {
			try {
				saveToFile("privateKey.ky", priv.getModulus(),priv.getPrivateExponent());
				saveToFile("pubeKey.ky", pub.getModulus(),pub.getPublicExponent());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void saveToFile(String fileName,  BigInteger mod, BigInteger exp) throws IOException {
		ObjectOutputStream oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
		try {
		    oout.writeObject(mod);
		    oout.writeObject(exp);
		  } catch (Exception e) {
			  throw new IOException("Unexpected error", e);
		  } finally {
			  if(oout != null)
			    oout.close();
			}
	}
	
	public static byte[] rsaEncrypt(byte[] data, PublicKey pubKey) {

		Cipher cipher = null;
		byte[] cipherData = null;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			cipherData = cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

		return cipherData;
	}

	public static byte[] rsaDecrypt(byte[] data,PrivateKey privKey) {
	
		Cipher cipher;
		byte[] cipherData = null;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privKey);
			cipherData = cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return cipherData;
	}

	

	public static void main(String args[]) {
		
		System.out.println(CryptoUtils.shaSpring("scarabeo", "cybertop"));
	}

	public static PublicKey readPublicKeyFromFile(String keyFileName) throws IOException {
		  InputStream in =  new FileInputStream(keyFileName);
		  ObjectInputStream oin =
		    new ObjectInputStream(new BufferedInputStream(in));
		  try {
		    BigInteger m = (BigInteger) oin.readObject();
		    BigInteger e = (BigInteger) oin.readObject();
		   // System.out.println("Module "+ byteArrayToHexString(m.toByteArray()));
		    //System.out.println("Exp "+ byteArrayToHexString(e.toByteArray()));
		    RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
		    KeyFactory fact = KeyFactory.getInstance("RSA");
		    PublicKey pubKey = fact.generatePublic(keySpec);
		    return pubKey;
		  } catch (Exception e) {
		    throw new RuntimeException("Spurious serialisation error", e);
		  } finally {
		    oin.close();
		  }
		}

	public static PrivateKey readPrivateKeyFromFile(String keyFileName) throws IOException {
		  InputStream in =  new FileInputStream(keyFileName);
		  ObjectInputStream oin =
		    new ObjectInputStream(new BufferedInputStream(in));
		  try {
		    BigInteger m = (BigInteger) oin.readObject();
		    BigInteger e = (BigInteger) oin.readObject();
		    RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, e);
		    KeyFactory fact = KeyFactory.getInstance("RSA");
		    PrivateKey pubKey = fact.generatePrivate(keySpec);
		    return pubKey;
		  } catch (Exception e) {
		    throw new RuntimeException("Spurious serialisation error", e);
		  } finally {
		    oin.close();
		  }
		}
	

	
}
