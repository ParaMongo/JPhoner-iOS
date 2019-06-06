package com.nicksdesk.utils;

public class PhoneUtils {

	private static ExeUtils exec = new ExeUtils();
	
	public static boolean isDeviceConnected(DeviceListType connectionType) {
		
		String[] args = {"-l"};
		
		switch(connectionType) {
			case ALL:
				args = new String[] {"-l"};
				break;
			case USB:
				args = new String[] {"-l", "--usb"};
				break;
			case NETWORK:
				args = new String[] {"-l", "--network"};
				break;
			default:
				break;
		}

		exec.setArgs(args);
		
		try {
			String response = exec.execute("idevice_id", false);
			return (!response.isEmpty() && response != null && !response.equalsIgnoreCase("Empty!") && !response.toLowerCase().contains("error"));
		} catch(Exception e) {
			Console.log(e.getMessage());
			return false;
		}
	}
	public static String getDeviceInfo() {
		exec.clearArgs();
		try {
			String response = exec.execute("ideviceinfo", false);
			return response;
		} catch(Exception e) {
			e.printStackTrace();
			return "Error!";
		}
	}
	
	public static String getDeviceId() {
		String[] args = {"-l"};
		exec.setArgs(args);
		try {
			String response = exec.execute("idevice_id", false);
			return response;
		} catch(Exception e) {
			e.printStackTrace();
			return "Error!";
		}
	}
	
	public static String getDeviceName() {
		exec.clearArgs();
		try {
			String response = exec.execute("idevicename", false);
			return response;
		} catch(Exception e) {
			e.printStackTrace();
			return "Error!";
		}
	}
	
	public static String getDeviceApps() {
		String[] args = {"-l"};
		exec.setArgs(args);
		try {
			String response = exec.execute("ideviceinstaller", false);
			return response;
		} catch(Exception e) {
			e.printStackTrace();
			return "Error!";
		}
	}
	public static String createDeviceBackup(String dir) {
		String[] args = {"backup", dir};
		exec.setArgs(args);
		try {
			String response = exec.execute("idevicebackup2", false);
			return response;
		} catch(Exception e) {
			e.printStackTrace();
			return "Error!";
		}
	}
	public static String restoreDeviceBackup(String dir) {
		String[] args = {"restore", dir};
		exec.setArgs(args);
		try {
			String response = exec.execute("idevicebackup2", false);
			return response;
		} catch(Exception e) {
			e.printStackTrace();
			return "Error!";
		}
	}
	
	public static String getDevicePhoneNumber() {
		exec.clearArgs();
		try {
			String response = exec.execute("ideviceinfo", false);
			String number = (response.substring(response.indexOf("PhoneNumber: ") + 13, response.indexOf("PkHash"))).replaceAll(" ", "-");
			return number;
		} catch(Exception e) {
			Console.err(e.getMessage());
			return "Error, Device might not be cellular capable!";
		}
	}

	//TODO: fix quit function, force stopping apple services seems to cause issues :/ <- do something to fix this
	public static void quit() {
		//String[] args = {"/F ", "/IM AppleMobileDeviceService.exe ", "/IM AppleMobileDeviceHelper.exe ", "/IM mDNSResponder.exe"};
		//String[] args = {"/IM mDNSResponder.exe"};
		exec.clearArgs();
		
		try {
			exec.execute("", true);
			System.exit(0);
		} catch(Exception e) {
			Console.err(e.getMessage());
		}
	}

	//TODO: make switch statement not kill certain tasks instead of appending different args!
	public enum DeviceListType {
		ALL(0), USB(1), NETWORK(2);

		private int type = 0;
		
		private DeviceListType(int type) {
			this.type = type;
		}
		
		public int getType() {
			return this.type;
		}
	}
	
}
