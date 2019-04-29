package com.io.giulio.utils;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class EncryptionUtils {
	private static final Logger log = LoggerFactory.getLogger(EncryptionUtils.class);
	@Autowired
	BasicTextEncryptor textEncryptor;

	public String encryption(String encryption) {
		return textEncryptor.encrypt(encryption);
	}
	
	public String decryption(String encryption) {
		return textEncryptor.decrypt(encryption);
	}
}
