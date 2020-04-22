import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JFactory {
	
	public JTextField createTextField(String label, JPanel panel)
	{
		createLabel(label, panel);
		JTextField box = new JTextField();
		panel.add(box);
		return box;
	}
	
	public JComboBox<String> createDropdown(String label, JPanel panel, String[] options)
	{
		createLabel(label, panel);
		JComboBox<String> list = new JComboBox<String>(options);
		panel.add(list);
		return list;
	}
	
	public JLabel createLabel(String text, JPanel panel)
	{
		JLabel l = new JLabel(text);
		panel.add(l);
		return l;
	}
	
	public JTextArea createArea(String label, JPanel panel)
	{
		createLabel(label, panel);
		JTextArea a = new JTextArea();
		panel.add(a);
		return a;
	}
	
	public JButton createButton(String text, JPanel panel, ActionListener action)
	{
		JButton b = new JButton(text);
		b.addActionListener(action);
		panel.add(b);
		return b;
	}
}
