import java.time.Duration;
import java.time.LocalDateTime;

import javax.swing.*;

public class LoonStopWatch {  
	
	static double totaalVerdiend = 0;

	public static void main(String[] args) {  
		JFrame kader = new JFrame();
		
		JButton b=new JButton("Tijd opnemen"); 
		b.setBounds(30,100,120, 40);
		
		JTextField activiteitVeld = new JTextField(20);
		activiteitVeld.setBounds(30,150,100, 40);
		
		JTextField uurLoon = new JTextField(10);
		uurLoon.setBounds(30,200,100, 40);
		
		JTextArea textArea = new JTextArea(50, 200);
		textArea.setBounds(30, 250, 700, 200);
		
		JTextField totaal = new JTextField(20);
		totaal.setBounds(30, 460, 300, 40);				

		TijdsMeting tm = new TijdsMeting();
		
		b.addActionListener(e ->
		{
			tm.eind = LocalDateTime.now();
			String ingevuld = activiteitVeld.getText();
			String brutoUurloon = uurLoon.getText();
			double loon = Double.parseDouble(brutoUurloon);
			Duration diff = Duration.between(tm.start, tm.eind);
			tm.start = tm.eind;
			totaalVerdiend = totaalVerdiend + (loon / 3600) * diff.getSeconds();
		    textArea.setText(textArea.getText() +  ingevuld + " - aantal sec.: "+ diff.getSeconds() + " - uurloon - " + loon + " - verdiend : " + ((loon / 3600) * diff.getSeconds()) + "\n");
		    totaal.setText("Je hebt " + totaalVerdiend + " verdiend!" );
		});	
				
		kader.add(b);
		kader.add(activiteitVeld);
		kader.add(uurLoon);
		kader.add(textArea);
		kader.add(totaal);
		kader.setSize(1000,600);
		
		kader.setLayout(null); 
		kader.setVisible(true);
	}  
}  

class TijdsMeting{
	LocalDateTime start = LocalDateTime.now();
	LocalDateTime eind;
}