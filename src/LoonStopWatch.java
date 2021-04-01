import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.*;
import java.text.DecimalFormat;		// Laatste Stap

public class LoonStopWatch {


	/*
*********	Stap 1 - begin met het opzetten van de public class en de main method
			- syso"hello world"
			- uitleg wat we willen maken
				Machine waarin je je taak en je uurloon kunt invullen, zodra je op de knop klikt laat ie zien
				hoe lang je met de klus bent bezig geweest, zet het op een lijstje, en houd bij hoeveel je in
				totaal hebt verdiend.

**********	Stap 2 - tijd is dus heel belangrijk in deze, dus we beginnen met de LocalDate en LocatDateTime functie
	- laten zien docs
	- laten zien: import java.time.LocalDateTime;
	- laten zien: System.out.println(LocalDateTime.now());

	Om tijdsduur tussen twee tijdstippen te berekenen heb je een Duration nodig

	- laten zien: Duration - docs
	- laten zien: import java.time.Duration -> class om lengte tussen twee tijdstippen te berekenen - vooral geschikt voor kortere tijdspannes
	  Period bestaat ook, maar die is eerder bedoeld om aantal dagen, maanden en jaren te berekenen

	 voorbeeld met LocalTime :
	---------------------------------------------------------------------
	 LocalTime tijdStip1 = LocalTime.of(8,5,10);
         LocalTime tijdStip2 = LocalTime.of(19,3,00);
         Duration tijdsVerschil = Duration.between(tijdStip1, tijdStip2);

        System.out.println("het tijdsverschil is : " + tijdsVerschil);
	---------------------------------------------------------------------
        PT -> P staat voor period (datum) en T voor Time
	---------------------------------------------------------------------
        LocalDateTime tijdStip1 = LocalDateTime.of(8,5,10);
         LocalDateTime tijdStip2 = LocalDateTime.of(19,3,00);
         Duration tijdsVerschil = Duration.between(tijdStip1, tijdStip2);

        System.out.println("het tijdsverschil is : " + tijdsVerschil);
	---------------------------------------------------------------------
        en daarna over langere tijden:
	---------------------------------------------------------------------
        LocalDateTime tijdStip3 = LocalDateTime.of(2020,10,1,8,5,00);
        LocalDateTime tijdStip4 = LocalDateTime.of(2021,1,18,8,10,00);
        Duration tijdsVerschil2 = Duration.between(tijdStip3,tijdStip4);
        long tijdsVerschil3 = tijdsVerschil2.toDays();

        System.out.println("het tweede tijdsverschil is : " + tijdsVerschil2);
        System.out.println("het tijdsverschil in dagen in : " + tijdsVerschil3);
	-------------------------------------------------------------------------------

 ********** Stap 3 Beginnen met het opzetten van een class Tijdsmeting waar we het tijdsmeten gaan doen
        -------------------------------------------------
        class Tijdsmeting {}
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime eind;
		-------------------------------------------------
        en dan in de main:
        -----------------------------------
        System.out.println(LocalDateTime.now());

        tijdsMeting klok = new tijdsMeting();
        System.out.println(klok.start);
        System.out.println(klok.eind);     -----> null, want die is enkel gedeclareerd en nog niet ingevuld.
		-----------------------------------------
***********        Stap 4 In deze stap willen we Swing gaan opbouwen
        - Uitleggen dat Swing een gedateerde API van Oracle is die je in staat stelt een
        Graphical User Interface te maken voor je Java programma. Iets moderner is JavaFX
        (maar dat wordt niet ondersteund nog door Repl.It en vereist wat installeerwerk op je IDE)
        - Veel moderner is het je Java programma aan een frontend in JS, html, Css te hangen, maar dat kost teveel tijd voor hier
        - beginnen met het opzetten van een frame
        ----------------------------------
        import javax.swing.*;
        *
        JFrame kader = new JFrame();
        kader.setSize(1000,600);
		kader.setLayout(null);
		kader.setVisible(true);
		----------------------------------
************ Stap 5 Knop gaan bouwen om een tijdsmeting te doen
		------------------------------------
		JButton knop=new JButton("Tijd opnemen");
		knop.setBounds(30,100,120, 40);

		kader.add(knop)

		------------------------------------
		en dan een eventlistener toevoegen aan de knop (zodat ie ook wat gaat doen zodra er geklikt wordt
		---------------------------------
		knop.addActionListener(e ->
		{
			System.out.println("knop gedrukt");
		});
		-----------------------------------
		en dan moeten we gebruik gaan maken van de tijd en daar een meting van maken
		-----------------------------------
		klok.eind = LocalDateTime.now();
        Duration tijdsVerschil = Duration.between(klok.start, klok.eind);
        System.out.println("Het verschil is : " + tijdsVerschil);
        -------------------------------------
        je ziet dat de tijd blijft oplopen en het verschil groter wordt, en dat komt doordat het beginpunt (klok.start)
        nog steeds hetzelfde blijft. We zullen die dus moeten herïnitialiseren:
        -----------------------------------
        klok.start = klok.eind;
        ----------------------------------
		we hebben nu dus een knop die een tijdsmeting start en de periode meet tussen dat je op de knop gedrukt hebt

*********** Stap 6 Velden bouwen om de Activiteit en het Uurloon in te vullen
		We gaan nu gebruik maken van een Swing Textfields om een veld te maken voor:
		* de activiteit
		* het uurloon
		* een outputveld waar de taken komen te staan
		* ook geven we alle velden labels
		----------------------------------------
		JTextField activiteitVeld = new JTextField();
		activiteitVeld.setBounds(30,150,100, 40);

		kader.add(activiteitVeld);
		------------------------------------------
		nu draaien en dan zien we het veld
		------------------------------------------
		JTextField uurLoon = new JTextField(10);
		uurLoon.setBounds(30,200,100, 40);

		kader.add(uurLoon);
		-------------------------------------------
		nu draaien en dan zien we het tweede veld eronder.
		Nu willen we een tekstblok maken waarin we de lijst maken van de taken en
		de hoeveelheid seconden.
		-------------------------------------------
		JTextArea textArea = new JTextArea(50, 200);
		textArea.setBounds(30, 250, 700, 200);

		kader.add(textArea);
		-------------------------------------------
		nu draaien en dan zien we een tekstarea eronder. Nu zouden wat labels nog wel heel handig zijn
		----------------------------------------------
		JLabel activiteitLabel = new JLabel("Voer hier de activiteit in");
		activiteitLabel.setBounds(150,150,200,40);

		kader.add(activiteitLabel);
		----------------------------------------------
		en nu nog voor het uurloon
		----------------------------------------------
		JLabel uurLoonLabel = new JLabel("Voer hier het uurloon in met een . ");
		uurLoonLabel.setBounds(150,200,200,40);

		kader.add(uurLoonLabel)
		------------------------------------------------
		en dan hebben we nu de velden in onze GUI uiteengezet

********** Stap 7 - Nu willen we dingen gaan invoeren en op de lijst krijgen EN we willen de tijdsmeting koppelen aan
* 			het uurloon, zodat we kunnen zien hoeveel we per taak verdiend hebben
		--------------------------------------------------
		String activiteit = activiteitVeld.getText();
        System.out.println(activiteit);
        ----------------------------------------------
        run en nu zie je dat je invoer in de console verschijnt. Dan is het nu zaak het Uurloon ook hier te krijgen
        en te zorgen dat het in een rekenbaar type komt, in dit geval een double
        ------------------------------------------------
        String brutoUurloon = uurLoon.getText();
		double loon = Double.parseDouble(brutoUurloon);
		------------------------------------------------
		nu willen we zorgen dat deze informatie in het Textarea verschijnt en dit doen we daar de textArea aan te roepen
		en daar de text in te plaatsen met setText
		---------------------------------------------------
		textArea.setText(activiteit + " met een uurloon van " + brutoUurloon + "\n");
		---------------------------------------------------
		je ziet nu dat het vorige item steeds weggehaald wordt, en dat willen we niet, dus daarom voegen we dit toe:
		----------------------------------------------------
		textArea.setText(textArea.getText() + activiteit + " met een uurloon van " + brutoUurloon + "\n");
		----------------------------------------------------
		We hebben nu in de terminal al de tijdmeting staan (zegmaar de duur per taak) en die willen we ook per taak laten zien
		----------------------------------------------------
		textArea.setText(textArea.getText() + activiteit + " met een uurloon van " + loon + " : " + tijdsVerschil.getSeconds() + " seconden " + "\n");
		----------------------------------------------------
		nu wil je nog zien hoeveel je per taak hebt verdiend en dat doe je door het uurloon om te rekenen naar loon per seconden
		en dan te vermenigvuldigen met het gemeten aantal seconden.
		---------------------------------------------------
		lijst.setText(lijst.getText() + activiteit + " met een uurloon van " + loon + " : " + tijdsVerschil.getSeconds() + " seconden gewerkt en " + ((loon/3600) * tijdsVerschil.getSeconds()) + " verdiend\n");
		---------------------------------------------------
		nu heb je wel heel veel getallen achter de komma, dat kan wel wat mooier
		---------------------------------------------------
		import java.text.DecimalFormat;
		private static DecimalFormat df = new DecimalFormat("0.00");

		long aantalSeconden = tijdsVerschil.getSeconds();
        String verdiendText = df.format((loon/3600) * aantalSeconden);

		lijst.setText(lijst.getText() + activiteit + " met een uurloon van " + loon + " : " + aantalSeconden + " seconden gewerkt en " + verdiendText + " verdiend\n");
		----------------------------------------------------------

*********** Stap 8 Totaalverdiend toevoegen
		nu is het wat netter. Als allerlaatste willen we natuurlijk ook een idee hebben van hoeveel we nu in totaal
		verdiend hebben, dus daar voegen we een textfield toe onder de text area, met ook een label:
		------------------------------------------------------------------
		JLabel totaalLabel = new JLabel("Totaal verdiend: ");
		totaalLabel.setBounds(30,460,100,40);

		JTextField totaal = new JTextField(20);
		totaal.setBounds(150, 460, 300, 40);

		kader.add(totaalLabel);
		kader.add(totaal);
		---------------------------------------------------------------
		nu draaien en dan zie je het veld en de label verschijnen. Nu willen we nog een variabele maken waarin
		het totaal verdiende bedrag wordt bijgehouden:
		---------------------------------------------------------------
		static double totaalVerdiend = 0;
		--------------------------------------------------------------
		nu willen we iedere keer het verdiende loon gaan optellen bij deze variabele
		--------------------------------------------------------------
		totaalVerdiend = totaalVerdiend + ((loon/3600) * aantalSeconden);
		--------------------------------------------------------------
		Let op: we hebben hier dus opnieuw het Loon uitgerekend, maar omdat de eerdere lijn hem via die formatter in
		een String dwingt, moeten we nog eens uitrekeken. Alternatief is de string te parsen als een double:
		-----------------------------------------------------------------
		totaalVerdiend = totaalVerdiend + Double.parseDouble(verdiendText);
		-----------------------------------------------------------------
		het allerlaastste wat we nu moeten doen is deze variabelen aan het TextField gaan toewijzen onderaan:
		-------------------------------------------------------------------
		totaal.setText(df.format(totaalVerdiend) + " Euro verdiend!" );
		-------------------------------------------------------------------
		Draaien en klaar, dank voor de aandacht :-)
		- Challenges:
			- maak een reset-knop
			- zet dit om naar JavaFX of naar een FrontEnd
			- zorg dat er weekend-toeslagen berekend worden

	 */
	
