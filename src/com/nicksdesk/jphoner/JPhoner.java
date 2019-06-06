package com.nicksdesk.jphoner;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.nicksdesk.utils.ExeUtils;
import com.nicksdesk.utils.PhoneUtils;
import com.nicksdesk.utils.PhoneUtils.DeviceListType;

public class JPhoner {
	
	private static final String dlURL = "http://nicksdesk.com/jphoner/natives.zip";
	private static final String path = System.getProperty("user.home")+File.separator+"Documents"+File.separator+"JPhoner";
	private static final int BUFFER_SIZE = 4096;
	private static boolean skipNatives = false;

	public static void main(String[] args) {
		if(args.length >= 1) {
			if(args[1].contains("-skipnatives")) {
				skipNatives = true;
			}
		}
		ExeUtils exec = new ExeUtils();
		if(!skipNatives) {
			if(setupRequirements()) {
				if(downloadRequirements()) {
					if(!unzipRequirements()) {
						JOptionPane.showMessageDialog(null, "There was an error unzipping the dependencies! Exiting...", "Error!", JOptionPane.ERROR_MESSAGE);
						System.exit(0);
					} 
				} else {
					JOptionPane.showMessageDialog(null, "There was an error downloading the dependencies! Exiting...", "Error!", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			}
		}
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(PhoneUtils.isDeviceConnected(DeviceListType.ALL)) {
			exec.startDaemons();
			JPhonerFrame main = new JPhonerFrame();
			main.setTitle("JPhoner");
			main.setResizable(false);
			main.setLocationRelativeTo(null);
			main.setVisible(true);
		} else {
			System.err.println("Error, could not find device!");
			JOptionPane.showMessageDialog(null, "Could not find device! Connect your device and restart JPhoner!", "Error!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	private static boolean setupRequirements() {
		File req = new File(path);
		if(!req.exists()) {
			try {
				return req.mkdir();
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return true;
		}
	}
	private static boolean unzipRequirements(){
		try {
			String zip = path+File.separator+"natives.zip";
			if (unzip(zip, path)) {
				File del = new File(zip);
				return (del.delete());
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
    }
	
	public static boolean unzip(String zipFilePath, String destDirectory) {
        try {
        	File destDir = new File(destDirectory);
            if (!destDir.exists()) {
                destDir.mkdir();
            }
            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                String filePath = destDirectory + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    extractFile(zipIn, filePath);
                } else {
                    File dir = new File(filePath);
                    dir.mkdir();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
            zipIn.close();
            return true;
        } catch(Exception e) {
        	e.printStackTrace();
        	return false;
        }
    }
   
    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
	
	private static boolean downloadRequirements(){
		String fileName = path + File.separator + "natives.zip";
		BufferedInputStream in = null;
		FileOutputStream out = null;
		try {
			URLConnection down = new URL(dlURL).openConnection();
			down.setRequestProperty("User-Agent", "Mozilla/5.0");
			in = new BufferedInputStream(down.getInputStream());
			out = new FileOutputStream(fileName);
			final byte data[] = new byte[4096];
			int count;
			while((count = in.read(data, 0, 1024)) != -1) {
				out.write(data, 0, count);
			}
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(in != null) {
					in.close();
				}
				if(out != null) {
					out.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}
}


