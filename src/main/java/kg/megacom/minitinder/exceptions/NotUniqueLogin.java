package kg.megacom.minitinder.exceptions;

public class NotUniqueLogin extends RuntimeException {
    public NotUniqueLogin(String message) {
        super(message);
    }
}
