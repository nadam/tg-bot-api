package se.anyro.tgbotapi;

import java.io.IOException;

import se.anyro.tgbotapi.types.ResponseParameters;

@SuppressWarnings("serial")
public class HttpResponseException extends IOException {

    private int errorCode;

    private ResponseParameters parameters;

    public HttpResponseException(int errorCode, String description, ResponseParameters parameters) {
        super(description);
        this.errorCode = errorCode;
        this.parameters = parameters;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public ResponseParameters getParameters() {
        return parameters;
    }
}
