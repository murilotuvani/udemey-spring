package udemyspring.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtils {

	public static String gerarBCrypt(String senha) {
		String hash = null;
		if (senha != null) {
			BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
			hash = bCryptEncoder.encode(senha);
		}
		return hash;
	}

	public static boolean senhaValida(String string, String senhaEncoded) {
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.matches(string, senhaEncoded);
	}
}
