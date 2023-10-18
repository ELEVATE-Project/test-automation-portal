package com.shikshalokam.utils.gSheet;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.shikshalokam.constants.IAutoConst;

import org.apache.commons.collections4.map.HashedMap;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

@SuppressWarnings("deprecation")
public class GoogleSheet {

	public static GoogleSheet sheetAPI = new GoogleSheet();

	/** Application name. */
	private static final String APPLICATION_NAME = "ShikshaLokamTestData";

	/** Directory to store user credentials for this application. */
	private static final File DATA_STORE_DIR = new File(System.getProperty("user.home"),
			".credentials/sheets.googleapis.com-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	@SuppressWarnings("unused")
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/sheets.googleapis.com-java-quickstart
	 */
	private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static Credential authorize() throws IOException, GeneralSecurityException {
		Credential credential = null;
		try {
			File f = new File(IAutoConst.GOOGLE_AUTHFILE);
			credential = new GoogleCredential.Builder().setTransport(HTTP_TRANSPORT).setJsonFactory(JSON_FACTORY)
					.setServiceAccountId("SikshaLokamTestData@sikshalokam.iam.gserviceaccount.com")
					.setServiceAccountPrivateKeyFromP12File(f).setServiceAccountScopes(SCOPES).build();
			credential.refreshToken();
			System.out.println("credential " + credential);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return credential;

	}

	/**
	 * Build and return an authorized Sheets API client service.
	 * 
	 * @return an authorized Sheets API client service
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
		Credential credential = authorize();
		return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME)
				.build();
	}

	public List<List<Object>> getSpreadSheetRecords(String spreadsheetId, String range)
			throws IOException, GeneralSecurityException {
		try {
			Sheets service = getSheetsService();
			ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
			List<List<Object>> values = response.getValues();
			if (values != null && values.size() != 0) {
				return values;
			} else {
				System.out.println("No data found.");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> getSpreadSheetValues(String TEST_DATA_GOOGLESHEET, String range, int Num) throws Exception {
		List<String> sheetvalues = new ArrayList<String>();
		Map<Object, Object> m = new HashedMap<>();

		List<List<Object>> rowvalues = getSpreadSheetRecords(TEST_DATA_GOOGLESHEET, range);
		for (int j = 1; j <= rowvalues.size() - 1; j++) {
			if (j <= Num - 1) {
				for (int i = 0; i <= rowvalues.get(j).size() - 1; i++) {

					sheetvalues.add(String.valueOf(rowvalues.get(j).get(i)));

				}

			}

		}

		System.out.println("data " + m);

		return sheetvalues;
	}

	public static Object[][] getTestDataFromGoogleSheet(String spreadsheetId, String range) throws Exception {
		List<List<Object>> values = null;
		try {
			values = sheetAPI.getSpreadSheetRecords(spreadsheetId, range);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Object[][] data = new Object[values.size() - 1][values.get(0).size()];

		for (int i = 1; i < values.size(); i++) {
			Map<Object, Object> dataMap = new HashMap<Object, Object>();
			for (int j = 0; j < values.get(i).size(); j++) {
				dataMap.put(values.get(0).get(j), values.get(i).get(j));

			}
			data[i - 1][0] = dataMap;
		}

		return data;
	}

	public static Object[][] getTestDataFromGoogleSheet1(String spreadsheetId, String range) throws Exception {
		List<List<Object>> values = null;
		try {
			values = sheetAPI.getSpreadSheetRecords(spreadsheetId, range);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Object[][] data = new Object[values.size() - 1][values.get(0).size()];

		for (int i = 1; i < values.size(); i++) {
			Map<Object, Object> dataMap = new HashMap<Object, Object>();
			for (int j = 0; j < values.get(i).size(); j++) {
				dataMap.put(values.get(0).get(j), values.get(i).get(j));
			}

			data[i - 1][0] = dataMap;
		}
		return data;
	}

	public ArrayList<String> getSpreadSheetValues1(String TEST_DATA_GOOGLESHEET, String range, int Num)
			throws Exception {
		ArrayList<String> sheetvalues = new ArrayList<String>();
		Map<Object, Object> m = new HashedMap<>();

		List<List<Object>> rowvalues = getSpreadSheetRecords(TEST_DATA_GOOGLESHEET, range);
		for (int j = 1; j <= rowvalues.size() - 1; j++) {
			if (j <= Num - 1) {
				for (int i = 0; i <= rowvalues.get(j).size() - 1; i++) {
					String value = String.valueOf(rowvalues.get(j).get(i));
					sheetvalues.add(value);
					if (sheetvalues.contains(".")) {
						sheetvalues.add("");
					}

				}

			}

		}

		System.out.println("data " + m);

		return sheetvalues;
	}
//	public ArrayList<String> getSpreadSheetValues(String TEST_DATA_GOOGLESHEET, String range, int Num)
//			throws Exception {
//		ArrayList<String> sheetvalues = new ArrayList<String>();
//		Map<Object, Object> m = new HashedMap<>();
//
//		List<List<Object>> rowvalues = getSpreadSheetRecords(TEST_DATA_GOOGLESHEET, range);
//		for (int j = 1; j <= rowvalues.size() - 1; j++) {
//			if (j <= Num - 1) {
//				for (int i = 0; i <= rowvalues.get(j).size() - 1; i++) {
//					sheetvalues.add(String.valueOf(rowvalues.get(j).get(i)));
//					m.put(rowvalues.get(j), rowvalues.get(j));
//				}
//			}
//		}
//		System.out.println("sss " + m);
//		return sheetvalues;
//	}

}
