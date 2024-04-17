package documentStorage;

public class DocumentStorage {
    private double maxVolumeOfData;
    private int countOfStorageDocuments;
    private float currentVolumeOfData;

    public DocumentStorage(float maxVolumeOfData, int countOfStorageDocuments, float currentVolumeOfData) {
        this.maxVolumeOfData = maxVolumeOfData;
        this.countOfStorageDocuments = countOfStorageDocuments;
        this.currentVolumeOfData = currentVolumeOfData;
    }

    public double getMaxVolumeOfData() {
        return maxVolumeOfData;
    }

    public int getCountOfStorageDocuments() {
        return countOfStorageDocuments;
    }

    public float getCurrentVolumeOfData() {
        return currentVolumeOfData;
    }

    public void storeDocument(Document document) {
        if (maxVolumeOfData - currentVolumeOfData >= document.getFileSize()) {
            this.countOfStorageDocuments++;
            this.currentVolumeOfData += document.getFileSize();
            System.out.println(document + " is stored in the storage!");
        } else {
            System.out.println("Not enough space!");
        }
    }

    @Override
    public String toString() {
        return "DocumentStorage{" +
                "maxVolumeOfData=" + maxVolumeOfData +
                ", countOfStorageDocuments=" + countOfStorageDocuments +
                ", currentVolumeOfData=" + currentVolumeOfData +
                '}';
    }
}
