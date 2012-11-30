package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlGUI extends JPanel {
	private Angle angle;
	private JSpinner angleSelect;
	private double gravity;
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
		angleModel.setMinimum(0);
		angleSelect = new JSpinner(angleModel);
//		JSpinner.DefaultEditor editor = ((JSpinner.DefaultEditor)angleSelect.getEditor());
		JFormattedTextField tf = ((JSpinner.DefaultEditor)angleSelect.getEditor()).getTextField();
		tf.setEditable(false);
		angleSelect.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				angle.setAngle((Integer) angleSelect.getValue());
				System.out.println(angle.getAngle());
			}
		});
		add(angleSelect);
	}
	
}
