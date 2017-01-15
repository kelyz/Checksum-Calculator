package com.kelyz.y.zhang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Calculator extends JFrame {
	
	private final int height = 300;
	private final int width = 500;
	public int choice;
	public JComboBox<String> textConvert;
	public JComboBox<String> fileConvert;
	public JPanel textPanel;
	public JPanel filePanel;
	public JPanel textResults;
	public JPanel fileResults;
	
	public Calculator() {
		
		setSize(width, height);
    
		JTabbedPane tabs = new JTabbedPane();
		textPanel = new JPanel();
		filePanel = new JPanel();
		textResults = new JPanel();
		textResults.setLayout(new BoxLayout(textResults, BoxLayout.Y_AXIS));
		fileResults = new JPanel();
		fileResults.setLayout(new BoxLayout(fileResults, BoxLayout.Y_AXIS));
		
		String [] options = {"md5", "sha1", "sha256", "sha384", "sha512", "crc32", "adler32"};
		
		JTextField input = new JTextField(20);
		input.setMaximumSize(input.getPreferredSize());
		textPanel.add(input);
		textConvert = new JComboBox<String>(options);
		textPanel.add(textConvert);
		JButton textClear = new JButton("Clear");
		textPanel.add(textClear);
		textPanel.add(textResults);
		
		fileConvert = new JComboBox<String>(options);
		JButton select = new JButton("Select File");
		filePanel.add(select);
		filePanel.add(fileConvert);
		JButton fileClear = new JButton("Clear");
		filePanel.add(fileClear);
		filePanel.add(fileResults);
		
		tabs.add("Text", textPanel);
		tabs.add("File", filePanel);
		this.add(tabs);
		
		textConvert.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String option = (String)textConvert.getSelectedItem();
				String entry = input.getText();
				byte[] strArray = entry.getBytes();
				String conversion = Converter.toChecksum(option, strArray);
				Converter.toLabel(option, entry, conversion, textResults);
			}
		});
		
		textClear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				textResults.removeAll();
				textResults.revalidate();
				textResults.repaint();
			}
		});
		
		select.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String option = (String)textConvert.getSelectedItem();
				
				try {
					JFileChooser fc = new JFileChooser();
					fc.showOpenDialog(Calculator.this);	
					String filePath = fc.getSelectedFile().getAbsolutePath();
					Path path = Paths.get(filePath);
					byte[] data = Files.readAllBytes(path);
					String conversion = Converter.toChecksum(option, data);
					Converter.toLabel(option, filePath, conversion, fileResults);
					
				} catch (java.lang.NullPointerException | IOException e1){
					JOptionPane.showMessageDialog(null, "No File Found");
				}
			}
		});
		
		fileClear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				fileResults.removeAll();
				fileResults.revalidate();
				fileResults.repaint();
			}
		});
	}
}