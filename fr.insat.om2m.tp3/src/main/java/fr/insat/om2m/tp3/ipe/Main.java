package fr.insat.om2m.tp3.ipe;

import java.util.List;

import org.eclipse.jetty.server.Server;

import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHLight;

import fr.insat.om2m.tp3.hue.HueProperties;
import fr.insat.om2m.tp3.hue.HueSdkListener;
import fr.insat.om2m.tp3.hue.HueUtil;


public class Main {
	/** Csebase id */
	public final static String CSEID = "mn-cse";
	/** CseBase Name */
	public final static String CSE_NAME = "mn-name";
	/** Generic create method name */
	public final static String METHOD = "CREATE";
	/** Data container id */
	public final static String DATA = "DATA";
	/** Descriptor container id */
	public final static String DESC = "DESCRIPTOR";
	/** Indicate if the program is connected to Hue bridge */
	private static boolean connected = false;

	public static boolean isConnected(){
		synchronized (Main.class) {
			return connected;
		}
	}

	public static void setConnected(boolean state){
		synchronized (Main.class) {
			connected = state;
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Loading properties from file...");
		HueProperties.loadProperties();

		System.out.println("Registering the listener");
		// TODO Look at the HueSdkListener class.
		PHHueSDK.getInstance().getNotificationManager().registerSDKListener(new HueSdkListener());

		System.out.println("Connecting to bridge");
		// TODO implement this method!!
		HueUtil.connectToBridge();

		System.out.println("Awaiting connection...");
		while(!isConnected()){
			Thread.sleep(500);
		}

		System.out.println("Retrieving lights");
		List<PHLight> lights = HueUtil.getLights();
		System.out.println("Getting last known states");
		HueUtil.getStates(lights);

		// Example: turn on a lamp 1
		//		 HueUtil.updateDevice("1", null, null, null, true);

		
		// --------------------- //
		// To launch server part //
		// --------------------- //
		
		// Creating the IPE Server on :1400/monitor
		//		Server server = IpeServer.createServer();
		//		System.out.println("Starting the server on: " + server.getState());
		//		server.join();
	}



}
