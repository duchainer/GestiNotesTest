package modele;

// Exemple : Ecriture  d'un seul objet (ArrayList<Groupe>) dans fichier et ensuite sa lecture
import java.awt.HeadlessException;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Raphael
 */
public class Serialise {

    /**
     *
     * @param args .
     * @throws IOException L'erreur a remonter
     * @throws ClassNotFoundException L'erreur a remonter
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Pour ecrire dans le fichier objet:
        Etablissement.initialise();
        ArrayList<Groupe> groupes = Etablissement.getTabGroupe();
        exporteGroupes(groupes,"initialisation.txt");
        
        for (Groupe groupe : Etablissement.getTabGroupe()) {
            //affichage de ses attributs
            System.out.println("---------------");
            System.out.println("Numero"+groupe.getNumero());
            System.out.println("Eleves: "+groupe.listeEleve());
        }
        System.out.println("-");
        
        importeGroupes("groupes.txt");

        for (Groupe groupe : Etablissement.getTabGroupe()) {
            //affichage de ses attributs
            System.out.println("---------------");
            System.out.println("Numero"+groupe.getNumero());
            System.out.println("Eleves: "+groupe.listeEleve());
        }
    }
    
    /**
     * Methode qui permet l'exportation d'un groupe dans un fichier texte nommee "groupes.txt"
     * @param groupes Le groupe a exporter
     * @throws IOException L'erreur a remonter
     */
    public static void exporteGroupes(ArrayList<Groupe> groupes) throws IOException {
        exporteGroupes(groupes,"groupes.txt");
    }
    
    /**
     * Methode qui permet l'exportation d'un groupe dans un fichier texte dans la destination voulue
     * @param groupes  Le groupe a exporter
     * @param filepath L'emplacement du fichier a exporter
     * @throws IOException L'erreur a remonter
     */
    public static void exporteGroupes(ArrayList<Groupe> groupes,String filepath) throws IOException {
        // Ouverture du flux objet en sortie
        ObjectOutputStream sortie = new ObjectOutputStream(
                new FileOutputStream(filepath));
        // ecriture de l'objet dans le fichier
            sortie.writeObject(groupes);
            sortie.flush();
        // fermer flux
        sortie.close();
    }

    /**
     * Lit un objet serialisé dans un fichier 
     * @param filepath the value of filename
     * @return Le groupe lue
     * @throws IOException L'erreur a remonter
     * @throws ClassNotFoundException L'erreur a remonter
     * @throws FileNotFoundException L'erreur a remonter
     */
    public static ArrayList<Groupe> ReadTabGroupe(final String filepath) throws IOException,ClassNotFoundException,FileNotFoundException {
        ObjectInputStream entree = null;
        ArrayList<Groupe> retour = new ArrayList<Groupe>();
        try {
            //Pour lire le fichier objet :
            // Ouverture du flux objet en entree
            entree = new ObjectInputStream(
                    new FileInputStream(filepath));
            // Lecture de l'objet contenu dans le fichier
                retour=(ArrayList<Groupe>) entree.readObject();
        }catch (FileNotFoundException e) {
            // Exception declenchee si le fichier n'existe pas
            printError(e);
        } catch (EOFException e) {
            // Exception declenchee si la fin du fichier est atteinte
            System.out.println("Fin du fichier");
            System.out.println("Aucun Objet");
        } catch (IOException e) {
            // Exception declenchee si un autre probleme acces fichier
            printError(e);
        } catch (ClassNotFoundException e) {
            printError(e);
        } finally {
            try {
                entree.close();
            } catch (IOException e) {
                printError(e);
            } catch (Exception e) {
                printError(e);
            }
        }
        return retour;
    }

    /**
     * Methode qui permet l'importation d'un groupe a partir d'un fichier texte
     * @param filepath L'emplacement du fichier texte
     * @throws IOException L'erreur a remonter
     * @throws ClassNotFoundException L'erreur a remonter
     */
    public static void importeGroupes(String filepath) throws IOException, ClassNotFoundException {
            Etablissement.setTabGroupe(ReadTabGroupe(filepath));
    }
    
    /**
     * Methode qui permet l'importation d'un groupe a partir du fichier texte "initialisation.txt"
     * @throws IOException L'erreur a remonter
     * @throws ClassNotFoundException L'erreur a remonter
     */
    public static void initialiseGroupes() throws IOException, ClassNotFoundException {
            Etablissement.setTabGroupe(ReadTabGroupe("initialisation.txt"));
    }
    /**
     * Methode qui ecrit dans la console le message d'erreur
     * @param e L'exception intercepte
     */
    private static void printError(Exception e) {
        System.err.println(e.getCause());
    }
}
