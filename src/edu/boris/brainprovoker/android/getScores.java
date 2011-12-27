package edu.boris.brainprovoker.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class getScores {

	private static final String SOAP_ACTION_GET = "http://ws.apache.org/axis2/sentScores";
	private static final String METHOD_NAME_GET = "sentScores";
	private static final String NAMESPACE_GET = "http://ws.apache.org/axis2";
	private static final String URL_GET = "http://10.0.2.2:8080/ProjektnaIP/services/Rezultati?wsdl";

	@SuppressWarnings("unchecked")
	public List<igralec> GetRezultat() throws IOException,
			XmlPullParserException {

		SoapObject request = new SoapObject(NAMESPACE_GET, METHOD_NAME_GET);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(request);

		HttpTransportSE sht = new HttpTransportSE(URL_GET);
		List<igralec> polje = new ArrayList<igralec>();

		try {
			sht.call(SOAP_ACTION_GET, envelope);
			Vector<String> res = (Vector<String>) envelope.getResponse();
			String[] result = res.toString().replace('[', ' ')
					.replace(']', ' ').split(",");
			for (int i = 0; i < result.length; i = i + 2) {
				igralec ig = new igralec();
				System.out.println(result[i]);
				System.out.println(result[i + 1]);
				ig.ime = result[i];
				ig.score = Integer.parseInt(result[i + 1].replace(" ", ""));
				polje.add(ig);
			}
			return polje;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static final String SOAP_ACTION_SEND = "http://ws.apache.org/axis2/getRezultati";
	private static final String METHOD_NAME_SEND = "getRezultati";
	private static final String NAMESPACE_SEND = "http://ws.apache.org/axis2";
	// private static final String URL_SEND =
	// "http://10.0.2.2:58134/Service1.asmx";
	private static final String URL_SEND = "http://10.0.2.2:8080/ProjektnaIP/services/Rezultati?wsdl";

	public void SendRezultat(String param) throws IOException,
			XmlPullParserException {

		SoapObject request = new SoapObject(NAMESPACE_SEND, METHOD_NAME_SEND);

		request.addProperty("rezultati", param);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(request);

		HttpTransportSE sht = new HttpTransportSE(URL_SEND);
		try {
			// System.out.println(param.length);
			sht.call(SOAP_ACTION_SEND, envelope);
			SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
