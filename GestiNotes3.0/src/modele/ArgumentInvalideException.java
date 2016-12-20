package modele;

/**
 *
 * @author Raphael Duchaine
 */
public class ArgumentInvalideException extends IllegalArgumentException {
    
    /**
     * Constructeur sans parametres qui permet de creer une erreur avec le message "L'argument est invalide"
     */
    public ArgumentInvalideException() {
        super("L\'argument est invalide");
    }

    /**
     * Constructeur qui permet de creer une erreur avec le message fourni en parametre
     * @param message Le message a ecrire
     */
    public ArgumentInvalideException(String message) {
        super(message);
    }
    
}
