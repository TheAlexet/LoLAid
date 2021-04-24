package business_logic.data_models;

public class GameCustomizationObject
{
    private String category;
    private String content;

    public GameCustomizationObject(){}

    public GameCustomizationObject(String category, String content) {
        this.category = category;
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
