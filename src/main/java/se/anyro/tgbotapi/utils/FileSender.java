package se.anyro.tgbotapi.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import se.anyro.tgbotapi.HttpResponseException;
import se.anyro.tgbotapi.types.ResponseParameters;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Simple multipart HTTP POST for sending a file and optionally parse the response.
 */
public class FileSender {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String BOUNDARY = "===y5twmnRq4X1rSQ8tJ8HfC3YxqkxcQd===";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data; boundary=" + BOUNDARY;
    private static final byte[] BOUNDARY_LINE = ("--" + BOUNDARY + "\r\n").getBytes();
    private static final byte[] BOUNDARY_END = ("--" + BOUNDARY + "--\r\n").getBytes();
    private static final byte[] CONTENT_DISPOSITION = "Content-Disposition: form-data; name=\"".getBytes();
    private static final byte[] FILENAME = "\"; filename=\"".getBytes();
    private static final byte[] CR_LF = "\r\n".getBytes();
    private static final byte[] QUOTE_CR_LF = "\"\r\n".getBytes();
    private static final byte[] CONTENT_TYPE_TEXT = "Content-Type: text/plain; charset=UTF-8\r\n".getBytes();
    private static final byte[] CONTENT_TYPE_FILE = "Content-Type: application/octet-stream\r\n".getBytes();
    private static final byte[] TRANSFER_ENCODING_BINARY = "Content-Transfer-Encoding: binary\r\n".getBytes();
    
    private static final Gson GSON = new Gson();
    private static final JsonParser PARSER = new JsonParser();

    private HttpURLConnection connection;
    private OutputStream out;

    public FileSender(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        connection = (HttpURLConnection) url.openConnection();
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setRequestProperty(CONTENT_TYPE, MULTIPART_FORM_DATA);
        connection.connect();
        out = new BufferedOutputStream(connection.getOutputStream());
    }

    public void addFormField(String name, String value) throws IOException {
        out.write(BOUNDARY_LINE);
        writeContentDisposition(name);
        out.write(CONTENT_TYPE_TEXT);
        out.write(CR_LF);
        out.write(value.getBytes(StandardCharsets.UTF_8));
        out.write(CR_LF);
    }

    public void addFormField(String name, long value) throws IOException {
        addFormField(name, String.valueOf(value));
    }

    private void writeContentDisposition(String key) throws IOException {
        out.write(CONTENT_DISPOSITION);
        out.write(key.getBytes(StandardCharsets.UTF_8));
        out.write(QUOTE_CR_LF);
    }

    private void writeContentDisposition(String key, String filename) throws IOException {
        out.write(CONTENT_DISPOSITION);
        out.write(key.getBytes(StandardCharsets.UTF_8));
        out.write(FILENAME);
        out.write(filename.getBytes(StandardCharsets.UTF_8));
        out.write(QUOTE_CR_LF);
    }

    public void addFilePart(String fieldName, File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        addFilePart(fieldName, inputStream, file.getName());
        inputStream.close();
    }

    public void addFilePart(String fieldName, InputStream inputStream, String fileName) throws IOException {
        out.write(BOUNDARY_LINE);
        writeContentDisposition(fieldName, fileName);
        out.write(CONTENT_TYPE_FILE);
        out.write(TRANSFER_ENCODING_BINARY);
        out.write(CR_LF);

        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        out.write(CR_LF);
    }

    /**
     * Write the last part and close the stream. Response data is ignored.
     */
    public int finish() throws IOException {
        out.write(BOUNDARY_END);
        out.close();
        int responseCode = connection.getResponseCode();
        closeInputStream(connection); // Close to let the connection be reused
        return responseCode;
    }

    /**
     * Write the last part and parse the response.
     */
    public <T> T finish(Class<T> responseClass) throws IOException {
        out.write(BOUNDARY_END);
        out.close();
        try (Reader reader = new InputStreamReader(connection.getInputStream())) {
            JsonObject response = (JsonObject) PARSER.parse(reader);
            if (response.getAsJsonPrimitive("ok").getAsBoolean()) {
                JsonElement result = response.get("result");
                return GSON.fromJson(result, responseClass);
            } else {
                int errorCode = response.getAsJsonPrimitive("error_code").getAsInt();
                String description = response.getAsJsonPrimitive("description").getAsString();
                JsonElement parameters = response.get("parameters");
                ResponseParameters responseParameters = GSON.fromJson(parameters, ResponseParameters.class);
                throw new HttpResponseException(errorCode, description, responseParameters);
            }
        }
    }
    
    private void closeInputStream(HttpURLConnection con) {
        try {
            con.getInputStream().close();
        } catch (IOException e) {
            // Ignore
        }
    }
}