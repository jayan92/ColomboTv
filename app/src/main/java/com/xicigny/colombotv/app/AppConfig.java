package com.xicigny.colombotv.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AppConfig {
	// Server user login url
	public static String URL_LOGIN = "http://10.0.2.2:8080/ColomboTVWebService/user/authenticate";

	// Server user register url
	public static String URL_REGISTER = "http://10.0.2.2:8080/ColomboTVWebService/user/create";

	// convert InputStream to String
	public static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}
}
