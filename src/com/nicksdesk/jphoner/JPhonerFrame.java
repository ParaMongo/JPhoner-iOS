package com.nicksdesk.jphoner;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import com.nicksdesk.utils.ExeUtils;
import com.nicksdesk.utils.PhoneUtils;
import com.nicksdesk.utils.PhoneUtils.DeviceListType;

public class JPhonerFrame extends JFrame {

	private static final long serialVersionUID = -4420451369918534285L;
	private static final ExeUtils exec = new ExeUtils();
	
	public JPhonerFrame() {
        initComponents();
    }
                     
    private void initComponents() {
        jLabel1 = new JLabel();
        actionsPanel = new JPanel();
        jLabel2 = new JLabel();
        getDeviceInfoButton = new JButton();
        deviceInfoType = new JComboBox<>();
        jLabel3 = new JLabel();
        createBackupButton = new JButton();
        restoreBackupButton = new JButton();
        jPanel1 = new JPanel();
        quitButton = new JButton();
        aboutButton = new JButton();
        actionProgress = new JProgressBar();
        selectDevice = new JComboBox<>();
        jLabel4 = new JLabel();
        useDeviceButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("MV Boli", 1, 24));
        jLabel1.setText("JPhoner - ultimate iDevice manipulator");

        actionsPanel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("MV Boli", 1, 14));
        jLabel2.setText("Get Device Info");

        getDeviceInfoButton.setText("Go!");
        getDeviceInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                getDeviceInfoButtonActionPerformed(evt);
            }
        });

        deviceInfoType.setModel(new DefaultComboBoxModel<>(new String[] { "All Info", "Device Name", "Device Phone Number", "Device ID" }));
        deviceInfoType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deviceInfoTypeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("MV Boli", 1, 14));
        jLabel3.setText("Make A Backup");

        createBackupButton.setText("Create Backup");
        createBackupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                createBackupButtonActionPerformed(evt);
            }
        });
        
        actionProgress.setMaximum(100);
        actionProgress.setMinimum(0);

        restoreBackupButton.setText("Restore A Backup");
        restoreBackupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                restoreBackupButtonActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        GroupLayout actionsPanelLayout = new GroupLayout(actionsPanel);
        actionsPanel.setLayout(actionsPanelLayout);
        actionsPanelLayout.setHorizontalGroup(
            actionsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
            .addGroup(actionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createBackupButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(restoreBackupButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(234, 234, 234)
                .addGroup(actionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deviceInfoType, GroupLayout.Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(getDeviceInfoButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(GroupLayout.Alignment.LEADING, actionsPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        actionsPanelLayout.setVerticalGroup(
            actionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(actionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actionsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(actionsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(deviceInfoType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(createBackupButton))
                .addGap(13, 13, 13)
                .addGroup(actionsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(getDeviceInfoButton)
                    .addComponent(restoreBackupButton))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        quitButton.setText("Quit JPhoner");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        aboutButton.setText("About JPhoner");
        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });

        selectDevice.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        selectDevice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                selectDeviceActionPerformed(evt);
            }
        });
        
        //TODO: Add Multi-Device support!
        selectDevice.setEnabled(false);
        useDeviceButton.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("MV Boli", 1, 14)); // NOI18N
        jLabel4.setText("Select Device:");

        useDeviceButton.setText("Use Device!");
        useDeviceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                useDeviceButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(quitButton, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(aboutButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectDevice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(useDeviceButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(actionsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(actionProgress, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 40, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(selectDevice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(useDeviceButton))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(actionsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(actionProgress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(quitButton)
                    .addComponent(aboutButton))
                .addContainerGap())
        );

        this.pack();
    }                     

    private void deviceInfoTypeActionPerformed(ActionEvent evt) {                                               
    	System.out.println("[Type Selector] " + deviceInfoType.getSelectedItem() + " selected.");
    }                                              

    private void getDeviceInfoButtonActionPerformed(ActionEvent evt) {
    	if(PhoneUtils.isDeviceConnected(DeviceListType.ALL)) {
    		String response = "";
        	JScrollPane bigTextShower = null;
            try {
            	int index = deviceInfoType.getSelectedIndex();
                switch(index) {
                	case 0:
                		JTextArea bigText = new JTextArea(PhoneUtils.getDeviceInfo());
                		bigTextShower = new JScrollPane(bigText) {
    						private static final long serialVersionUID = 1L;
                			public Dimension getPreferredSize() {
                				return new Dimension(480, 300);
                			}
                		};
                		bigText.setEditable(false);
                		response = null;
                		break;
                	case 1:
                		response = PhoneUtils.getDeviceName();
                		break;
                	case 2:
                		response = PhoneUtils.getDevicePhoneNumber();
                		break;
                	case 3:
                		response = PhoneUtils.getDeviceId();
                		break;
                	default:
                		response = "Please Select an Option!";
                		break;
                }
                if(response != null) {
                	JOptionPane.showMessageDialog(null, response, "Device Info:", JOptionPane.INFORMATION_MESSAGE);
                } else if(response == null && bigTextShower != null) {
                	JOptionPane.showMessageDialog(null, bigTextShower, "Device Info:", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch(Exception e) {
            	e.printStackTrace();
            }
    	} else {
    		System.out.println("[Warning] Device disconnected!");
    		JOptionPane.showMessageDialog(null, "The device being managed has been disconnected, aborting!", "Device", JOptionPane.WARNING_MESSAGE);
    		System.exit(0);
    	}
    }    

    private void quitButtonActionPerformed(ActionEvent evt) {
    	this.dispose();
    	PhoneUtils.quit();
        //System.exit(0);
    }                                          

    private void aboutButtonActionPerformed(ActionEvent evt) {   
        JOptionPane.showMessageDialog(null, "--------------------\nName: JPhoner\nCreator: Nicklvsa\nVersion: 0.1\nBuild: Beta\n--------------------", "About JPhoner", JOptionPane.INFORMATION_MESSAGE);
    }                                           

    private void useDeviceButtonActionPerformed(ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void selectDeviceActionPerformed(ActionEvent evt) {                                             
        System.out.println("[Device Selector] " + selectDevice.getSelectedItem() + " selected.");
    }
    
    public static void alertBackupThreadStarted(String msg) {
    	jLabel3.setText(msg);
    }
    
    public static void updateProgress(int update) {
    	actionProgress.setValue(actionProgress.getValue() + update);
    }
    
    public static void setProgress(int set) {
    	actionProgress.setValue(set);
    }
    
    public static void toggleBackupOptions() {
    	createBackupButton.setEnabled(!createBackupButton.isEnabled());
    	restoreBackupButton.setEnabled(!restoreBackupButton.isEnabled());
    	quitButton.setEnabled(!quitButton.isEnabled());
    }

    private void createBackupButtonActionPerformed(ActionEvent evt) {                                                   
    	try {
    		JFileChooser backupPath = new JFileChooser();
    		backupPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    		int choose = backupPath.showOpenDialog(this);
    		if(choose == JFileChooser.APPROVE_OPTION) {
    			if(!backupPath.getSelectedFile().isDirectory()) {
        			JOptionPane.showMessageDialog(null, "Please make sure you selected a valid backup folder!", "Warning!", JOptionPane.WARNING_MESSAGE);
        			createBackupButtonActionPerformed(evt);
        		} else {
        			toggleBackupOptions();
        			exec.startBackupThread(backupPath.getSelectedFile());
        		}
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }                                                  

    private void restoreBackupButtonActionPerformed(ActionEvent evt) { 
    	String id = PhoneUtils.getDeviceId();
    	try {
    		JFileChooser backupPath = new JFileChooser();
    		backupPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    		int choose = backupPath.showOpenDialog(this);
    		if(choose == JFileChooser.APPROVE_OPTION) {
    			if(!backupPath.getSelectedFile().isDirectory()) {
        			JOptionPane.showMessageDialog(null, "Please make sure you selected a valid restore folder!", "Warning!", JOptionPane.WARNING_MESSAGE);
        			createBackupButtonActionPerformed(evt);
        		} else if(backupPath.getSelectedFile().getName().contains(id)) {
        			JOptionPane.showMessageDialog(null, "Please select the parent folder!", "Error!", JOptionPane.ERROR_MESSAGE);
        		} else {
        			toggleBackupOptions();
        			System.out.println(PhoneUtils.getDeviceId());
        			exec.startRestoreThread(backupPath.getSelectedFile());
        		}
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }      
                      
    public JButton aboutButton;
    public static JProgressBar actionProgress;
    public JPanel actionsPanel;
    public static JButton createBackupButton;
    public JComboBox<String> deviceInfoType;
    public JButton getDeviceInfoButton;
    public JLabel jLabel1;
    public JLabel jLabel2;
    public static JLabel jLabel3;
    public JLabel jLabel4;
    public JPanel jPanel1;
    public static JButton quitButton;
    public static JButton restoreBackupButton;
    public JComboBox<String> selectDevice;
    public JButton useDeviceButton;                 
}
