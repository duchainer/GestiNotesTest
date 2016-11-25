package modele;


// Exemple : Ecriture  d'un seul objet (ArrayList<Groupe>) dans fichier et ensuite sa lecture
import java.awt.HeadlessException;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Serialise {

    public static void main(String[] args) throws IOException {
        // Pour �crire dans le fichier objet:
        Etablissement.initialise();
        ArrayList<Groupe> groupes = Etablissement.getTabGroupe();
        WriteTabGroupe(groupes);
        
        try {
            Etablissement.setTabGroupe(ReadTabGroupe());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        for (Groupe groupe : Etablissement.getTabGroupe()) {
            //affichage de ses attributs
            System.out.println("---------------");
            System.out.println("Numero"+groupe.getNumero());
            System.out.println("Eleves: "+groupe.listeEleve());
        }
    }
    
    public static void WriteTabGroupe(ArrayList<Groupe> groupes) throws IOException {
        // Ouverture du flux objet en sortie
        ObjectOutputStream sortie = new ObjectOutputStream(
                new FileOutputStream("groupes.txt"));
        // �criture de l'objet dans le fichier
            sortie.writeObject(groupes);
            sortie.flush();
        // fermer flux
        sortie.close();
    }

    public static ArrayList<Groupe> ReadTabGroupe() throws IOException,ClassNotFoundException,FileNotFoundException {
        ObjectInputStream entree = null;
        ArrayList<Groupe> retour = new ArrayList<Groupe>();
        try {
            //Pour lire le fichier objet :
            // Ouverture du flux objet en entr�e
            entree = new ObjectInputStream(
                    new FileInputStream("groupes.txt"));
            // Lecture de l'objet contenu dans le fichier
                retour=(ArrayList<Groupe>) entree.readObject();
        }catch (FileNotFoundException e) {
            // Exception d�clench�e si le fichier n'existe pas
            printStackTrace(e);
        } catch (EOFException e) {
            // Exception d�clench�e si la fin du fichier est atteinte
            System.out.println("Fin du fichier");
            System.out.println("Aucun Objet");
        } catch (IOException e) {
            // Exception d�clench�e si un autre probl�me acc�s fichier
            printStackTrace(e);
        } catch (ClassNotFoundException e) {
            printStackTrace(e);
        } finally {
            try {
                entree.close();
            } catch (IOException e) {
                printStackTrace(e);
            } catch (Exception e) {
                printStackTrace(e);
            }
        }
        return retour;
    }

    /*public static ArrayList<Groupe> demanderGroupes() throws NumberFormatException, HeadlessException {
        //Cr�ation d'objets de la classe vente (qui est s�rialisable)
        ArrayList<Groupe> retour = new ArrayList<Groupe>();
        int reponse=0;
        do {
            
            String numero =JOptionPane.showInputDialog(null, "Entrer le numero de vente.");
            int commission=Integer.parseInt(JOptionPane.showInputDialog(
                    null, "Entrer la commission de la vente."));
            retour.add(new Groupe(numero, commission));
            reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous entrer une autre vente?",
                    "Continuer?", JOptionPane.YES_NO_OPTION);
        } while (reponse != JOptionPane.NO_OPTION);
        return retour;
    }*/

    private static void printStackTrace(Exception e) {
        System.err.println(e.getCause());
    }
}
