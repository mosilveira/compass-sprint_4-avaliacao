package uol.compass.avaliacao.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id) {
        super(String.format("Resource with id %s does not exist!", id));
    }
}
