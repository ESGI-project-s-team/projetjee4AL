package fr.esgi.rent.api.error;

import fr.esgi.rent.exception.NotFoundRentalCarException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorHandlerTest {
    @Test
    void shouldHandleNotFoundRentalCarException() {
        String message = "Message";
        ErrorHandler errorHandler = new ErrorHandler();

        ErrorDto errorDto = errorHandler.handleNotFoundRentalCarException(new NotFoundRentalCarException(message));

        assertThat(errorDto.message()).isEqualTo(message);
    }

    @Test
    void shouldHandleMethodArgumentNotValidException() {
        String message = "La requête envoyée est invalide";
        ErrorHandler errorHandler = new ErrorHandler();

        ErrorDto errorDto = errorHandler.handleMethodArgumentNotValidException();

        assertThat(errorDto.message()).isEqualTo(message);
    }
}
