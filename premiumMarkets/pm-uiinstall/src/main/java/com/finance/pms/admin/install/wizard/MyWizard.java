package com.finance.pms.admin.install.wizard;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import com.nexes.wizard.Wizard;

public class MyWizard extends Wizard {
	
	
	public void selectNextButton() {
		try {
			Box next = ((Box) ((JPanel)((JPanel)((JLayeredPane)((JRootPane) getDialog().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0)).getComponent(1));
			JButton button = (JButton) next.getComponent(2);
			button.requestFocusInWindow();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pressNextButton() {
		try {
			Box next = ((Box) ((JPanel)((JPanel)((JLayeredPane)((JRootPane) getDialog().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0)).getComponent(1));
			JButton button = (JButton) next.getComponent(2);
			button.doClick();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
