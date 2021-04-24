package business_logic.data_models;

public class Observer
{
    private String encryptionKey;

    public Observer(){}

    public Observer(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }
}
