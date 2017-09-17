package com.fantagame.be.application.security;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import com.fantagame.be.application.security.IFcae.PlainText;
import com.fantagame.be.util.CryptoUtils;

public class LoadRSAPrivateKey implements PlainText{

	private File path;
	private PrivateKey privateKey = null;
	private Cipher cipher = null;

	private void generateKey() {
		// Read Private Key.
		try {
			privateKey =CryptoUtils.readPrivateKeyFromFile(path.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getPath() {
		return path;
	}

	
	public void setPath(File path) {
		this.path = path;
		generateKey();
		initCipher();
	}

	@Override
	public byte[] getPlainText(byte[] EncrptedTex) throws Exception{
		if (privateKey == null)
			throw new IllegalArgumentException("No private Key");


		return cipher.doFinal(EncrptedTex);
		
	}

	public void initCipher() {

		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();

		}

	}

}
