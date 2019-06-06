package com.nicksdesk.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import com.nicksdesk.jphoner.JPhonerFrame;
import com.nicksdesk.utils.PhoneUtils.DeviceListType;

public class ExeUtils {
	
	private String args = "";
	//private final String ENV = System.getProperty("user.dir") + "\\natives\\";
	private final String ENV = System.getProperty("user.home")+File.separator+"Documents"+File.separator+"JPhoner"+File.separator+"natives"+File.separator;
	private String response = "";

	public String execute(String name, boolean custom) throws Exception {
		 Thread t = new Thread(() -> {
			 try {
				 ProcessBuilder builder;
				 if(!custom) {
					 builder = new ProcessBuilder("cmd.exe", "/c", "start", "/B", ENV+(name.contains(".exe") ? name : name+".exe"), args);
				 } else {
					 builder = new ProcessBuilder("cmd.exe", "/c", "start", "/B", name);
				 }
				 Console.log(builder.command());
				 builder.redirectErrorStream(true);
				 Process p = builder.start();
				 response = this.isToString(p.getInputStream());
				 try {
					 p.waitFor();
				 } catch(Exception e) {
					 e.printStackTrace();
				 }
				 p.destroy();
			 } catch(Exception e) {
				 e.printStackTrace();
			 }
		 });
		 t.run();
		 if(!t.isAlive()) {
			 t.interrupt();
		 }
		 return ((!response.toString().isEmpty()) ? response.toString() : "Empty!");
	}
	
	public void startCheckDeviceDaemon() {
		int seconds = 2;
		Timer checkDevice = new Timer();
		Runnable deviceThread = new Runnable() {
			public void run() {
				checkDevice.schedule(new TimerTask() {
					public void run() {
						if(!PhoneUtils.isDeviceConnected(DeviceListType.ALL)) {
							System.out.println("[Warning] Device disconnected!");
				    		JOptionPane.showMessageDialog(null, "The device being managed has been disconnected, aborting!", "Device", JOptionPane.WARNING_MESSAGE);
				    		System.exit(0);
						}
					}
				}, 0, seconds*1000);
			}
		};
		ExecutorService runner = Executors.newCachedThreadPool();
		runner.submit(deviceThread);
	}
	
	public void startCheckCleanCacheDaemon() {
		int seconds = 20;
		File cache = new File(""); //data folder
		Timer checkCache = new Timer();
		Runnable cacheThread = new Runnable() {
			public void run() {
				checkCache.schedule(new TimerTask() {
					public void run() {
						
					}
				}, 0, seconds*1000);
			}
		};
		ExecutorService runner = Executors.newCachedThreadPool();
		runner.submit(cacheThread);
	}
	
	public void startBackupThread(File dir) {
		JPhonerFrame.alertBackupThreadStarted("Backing Up...");
		Runnable backup = new Runnable() {
			public void run() {
				String resp = PhoneUtils.createDeviceBackup(dir.getAbsolutePath());
				if(!resp.isEmpty() && resp != null && resp.contains("Backup Successful")) {
					if(resp.contains("Backup Successful")) {
        				JOptionPane.showMessageDialog(null,	"Your device was backed up to path: "+dir.getAbsolutePath(), "Info.", JOptionPane.INFORMATION_MESSAGE);
        				JPhonerFrame.alertBackupThreadStarted("Finished!");
        				JPhonerFrame.toggleBackupOptions();
					} else {
        				JOptionPane.showMessageDialog(null,	"Backup Failed!", "Error!", JOptionPane.ERROR_MESSAGE);
        				JPhonerFrame.alertBackupThreadStarted("Backup Failed!");
        				JPhonerFrame.toggleBackupOptions();
					}
				}
			}
		};
		ExecutorService runner = Executors.newCachedThreadPool();
		runner.submit(backup);
	}
	
	public void startRestoreThread(File dir) {
		JPhonerFrame.alertBackupThreadStarted("Restoring...");
		Runnable restore = new Runnable() {
			public void run() {
				String resp = PhoneUtils.restoreDeviceBackup(dir.getAbsolutePath());
				if(!resp.isEmpty() && resp != null && resp.contains("Restore Successful")) {
					JOptionPane.showMessageDialog(null,	"Your device was backed up to path: "+dir.getAbsolutePath(), "Info.", JOptionPane.INFORMATION_MESSAGE);
    				JPhonerFrame.alertBackupThreadStarted("Finished!");
    				JPhonerFrame.toggleBackupOptions();
				} else if(resp.contains("211")) {
					JOptionPane.showMessageDialog(null,	"Please disable find my iPhone to complete the restore!", "Error.", JOptionPane.ERROR_MESSAGE);
    				JPhonerFrame.alertBackupThreadStarted("Restore Failed!");
    				JPhonerFrame.toggleBackupOptions();
				} else {
					JOptionPane.showMessageDialog(null,	"Restore Failed!", "Error!", JOptionPane.ERROR_MESSAGE);
    				JPhonerFrame.alertBackupThreadStarted("Restore Failed!");
    				JPhonerFrame.toggleBackupOptions();
				}
			}
		};
		ExecutorService runner = Executors.newCachedThreadPool();
		runner.submit(restore);
	}
	
	public void startDaemons() {
		this.startCheckDeviceDaemon();
	}
	
	public void setArgs(String[] args) {
		this.clearArgs();
		for(int i = 0; i < args.length; i++) {
			if(args.length >= 2) {
				args[0] = args[0].substring(0);
				args[0] += " ";
			}
			this.args += args[i];
			Console.log(this.args);
		}
	}
	
	public void clearArgs() {
		this.args = "";
	}
	
	public String encode(String text) {
		return Base64.getEncoder().encodeToString(text.getBytes());
	}
	
	public String decode(String encoded) {
		return new String(Base64.getDecoder().decode(encoded));
	}
	
	private String isToString(InputStream is) {
		BufferedReader reader = null;
		StringBuilder response = new StringBuilder();
		String line;
		try {
			reader = new BufferedReader(new InputStreamReader(is));
			while((line = reader.readLine()) != null) {
				response.append(line).append("\n");
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return response.toString();
	}
}