package translate.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {
	private static final int timeout = 10000;

	public static String get(String requestUrl) {
		String result = "";
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(timeout);

			int reponseCode = connection.getResponseCode();
			if (reponseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder sb = new StringBuilder();

				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				reader.close();
				result = sb.toString();
			} else {
				System.out.println("翻译请求异常：" + reponseCode);
			}

		} catch (MalformedURLException e) {
			System.out.println("Url 格式不正确");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
