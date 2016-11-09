import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;

public class ExempleFrame extends UtileFrame
{
	public ExempleFrame(){
		super("Gestion",200,230);
		addChamp("Nom");
		addChamp("Prenom");
		addChamp("adresse");
		addChamp("Taux");
		addBouton("Lister");
		addBouton("Statistiquer");
	}
        @Override
	public void actionPerformed(ActionEvent event){  // Methode recoit evenemen
		if(((JButton)event.getSource()).getText()=="Lister"){
                    System.out.println("Lister");
                }
	}
        public static void main(String[] args) {
            new ExempleFrame().setVisible(true);
            
        
    }
}

 