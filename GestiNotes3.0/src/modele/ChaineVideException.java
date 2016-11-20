package modele;

/**
 *
 * @author Raphael Duchaine
 */
class ArgumentInvalideException extends IllegalArgumentException {
    
    public ArgumentInvalideException() {
        super("L\'argument est invalide");
    }
    public ArgumentInvalideException(String message) {
        super(message);
    }
    
}
