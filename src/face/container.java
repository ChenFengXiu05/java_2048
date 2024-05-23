package face;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;


public class container {
    private int nowScore;
    private static int size = 5;
    //二维滑块数组
    private block[][] blocks = new block[size][size];
    //空余位置的坐标数组
    private ArrayList<int[]> NullIndex = new ArrayList<>();

    //方向初始坐标数组
    public static int[][] upright = new int[][]{{1,0},{1,1},{2,0},{3,0},{0,3},{0,1},{0,2}};
    public static int[][] downleft = new int[][]{{4,1},{4,2},{4,3},{1,4},{2,4},{3,4},{3,3}};
    public static int[][] downright = new int[][]{{1,3},{1,4},{2,4},{3,4},{0,3},{0,1},{0,2}};
    public static int[][] upleft = new int[][]{{1,0},{3,1},{2,0},{3,0},{4,3},{4,1},{4,2}};
    public static int[][] rigth = new int[][]{{3, 0}, {4, 1}, {4, 2}, {4, 3}, {3, 4}};
    public static int[][] left = new int[][]{{1, 0}, {0, 2}, {0, 1}, {0, 3}, {1, 4}};
    public static int[][] up = new int[][]{{0, 1}, {1, 0}, {2, 0}, {3, 0}, {4, 1}};
    public static int[][] down = new int[][]{{0, 3}, {1, 4}, {2, 4}, {3, 4}, {4, 3}};

    //输入键code编码
    //Q:左上-81  E:右下-69   Z:左下-90   X:右下-88   W:上-87   S:下-83   A:左-65   D:右-68
    private static int[] Input = new int[]{81, 88, 69, 90, 68, 65, 87, 83};

    //方向数组
    public static int[] uprightdir = new int[]{1,1};
    public static int[] downleftdir = new int[]{-1,-1};
    public static int[] upleftdir = new int[]{-1,1};
    public static int[] downrightdir = new int[]{1,-1};
    public static int[] rightdir = new int[]{-1, 0};
    public static int[] leftdir = new int[]{1, 0};
    public static int[] updir = new int[]{0, 1};
    public static int[] downdir = new int[]{0, -1};

    public void setNullIndex(ArrayList<int[]> nullIndex) {
        NullIndex = nullIndex;
    }
    public ArrayList<int[]> getNullIndex() {
        return NullIndex;
    }


    public int getNowScore() {
        return nowScore;
    }

    public void setNowScore(int nowScore) {
        this.nowScore = nowScore;
    }

    public block[][] getBlocks() {
        return blocks;
    }

    public void setBlocks(block[][] blocks) {
        this.blocks = blocks;
    }

