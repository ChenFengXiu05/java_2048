package face;

public class block {
    private int id;
    private int x_index;
    private int y_index;
    private static String basePath = "D:/JAVA_codes/java_2048/images/";
    private String ImagePath;

    public block() {
    }

    public block(int id) {
        this.id = id;
        this.ImagePath = basePath + id + ".png";
    }

    public block(int id, int x_index, int y_index) {
        this.id = id;
        this.x_index = x_index;
        this.y_index = y_index;
        this.ImagePath = basePath + id + ".png";
    }

    public int getX_index() {
        return x_index;
    }

    public void setX_index(int x_index) {
        this.x_index = x_index;
    }

    public int getY_index() {
        return y_index;
    }

    public void setY_index(int y_index) {
        this.y_index = y_index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getBasePath() {
        return basePath;
    }

    public static void setBasePath(String basePath) {
        block.basePath = basePath;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
}
