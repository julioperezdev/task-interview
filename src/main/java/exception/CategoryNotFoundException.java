package exception;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException() {
        super("La categoría no existe");
    }
}
