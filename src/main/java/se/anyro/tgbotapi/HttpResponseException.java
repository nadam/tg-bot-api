package se.anyro.tgbotapi;

import java.io.IOException;

@SuppressWarnings("serial")
public class HttpResponseException extends IOException {

    private int errorCode;

    public HttpResponseException(int errorCode, String description) {
        super(description);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