    //初始化容器
    public container() {
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(!filter(i, j))
                    continue;
                //初始将所有格子的坐标都添加到NullIndex中
                NullIndex.add(new int[]{i, j});
                //初始化所有的方块
                blocks[i][j] = new block(0, i, j);
            }
        }
    }

    public void initNullIndex(){
        NullIndex.clear();
        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {
                if (!filter(i, j))
                    continue;
                //将所有空闲格子的坐标都添加到NullIndex中
                if(!blocks[i][j].getStu())
                    NullIndex.add(new int[]{i, j});
            }
        }
    }

    //非法坐标
    public boolean filter(int i, int j){
        if(i==0 && j==0 || i==4 && j==0 || i==0 && j==4 || i==4 && j==4)
            return false;
        else return true;
    }
    //容器内所有方格滑动方法
    public void slide(int input){
        int [][] startdir = {{}};
        int[] dir = {};
        if(input == 81){
            startdir = upright;
            dir = uprightdir;
            System.out.println("向左上方移动方块");
        } else if (input == 88){
            startdir = downleft;
            dir = downleftdir;
            System.out.println("向右下方移动方块");
        } else if (input == 90) {
            startdir = downright;
            dir = downrightdir;
            System.out.println("向左下方移动方块");
        } else if (input == 69) {
            startdir = upleft;
            dir = upleftdir;
            System.out.println("向右上角移动方块");
        } else if (input == 87) {
            startdir = up;
            dir = updir;
            System.out.println("向上移动方块");
        } else if (input == 83) {
            startdir = down;
            dir = downdir;
            System.out.println("向下移动方块");
        } else if (input == 65) {
            startdir = left;
            dir = leftdir;
            System.out.println("向左移动方块");
        } else if (input == 68) {
            startdir = rigth;
            dir = rightdir;
            System.out.println("向右移动方块");
        }
        for(int i=0;i<startdir.length;i++){
            int x = startdir[i][0];
            int y = startdir[i][1];
            int[] empty = new int[]{x, y};
            while(isOutBound(x, y)){
                if (blocks[x][y].getStu()) {
                    int id = blocks[empty[0]][empty[1]].getId();
                    blocks[empty[0]][empty[1]].setId(blocks[x][y].getId());
                    blocks[x][y].setId(id);
                    empty[0]+=dir[0];
                    empty[1]+=dir[1];
                }
                x += dir[0];
                y += dir[1];
            }
        }
    }

    //判断是否能够继续滑动方法
    public boolean isSlide(int stu){
        int [][] startdir = {{}};
        int[] dir = {};
        int input;
        int[] INPUT;
        if(stu!=0){
            INPUT = new int[]{stu};
        }else INPUT = Input;
        for(int num=0;num<INPUT.length;num++){
            input = INPUT[num];
            if(input == 81){
                startdir = upright;
                dir = uprightdir;
            } else if (input == 88){
                startdir = downleft;
                dir = downleftdir;
            } else if (input == 90) {
                startdir = downright;
                dir = downrightdir;
            } else if (input == 69) {
                startdir = upleft;
                dir = upleftdir;
            } else if (input == 87) {
                startdir = up;
                dir = updir;
            } else if (input == 83) {
                startdir = down;
                dir = downdir;
            } else if (input == 65) {
                startdir = left;
                dir = leftdir;
            } else if (input == 68) {
                startdir = rigth;
                dir = rightdir;
            }
            for(int i=0;i<startdir.length;i++){
                int x = startdir[i][0];
                int y = startdir[i][1];
                int[] empty = new int[]{x, y};
                while(isOutBound(x, y)){
                    if (blocks[x][y].getStu()) {
                        int id = blocks[empty[0]][empty[1]].getId();
                        if(id != blocks[x][y].getId()) {
                            return true;
                        }
                        empty[0]+=dir[0];
                        empty[1]+=dir[1];
                    }
                    x += dir[0];
                    y += dir[1];
                }
            }
        }
        return false;
    }

    //判断坐标元素是否越界
    public boolean isOutBound(int i, int j){
        if(!filter(i, j) || i<0 || j<0 || j>size-1 || i>size-1)
            return false;
        else return true;
    }

    //将容器内滑动后方格进行合并，达到消除的效果
    //合并后产生的数字如果大于score，则更新score
    public void merge(int input){
        int[][] startdir = {{}};
        int[] dir = {};

        if (input == 81) {
            startdir = upright;
            dir = uprightdir;
            System.out.println("左上角方向合并");
        } else if (input == 88) {
            startdir = downleft;
            dir = downleftdir;
            System.out.println("向右下方合并");
        } else if (input == 90) {
            startdir = downright;
            dir = downrightdir;
            System.out.println("向左下方移动合并");
        } else if (input == 69) {
            startdir = upleft;
            dir = upleftdir;
            System.out.println("向右上角合并");
        } else if (input == 65) {
            startdir = left;
            dir = leftdir;
            System.out.println("向左合并");
        } else if (input == 68) {
            startdir = rigth;
            dir = rightdir;
            System.out.println("向右合并");
        } else if (input == 87) {
            startdir = up;
            dir = updir;
            System.out.println("向上合并");
        } else if (input == 83) {
            startdir = down;
            dir = downdir;
            System.out.println("向下合并");
        }
        for (int i = 0; i < startdir.length; i++) {
            int x = startdir[i][0];
            int y = startdir[i][1];
            int[] empty = new int[]{x, y};
            while (isOutBound(x, y) && isOutBound(x + dir[0], y + dir[1])) {
                //相邻的方块相等，需要合并，合并之后跨两个格子
                if (blocks[x][y].getStu() && blocks[x + dir[0]][y + dir[1]].getId() == blocks[x][y].getId()) {
                    int id = blocks[x][y].getId() * 2;
                    //更新分数
                    if(id>nowScore) nowScore = id;
                    //找到对应的先设为零
                    blocks[x][y].setId(0);
                    blocks[x + dir[0]][y + dir[1]].setId(0);
                    //将合适地方ID设置为对应的
                    blocks[empty[0]][empty[1]].setId(id);

                    //empty相应变成下一个
                    empty[0] += dir[0];
                    empty[1] += dir[1];
                    x += dir[0];
                    y += dir[1];
                    //相邻的不相等，不需要合并，正常流程跨一个格子就够了
                } else if (blocks[x][y].getStu()) {
                    int id = blocks[x][y].getId();
                    blocks[x][y].setId(0);
                    blocks[empty[0]][empty[1]].setId(id);
                    empty[0] += dir[0];
                    empty[1] += dir[1];
                }
                //状态为false，说明方块之后的都没有数字了，直接跳出循环
                //每次循环到下一个对应坐标位置
                x += dir[0];
                y += dir[1];
            }
            //特殊情况合并后的直接越界了，导致后面的方块没能补上来
            if(isOutBound(x, y) && blocks[x][y].getStu()){
                int id = blocks[x][y].getId();
                blocks[x][y].setId(0);
                blocks[empty[0]][empty[1]].setId(id);
            }
        }
    }

    //判断是否能够继续合并方法
    public boolean isMerge(int stu){
        int input;
        int[][] startdir = {{}};
        int[] dir = {};
        int[] INPUT;
        if(stu!=0){
            INPUT = new int[]{stu};
        }else INPUT = Input;
        for(int num = 0;num<INPUT.length;num++) {
            input = INPUT[num];
            if (input == 81) {
                startdir = upright;
                dir = uprightdir;
            } else if (input == 88) {
                startdir = downleft;
                dir = downleftdir;
            } else if (input == 90) {
                startdir = downright;
                dir = downrightdir;
            } else if (input == 69) {
                startdir = upleft;
                dir = upleftdir;
            } else if (input == 65) {
                startdir = left;
                dir = leftdir;
            } else if (input == 68) {
                startdir = rigth;
                dir = rightdir;
            } else if (input == 87) {
                startdir = up;
                dir = updir;
            } else if (input == 83) {
                startdir = down;
                dir = downdir;
            }
            for (int i = 0; i < startdir.length; i++) {
                int x = startdir[i][0];
                int y = startdir[i][1];
                int[] empty = new int[]{x, y};
                while (isOutBound(x, y) && isOutBound(x + dir[0], y + dir[1])) {
                    //相邻的方块相等，需要合并，合并之后跨两个格子
                    if (blocks[x][y].getStu() && blocks[x + dir[0]][y + dir[1]].getId() == blocks[x][y].getId()) {
                        //如果找到了能够合并的方格则返回true
                        return true;
                    } else if (blocks[x][y].getStu()) {
                        int id = blocks[x][y].getId();
                        empty[0] += dir[0];
                        empty[1] += dir[1];
                    }
                    //状态为false，说明方块之后的都没有数字了，直接跳出循环
                    //每次循环到下一个对应坐标位置
                    x += dir[0];
                    y += dir[1];
                }
            }
        }
        //没有return则代表不能合并，返回false
        return false;
    }

    //是否还能继续游戏
    public boolean isContinue(){
        if(Isfull() && isMerge(0))
            return false;
        else return true;
    }

    //返回容器是否全都被非0方块填满
    //全满返回true
    public boolean Isfull(){
        if(NullIndex.isEmpty())
            return true;
        else
            return false;
    }

    //在所有空的位置里随机找到一个位置作为新出现的方格
    //随机生成的新方格具体号码随机
    public void RandomGenerate(){
        if(Isfull())
            return;
        Random r = new Random();
        int num;
        int id = 2;
        if(nowScore<=8)
            id = 2;
        else {
            num = r.nextInt(0, 3);
            while(num>0){
                id *= 2;
                num--;
            }
        }
        int length = NullIndex.size();
        int random = r.nextInt(0, length);
        int new_x = NullIndex.get(random)[0];
        int new_y = NullIndex.get(random)[1];
        blocks[new_x][new_y].setId(id);
        //将新添加的位置从数组中移除
        NullIndex.remove(random);
        System.out.println("生成了一个新方块");
    }
}
