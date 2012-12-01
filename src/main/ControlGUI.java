package main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class ControlGUI extends JPanel {
	private Angle angle;
	private JSpinner angleSelect;
	private JLabel gravityLabel, scoreLabel;
//	public void displayAnglePanel() {
//		
//	}
	
//	public void drawAngle() {
//		
//	}
	public ControlGUI() {
		angle = new Angle(0);
		setBorder(new TitledBorder("Missle Angle"));
		setPreferredSize(new Dimension(GameGUI.FRAME_WIDTH, 80));
		SpinnerNumberModel angleModel = new SpinnerNumberModel();
		angleModel.setMaximum(180);
		angleModel.setMinimum(0);
		angleSelect = new JSpinner(angleModel);
//		JSpinner.DefaultEditor editor = ((JSpinner.DefaultEditor)angleSelect.getEditor());
		JFormattedTextField tf = ((JSpinner.DefaultEditor)angleSelect.getEditor()).getTextField();
		tf.setEditable(false);
		angleSelect.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				angle.setDegrees((Integer) angleSelect.getValue());
			}
		});
		angleSelect.setSize(100, 20);
		add(angleSelect);
		
		JPanel gravityPanel = new JPanel(new BorderLayout());
		gravityPanel.setBorder(new TitledBorder("Gravity"));
		gravityPanel.setPreferredSize(new Dimension(60, 40));
		gravityLabel = new JLabel();
		gravityLabel.setAlignmentY(CENTER_ALIGNMENT);
		gravityPanel.add(gravityLabel, BorderLayout.CENTER);
		add(gravityPanel);
		
		JPanel scorePanel = new JPanel(new BorderLayout());
		scorePanel.setBorder(new TitledBorder("Score"));
		scorePanel.setPreferredSize(new Dimension(80, 40));
		scoreLabel = new JLabel();
		scoreLabel.setText(Integer.toString(0));
		scoreLabel.setAlignmentY(CENTER_ALIGNMENT);
		scorePanel.add(scoreLabel, BorderLayout.CENTER);
		add(scorePanel);
	}
	
	public void setGravity(double gravity) {
		gravityLabel.setText(Double.toString(gravity));
	}
	
	public void setPointsScored(int points) {
		scoreLabel.setText(Integer.toString(points));
	}
	
	public Angle getAngle() {
		return angle;
	}
	
}
