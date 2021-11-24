package reversepolishnotation.validator;


/**
 * Class that represents ExpressionValidator response
 */
public class ValidatorResponse {
    /** Code of the response */
   private StatusCode statusCode;
    /** Response message */
   private String message;

    public ValidatorResponse() {
    }

    /**
     * Creates response with specified code and message
     * @param statusCode code of the response
     * @param message response message
     */
    public ValidatorResponse(StatusCode statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * Gets code of the response
     * @return code of the response
     */
    public StatusCode getStatusCode() {
        return statusCode;
    }

    /**
     * Gets response message
     * @return response message
     */
    public String getMessage() {
        return message;
    }


    /**
     * Returns the response with specified status code and message
     * @param statusCode code of the response
     * @param message response message
     * @return a response representation of the message
     */
    public static ValidatorResponse of(StatusCode statusCode, String message){
        return new ValidatorResponse(statusCode, message);
    }

    /** Enumeration of response codes*/
    public enum StatusCode{
        OKAY,
        ERROR
    }
}
