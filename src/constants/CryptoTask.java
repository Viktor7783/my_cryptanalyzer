package constants;

import static constants.Constants.*;

public enum CryptoTask {
    ENCRYPT(ENCRYPT_SOURCE_MESSAGE, ENCRYPT_DESTINATION_MESSAGE, KEY_FOR_ENCRYPT_MESSAGE),
    DECRYPT(DECRYPT_SOURCE_MESSAGE, DECRYPT_DESTINATION_MESSAGE, KEY_FOR_DECRYPT_MESSAGE);

    private final String sourceMessage;
    private final String destinationMessage;
    private final String keyMessage;

    public String getSourceMessage() {
        return sourceMessage;
    }

    public String getDestinationMessage() {
        return destinationMessage;
    }

    public String getKeyMessage() {
        return keyMessage;
    }

    CryptoTask(String sourceMessage, String destinationMessage, String keyMessage) {
        this.sourceMessage = sourceMessage;
        this.destinationMessage = destinationMessage;
        this.keyMessage = keyMessage;
    }

}
