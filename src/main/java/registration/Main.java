package registration;

// Include the Dropbox SDK.

import java.io.*;
import java.util.Locale;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;


public class Main {
	public static void main(String[] args) throws IOException, DbxException {
		// Get your app key and secret from the Dropbox developers website.
		final String APP_KEY = "qxu8dakd57wr1ce";
		final String APP_SECRET = "gb6jn7c4v1htuhc";

		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

		DbxRequestConfig config = new DbxRequestConfig(
				"JavaTutorial/1.0", Locale.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		
		// Get authorization url
		String authorizeUrl = webAuth.start();
		
		// Now, ask user to sign in and authorize
		System.out.println("1. Go to: " + authorizeUrl);
		System.out.println("2. Click \"Allow\" (you might have to log in first)");
		System.out.println("3. Copy the authorization code.");
		String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
		
		DbxAuthFinish authFinish = webAuth.finish(code);
		String accessToken = authFinish.accessToken;
		
		DbxClient client = new DbxClient(config, accessToken);
		System.out.println("Linked account: " + client.getAccountInfo().displayName);
	}
}