	static double totaalVerdiend = 0;
	private static DecimalFormat df = new DecimalFormat("0.00"); //Laatste stap


	public static void main(String[] args) {  
		JFrame kader = new JFrame();
		
		JButton knop=new JButton("Tijd opnemen");
		knop.setBounds(30,100,120, 40);

		JLabel activiteitLabel = new JLabel("Voer hier de activiteit in");
		activiteitLabel.setBounds(150,150,200,40);

		JTextField activiteitVeld = new JTextField(20);
		activiteitVeld.setBounds(30,150,100, 40);

		JLabel uurLoonLabel = new JLabel("Voer hier het uurloon in met een . ");
		uurLoonLabel.setBounds(150,200,200,40);

		JTextField uurLoon = new JTextField(10);
		uurLoon.setBounds(30,200,100, 40);
		
		JTextArea textArea = new JTextArea(50, 200);
		textArea.setBounds(30, 250, 700, 200);

		JLabel totaalLabel = new JLabel("Totaal verdiend: ");
		totaalLabel.setBounds(30,460,100,40);

		JTextField totaal = new JTextField(20);
		totaal.setBounds(150, 460, 300, 40);

		TijdsMeting tm = new TijdsMeting();
		
		knop.addActionListener(e ->
		{
			tm.eind = LocalDateTime.now();
			String ingevuld = activiteitVeld.getText();
			String brutoUurloon = uurLoon.getText();
			double loon = Double.parseDouble(brutoUurloon);
			Duration diff = Duration.between(tm.start, tm.eind);
			tm.start = tm.eind;
			totaalVerdiend = totaalVerdiend + (loon / 3600) * diff.getSeconds();
		    textArea.setText(textArea.getText() +  ingevuld + " - aantal sec.: "+ diff.getSeconds() + " - uurloon - " + loon + " - verdiend : " + ((loon / 3600) * diff.getSeconds()) + "\n");
		    totaal.setText(df.format(totaalVerdiend) + " Euro verdiend!" );
		});	
				
		kader.add(knop);
		kader.add(activiteitLabel);
		kader.add(activiteitVeld);
		kader.add(uurLoonLabel);
		kader.add(uurLoon);
		kader.add(textArea);
		kader.add(totaalLabel);
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