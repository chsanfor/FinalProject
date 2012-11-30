package main;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlGUI extends JPanel {
	Angle angle;
	JSpinner angleSelect;
//	public void displayAnglePanel() {
//		
//	}
	
//	public void drawAngle() {
//		
//	}
	public ControlGUI() {
		angle = new Angle(0);
		setBorder(new TitledBorder("Missle Angle"));
		SpinnerNumberModel angleModel = new SpinnerNumberModel();
		angleModel.setMaximum(180);
		angleSelect = new JSpinner(angleModel);
		angleSelect.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				angle.setAngle((Integer) angleSelect.getValue());
			}
		});
		add(angleSelect);
	}
}
