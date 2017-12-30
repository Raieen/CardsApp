package xyz.raieen.cardsapp;


public class Card {

    private String id;
    private String ownerId;
    private String displayName;
    private String imageFront;
    private String imageBack;
    private String description;
    private long created;
    private long lastModified;

    public Card() {
    }

    public Card(String id, String ownerId, String displayName, String imageFront, String imageBack, String description, long created, long lastModified) {
        this.id = id;
        this.ownerId = ownerId;
        this.displayName = displayName;
        this.imageFront = imageFront;
        this.imageBack = imageBack;
        this.description = description;
        this.created = created;
        this.lastModified = lastModified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImageFront() {
        return imageFront;
    }

    public void setImageFront(String imageFront) {
        this.imageFront = imageFront;
    }

    public String getImageBack() {
        return imageBack;
    }

    public void setImageBack(String imageBack) {
        this.imageBack = imageBack;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }
}
