package Game;

public class block {
    private int id;     //方块的数字，零表示没有数字
    private int x_index;    //图片所处位置的x坐标
    private int y_index;    //图片所处位置的y坐标
    private static String basePath = "images/num_blocks/";     //所有图片的基础地址
    private String ImagePath;   //图片的路径
    private boolean stu;    //表示方块的状态，true表示非零元素，false表示零元素

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
        if(id==0)
            this.stu = false;
        else this.stu = true;    //默认新生成的都是非零的，所以为true
    }
    public boolean getStu() {
        return stu;
    }

    public void setStu(boolean stu) {
        this.stu = stu;
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
        this.ImagePath = basePath + id + ".png";
        if(id==0)
            this.stu = false;
        else
            this.stu = true;
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
