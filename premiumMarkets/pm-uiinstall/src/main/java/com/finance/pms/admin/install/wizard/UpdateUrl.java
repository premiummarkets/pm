/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.admin.install.wizard;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;


// TODO: Auto-generated Javadoc
/**
 * The Class IntroPanel.
 * 
 * @author Guillaume Thoreton
 */
public class UpdateUrl extends JPanel {
 
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6101824066434845051L;
	
    //private JEditorPane editorPane;
   
    
    /** The content panel. */
    private JPanel contentPanel;
    
    JPanel jPanel1;

    
    /** The icon label. */
    private JLabel iconLabel;
    
    /** The icon. */
    private ImageIcon icon;
    
    JLabel jLabel1;

    JLabel jLabel2;
    
    JLabel label3;
    JLabel label4;
    JLabel label5;
    
    JLabel label6;
    JButton buttonOk;
    JButton buttonNo;
    
	MyObs observable;
	String versionNumber;
    

    /**
     * Instantiates a new intro panel.
     * 
     * @author Guillaume Thoreton
     */
    public UpdateUrl() {
    	
    	observable = new MyObs();
        
        iconLabel = new JLabel();
        contentPanel = getContentPanel();
        contentPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        icon = getImageIcon();

        setLayout(new java.awt.BorderLayout());

        if (icon != null)
            iconLabel.setIcon(icon);
        
        iconLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        
        add(iconLabel, BorderLayout.WEST);
        
        JPanel secondaryPanel = new JPanel();
        secondaryPanel.add(contentPanel, BorderLayout.NORTH);
        add(secondaryPanel, BorderLayout.CENTER);
    }
    
    
    /**
     * Gets the content panel.
     * 
     * @return the content panel
     */
    private JPanel getContentPanel() {
    	
    	JPanel contentPanel1 = new JPanel();
    	contentPanel1.setLayout(new java.awt.BorderLayout());
    	
        jPanel1 = new JPanel();
        jPanel1.setLayout(new java.awt.GridLayout(0, 1));
        
        jLabel1 = new JLabel();
        jLabel1.setText("Checking for updates ...");
        jPanel1.add(jLabel1);
        
        jLabel2 = new JLabel();
        jPanel1.add(jLabel2);
        label3 = new JLabel();
        label3.setText("");
        jPanel1.add(label3);
        label4 = new JLabel();
        label4.setText("");
        jPanel1.add(label4);
        label5 = new JLabel();
        jPanel1.add(label5);
        
		label6 = new JLabel();
        label6.setText("Download latest?");
        label6.setVisible(false);
        jPanel1.add(label6);

        buttonOk = new JButton("Ok");
        buttonOk.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				Runnable runnable = new Runnable() {
					public void run() {
						runLatest();
					}
				};
				
				Thread thread = new Thread(runnable);
				thread.start();
				
				try {
					Thread.sleep(10000);
				} catch (InterruptedException ed) {
					ed.printStackTrace();
				}
				
				System.exit(0);
				
			}
        	
        });
        buttonOk.setVisible(false);
        
        buttonNo = new JButton("No");
        buttonNo.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent e) {
        		observable.setChanged();
        		observable.notifyObservers();
        	}
        });
        buttonNo.setVisible(false);
        
        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new java.awt.GridLayout(0, 2));
        jPanel2.add(buttonOk);
        jPanel2.add(buttonNo);
        
        jPanel1.add(jPanel2);
        
        
        
        contentPanel1.add(jPanel1,BorderLayout.SOUTH);

        return contentPanel1;
        
    }


	/**
	 * @param jPanel1
	 */
	public void downLoadLatest(final String vn, Observer observer) {
	     
		versionNumber = vn;
    	observable.addObserver(observer);
    	
    	buttonNo.setVisible(true);
    	buttonOk.setVisible(true);
    	label6.setVisible(true);

	}
    
	/**
	 * 
	 */
	private void runLatest() {
		
		try {
			String params[];
			params = new String[] {
					System.getProperty("java.home")+File.separator+"bin"+File.separator+"javaws",
					"http://sourceforge.net/projects/pmsqueak/files/PremiumMarkets.jnlp/download"};
			Process process = Runtime.getRuntime().exec(params);
			String line = null;
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
					
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    

    /**
     * Gets the image icon.
     * 
     * @return the image icon
     */
    private ImageIcon getImageIcon() {
    	URL resource = this.getClass().getClassLoader().getResource(Install.iconFile+".png");
    	if (resource != null){
    		return new ImageIcon(resource);
    	} else {
    		return new ImageIcon();
    	}
    }
    
    class ActivatedHyperlinkListener implements HyperlinkListener {

    	  JEditorPane editorPane;

    	  public ActivatedHyperlinkListener(JEditorPane editorPane) {
    	    this.editorPane = editorPane;
    	  }

    	  public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {
    	    HyperlinkEvent.EventType type = hyperlinkEvent.getEventType();
    	    final URL url = hyperlinkEvent.getURL();
    	    if (type == HyperlinkEvent.EventType.ENTERED) {
    	      System.out.println("URL: " + url);
    	    } else if (type == HyperlinkEvent.EventType.ACTIVATED) {
    	      System.out.println("Activated");
    	      Document doc = editorPane.getDocument();
    	      try {
    	        editorPane.setPage(url);
    	      } catch (IOException ioException) {
    	        System.out.println("Error following link");
    	        editorPane.setDocument(doc);
    	      }
    	    }
    	  }
    	}
    
    class MyObs extends Observable {

		@Override
		public synchronized void setChanged() {
			super.setChanged();
		}
    	
    }
 
}
