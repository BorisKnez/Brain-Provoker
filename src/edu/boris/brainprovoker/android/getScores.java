package edu.boris.brainprovoker.android;


import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class getScores {

	
	private static final String SOAP_ACTION_GET = "http://tempuri.org/sentScores";
	private static final String METHOD_NAME_GET = "sentScores";
	private static final String NAMESPACE_GET = "http://tempuri.org/";    
	private static final String URL_GET = "http://10.0.2.2:58134/Service1.asmx";

	public String GetRezultat() throws IOException, XmlPullParserException {

	    SoapObject request = new SoapObject(NAMESPACE_GET, METHOD_NAME_GET);
	    

	    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.dotNet = true;
	    envelope.setOutputSoapObject(request);

	    HttpTransportSE sht=new HttpTransportSE(URL_GET);
    	try
    	{
    		sht.call(SOAP_ACTION_GET, envelope);
    		SoapPrimitive result=(SoapPrimitive) envelope.getResponse();
    		return result.toString();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}	
    	return "napaka!";
	}
	
	
	private static final String SOAP_ACTION_SEND = "http://tempuri.org/getScores";
	private static final String METHOD_NAME_SEND = "getScores";
	private static final String NAMESPACE_SEND = "http://tempuri.org/";    
	private static final String URL_SEND = "http://10.0.2.2:58134/Service1.asmx";

	public void SendRezultat(String param) throws IOException, XmlPullParserException {

	    SoapObject request = new SoapObject(NAMESPACE_SEND, METHOD_NAME_SEND);
	    request.addProperty("st",param);

	    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.dotNet = true;
	    envelope.setOutputSoapObject(request);

	    HttpTransportSE sht=new HttpTransportSE(URL_SEND);
    	try
    	{
    		sht.call(SOAP_ACTION_SEND, envelope);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}	
	}
	

}
