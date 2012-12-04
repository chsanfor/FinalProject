package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class ControlGUI extends JPanel {
	private Angle angle;
	private JSpinner angleSelect;
	private JLabel gravityLabel, scoreLabel, shotsLabel;
//	public void displayAnglePanel() {
//		
//	}
	
//	public void drawAngle() {
//		
//	}
	public ControlGUI() {
		angle = new Angle(0);
		setBorder(new TitledBorder("Controls"));
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
		class DecreaseAction extends AbstractAction {
			
			public void actionPerformed(ActionEvent e) {
				if((Integer)angleSelect.getValue() > 0) {
					angleSelect.setValue((Integer)angleSelect.getValue() - 1);
            		angle.setDegrees((Integer)angleSelect.getValue());
				}
				angleSelect.getChangeListeners()[0].stateChanged(new ChangeEvent(0));
			}
			
		}
		class IncreaseAction extends AbstractAction {
			public void actionPerformed(ActionEvent e) {
				if((Integer)angleSelect.getValue() < 180) {
					angleSelect.setValue((Integer)angleSelect.getValue() + 1);
					angle.setDegrees((Integer)angleSelect.getValue());
				}
				angleSelect.getChangeListeners()[0].stateChanged(new ChangeEvent(0));
			}
		}
		Action down = new DecreaseAction();
		Action up = new IncreaseAction();
		this.getActionMap().put("down", down);
		this.getActionMap().put("up", up);
		
		JPanel gravityPanel = new JPanel(new BorderLayout());
		gravityPanel.setBorder(new TitledBorder("Gravity"));
		gravityPanel.setPreferredSize(new Dimension(60, 40));
		gravityLabel = new JLabel();
		gravityLabel.setAlignmentY(CENTER_ALIGNMENT);
		gravityPanel.add(gravityLabel, BorderLayout.CENTER);
		add(gravityPanel);
		
		JPanel shotsPanel = new JPanel(new BorderLayout());
		shotsPanel.setBorder(new TitledBorder("Shots Remaining"));
		shotsPanel.setPreferredSize(new Dimension(120, 40));
		shotsLabel = new JLabel();
		shotsLabel.setAlignmentY(CENTER_ALIGNMENT);
		shotsPanel.add(shotsLabel, BorderLayout.CENTER);
		add(shotsPanel);
		
		JPanel scorePanel = new JPanel(new BorderLayout());
		scorePanel.setBorder(new TitledBorder("Score"));
		scorePanel.setPreferredSize(new Dimension(80, 40));
		scoreLabel = new JLabel();
		scoreLabel.setText(Integer.toString(0));
		scoreLabel.setAlignmentY(CENTER_ALIGNMENT);
		scorePanel.add(scoreLabel, BorderLayout.CENTER);
		add(scorePanel);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");
		
		angleSelect.setSize(100, 20);
		angleSelect.setBorder(new TitledBorder("Angle"));
		add(angleSelect);
	}
	
	public void setGravity(double gravity) {
		gravityLabel.setText(Double.toString(gravity));
	}
	
	public void setPointsScored(int points) {
		scoreLabel.setText(Integer.toString(points));
	}
	
	public void setShotsRemaining(int shots) {
		shotsLabel.setText(Integer.toString(shots));
	}
	
	public Angle getAngle() {
		return angle;
	}
}
