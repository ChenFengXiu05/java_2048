package face;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;


public class container {
    private int nowScore;
    private static int size = 5;
    private block[][] blocks = new block[5][5];
    private ArrayList<int[]> NullIndex = new ArrayList<>();

    public static int[][] upright = new int[][]{{1,0},{1,1},{2,0},{3,0},{0,3},{0,1},{0,2}};
    public static int[][] downleft = new int[][]{{4,1},{4,2},{4,3},{1,4},{2,4},{3,4},{3,3}};
    public static int[][] downright = new int[][]{{1,3},{1,4},{2,4},{3,4},{0,3},{0,1},{0,2}};
    public static int[][] upleft = new int[][]{{1,0},{3,1},{2,0},{3,0},{4,3},{4,1},{4,2}};
    public static int[][] left = new int[][]{{3, 1}, {4, 1}, {4, 2}, {4, 3}, {3, 4}};
    public static int[][] right = new int[][]{{1, 0}, {0, 2}, {0, 1}, {0, 3}, {1, 4}};

    public static int[][] up = new int[][]{{0, 1}, {1, 0}, {2, 0}, {3, 0}, {4, 1}};

    public static int[][] down = new int[][]{{0, 3}, {1, 4}, {2, 4}, {3, 4}, {4, 3}};

    private static int[] Input = new int[]{81, 88, 69, 90, 68, 65, 87, 83};

    public static int[] uprightdir = new int[]{1,1};
    public static int[] downleftdir = new int[]{-1,-1};
    public static int[] upleftdir = new int[]{-1,1};
    public static int[] downrightdir = new int[]{1,-1};
    public static int[] leftdir = new int[]{-1, 0};
    public static int[] rightdir = new int[]{1, 0};
    public static int[] updir = new int[]{0, 1};
    public static int[] downdir = new int[]{0, -1};
    //    保存所有的没有位置的区域坐标-需要经常进行增删改

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

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        container.size = size;
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
                //初始将所有格子的坐标都添加到NullIndex中
                if(!blocks[i][j].getStu())
                    NullIndex.add(new int[]{i, j});
            }
        }
    }

    public boolean filter(int i, int j){
        if(i==0 && j==0 || i==4 && j==0 || i==0 && j==4 || i==4 && j==4)
            return false;
        else return true;
    }
    //容器内所有方格滑动
    public void slide(int input){

        //up/down/right/left/upRight/upLeft/downRight/downLeft

        //上下左右
        //empty- 0  4
        //step- 1  -1
        //while循环规定循环次数
        //写两个【】数组索引没办法，有两种。只能分成水平和垂直两种
        //start step
        int start;
        int step;
        if(input == 87 || input == 83){
            if(input == 87){
                start = 0;
                step = 1;
                System.out.println("向上移动方块");
            }
            else{
                start = 4;
                step = -1;
                System.out.println("向下移动方块");
            }
            int a=0;
            int i = start;
            while(a<size){
                int b=0;
                int j = start;
                int empty = start;
                while(b<size){
                    if(!filter(i, j)){
                        empty += step;
                        b++;
                        j += step;
                        continue;
                    }
                    if(blocks[i][j].getStu()) {
                        int id = blocks[i][empty].getId();
                        blocks[i][empty].setId(blocks[i][j].getId());
                        blocks[i][j].setId(id);
                        empty += step;
                    }
                    b++;
                    j += step;
                }
                i += step;
                a++;
            }
        }
        else if (input == 68 || input == 65){
            if(input == 65){
                start = 0;
                step = 1;
                System.out.println("向左移动方块");
            }
            else{
                start = 4;
                step = -1;
                System.out.println("向右移动方块");
            }
            int a=0;
            int i = start;
            while(a<size){
                int b=0;
                int j = start;
                int empty = start;
                while(b<size){
                    if(!filter(j, i)){
                        empty += step;
                        b++;
                        j += step;
                        continue;
                    }
                    if(blocks[j][i].getStu()) {
                        int id = blocks[empty][i].getId();
                        blocks[empty][i].setId(blocks[j][i].getId());
                        blocks[j][i].setId(id);
                        empty += step;
                    }
                    b++;
                    j += step;
                }
                i += step;
                a++;
            }
        }

        else if (input == 81 || input == 88 || input == 90 || input == 69) {
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
            }
            for(int i=0;i<startdir.length;i++){
                int x = startdir[i][0];
                int y = startdir[i][1];
                int[] empty = new int[]{x, y};
                while(isOutBound(x, y)){
                    if (blocks[x][y].getStu()) {
                        System.out.println(x+" "+ y);
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
    }

    public boolean isSlide(){
        int start;
        int step;
        int input;
        for(int num = 0;num<Input.length;num++){
            input = Input[num];
            if(input == 87 || input == 83){
                if(input == 87){
                    start = 0;
                    step = 1;
                }
                else{
                    start = 4;
                    step = -1;
                }
                int a=0;
                int i = start;
                while(a<size){
                    int b=0;
                    int j = start;
                    int empty = start;
                    while(b<size){
                        if(!filter(i, j)){
                            empty += step;
                            b++;
                            j += step;
                            continue;
                        }
                        if(blocks[i][j].getStu()) {
                            int id = blocks[i][empty].getId();
                            if(id != blocks[i][j].getId())
                                return true;
                            empty += step;
                        }
                        b++;
                        j += step;
                    }
                    i += step;
                    a++;
                }
            }
            else if (input == 68 || input == 65){
                if(input == 65){
                    start = 0;
                    step = 1;
                }
                else{
                    start = 4;
                    step = -1;
                }
                int a=0;
                int i = start;
                while(a<size){
                    int b=0;
                    int j = start;
                    int empty = start;
                    while(b<size){
                        if(!filter(j, i)){
                            empty += step;
                            b++;
                            j += step;
                            continue;
                        }
                        if(blocks[j][i].getStu()) {
                            int id = blocks[empty][i].getId();
                            if(id != blocks[j][i].getId())
                                return true;
                            empty += step;
                        }
                        b++;
                        j += step;
                    }
                    i += step;
                    a++;
                }
            }

            else if (input == 81 || input == 88 || input == 90 || input == 69) {
                int [][] startdir = {{}};
                int[] dir = {};
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
                }
                for(int i=0;i<startdir.length;i++){
                    int x = startdir[i][0];
                    int y = startdir[i][1];
                    int[] empty = new int[]{x, y};
                    while(isOutBound(x, y)){
                        if (blocks[x][y].getStu()) {
                            int id = blocks[empty[0]][empty[1]].getId();
                            if(id != blocks[x][y].getId())
                                return true;
                            empty[0]+=dir[0];
                            empty[1]+=dir[1];
                        }
                        x += dir[0];
                        y += dir[1];
                    }
                }
            }
        }
        return false;
    }
    public boolean isOutBound(int i, int j){
        if(!filter(i, j) || i<0 || j<0 || j>size-1 || i>size-1)
            return false;
        else return true;
    }
    public void show_blocks(){
        System.out.println("---------------------");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(!filter(i, j))
                    continue;
                System.out.print(blocks[i][j].getId() + " ");
                System.out.print(blocks[i][j].getX_index() + " " + blocks[i][j].getY_index() + "    ");
            }
            System.out.println();
        }
        System.out.println("_________________");
    }
    //将容器内滑动后方格进行合并，达到消除的效果
    //合并后产生的数字如果大于score，则更新score
    public void merge(int input){
        boolean flag = false;
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
            System.out.println("向左合并");
        } else if (input == 68) {
            System.out.println("向右合并");
        } else if (input == 87) {
            System.out.println("向上合并");
        } else if (input == 83) {
            System.out.println("向下合并");
        }
        for (int i = 0; i < startdir.length; i++) {
            int x = startdir[i][0];
            int y = startdir[i][1];
            int[] empty = new int[]{x, y};
            System.out.println("empty[0]:" + empty[0] + " empty[1]:" + empty[1]);
            while (isOutBound(x, y) && isOutBound(x + dir[0], y + dir[1])) {
                //相邻的方块相等，需要合并，合并之后跨两个格子
                if (blocks[x][y].getStu() && blocks[x + dir[0]][y + dir[1]].getId() == blocks[x][y].getId()) {
                    int id = blocks[x][y].getId() * 2;
                    flag = true;
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
                    System.out.println(blocks[x][y].getId());
                    int id = blocks[x][y].getId();
                    blocks[x][y].setId(0);
                    blocks[empty[0]][empty[1]].setId(id);
                    empty[0] += dir[0];
                    empty[1] += dir[1];
                }
                //状态为false，说明方块之后的都没有数字了，直接跳出循环
                else break;
                //每次循环到下一个对应坐标位置
                x += dir[0];
                y += dir[1];
            }
        }
    }





        /*
        //左上角合并
        if(input == 81){
            int[][] startdir = new int[][]{{1,0},{1,1},{2,0},{3,0},{0,3},{0,1},{0,2}};
            int[] dir = new int[]{1,1};
            for(int i=0;i<startdir.length;i++){
                int x = startdir[i][0];
                int y = startdir[i][1];
                int[] empty = new int[]{x, y};
                System.out.println("---------合并区间-----------------");
                System.out.println("empty[0]: " + empty[0] + " empty[1]: " + empty[1]);
                while(isOutBound(x, y) && isOutBound(x+dir[0], y+dir[1])){
                    //相邻的方块相等，需要合并，合并之后跨两个格子
                    if (blocks[x][y].getStu() && blocks[x+dir[0]][y+dir[1]].getId() == blocks[x][y].getId()) {
                        System.out.println("合并区间内" + x+" "+ y);
                        int id = blocks[x][y].getId()*2;
                        //找到对应的先设为零
                        blocks[x][y].setId(0);
                        blocks[x+dir[0]][y+dir[1]].setId(0);
                        //将合适地方ID设置为对应的
                        blocks[empty[0]][empty[1]].setId(id);
                        //empty相应变成下一个
                        empty[0]+=dir[0];
                        empty[1]+=dir[1];
                        x+=dir[0];
                        y+=dir[1];
                    //相邻的不相等，不需要合并，正常流程跨一个格子就够了
                    } else if (blocks[x][y].getStu()) {
                        System.out.println(blocks[x][y].getId());
                        int id = blocks[x][y].getId();
                        blocks[x][y].setId(0);
                        blocks[empty[0]][empty[1]].setId(id);
                        empty[0]+=dir[0];
                        empty[1]+=dir[1];
                    }
                    //状态为false，说明方块之后的都没有数字了，直接跳出循环
                    else break;
                    //每次循环到下一个对应坐标位置
                    x += dir[0];
                    y += dir[1];
                }
            }
        }

        //向下
        if(input == 83){
            for(int i=size-1;i>=0;i--){
                int index = size-1;
                for(int j=size-1;j>0;j--) {
                    if (!filter(i, j) || !filter(i, j-1)) {
                        if(!filter(i, j-1)){
                            blocks[i][index].setId(blocks[i][j].getId());
                            index--;
                        }
                        else{
                            index = 3;
                        }
                        continue;
                    }
                    if(blocks[i][j].getStu() && blocks[i][j].getId()==blocks[i][j-1].getId()){
                        flag = true;
                        blocks[i][index].setId(blocks[i][j-1].getId()*2);
                        index--;
                        j--;
                    }
                    else if(blocks[i][j].getStu()){
                        blocks[i][index].setId(blocks[i][j].getId());
                        index--;
                    }
                }
                for(int j=index;j>=0;j--){
                    if (!filter(i, j))
                        continue;
                    blocks[i][j].setId(0);
                }
            }
            System.out.println("向下合并完成");
        }
        //向上
        if(input == 87){
            for(int i=0;i<size;i++){
                int index = 0;
                for(int j=0;j<size-1;j++) {
                    if (!filter(i, j) || !filter(i, j + 1)) {
                        if (!filter(i, j + 1)) {
                            blocks[i][index].setId(blocks[i][j].getId());
                            index++;
                        } else {
                            index = 1;
                        }
                        continue;
                    }
                    if (blocks[i][j].getStu() && blocks[i][j].getId() == blocks[i][j + 1].getId()) {
                        flag = true;
                        blocks[i][index].setId(blocks[i][j + 1].getId() * 2);
                        index++;
                        j++;
                    } else if (blocks[i][j].getStu()) {
                        blocks[i][index].setId(blocks[i][j].getId());
                        index++;
                    }
                }
                for(int j=index;j<size;j++){
                    if (!filter(i, j))
                        continue;
                    blocks[i][j].setId(0);
                }
            }
            System.out.println("向上合并完成");
        }

        //向右
        if(input == 68){
            for(int i=size-1;i>=0;i--){
                int index = 4;
                for(int j=size-1;j>0;j--) {
                    if (!filter(j, i) || !filter(j-1, i)) {
//                        if (!filter(j-1, i)) {
//                            blocks[index][i].setId(blocks[j][i].getId());
//                            index--;
//                            System.out.println("sd");
//                        } else {
                        index = 3;
//                        }
                        continue;
                    }
                    if (blocks[j][i].getStu() && blocks[j][i].getId() == blocks[j - 1][i].getId()) {
                        flag = true;
                        blocks[index][i].setId(blocks[j-1][i].getId() * 2);
                        index--;
                        j--;
                    } else if (blocks[j][i].getStu()) {
                        blocks[index][i].setId(blocks[j][i].getId());
                        index--;
                    }
                }
                for(int j=index;j>=0;j--){
                    if (!filter(i, j))
                        continue;
                    blocks[j][i].setId(0);
                }
            }
            System.out.println("向右合并完成");
        }

        //向左
        if(input == 65){
            for(int i=0;i<size;i++){
                int index = 0;
                for(int j=0;j<size-1;j++) {
                    if (!filter(j, i) || !filter(j+1, i)) {
                        if (!filter(j+1, i)) {
                            blocks[index][i].setId(blocks[j][i].getId());
                            index++;
                        } else {
                            index = 1;
                        }
                        continue;
                    }
                    if (blocks[j][i].getStu() && blocks[j][i].getId() == blocks[j + 1][i].getId()) {
                        flag = true;
                        blocks[index][i].setId(blocks[j+1][i].getId() * 2);
                        index++;
                        j++;
                    } else if (blocks[j][i].getStu()) {
                        blocks[index][i].setId(blocks[j][i].getId());
                        index++;
                    }
                }
                for(int j=index;j<size-1;j++){
                    if (!filter(i, j))
                        continue;
                    blocks[j][i].setId(0);
                }
            }
            System.out.println("向左合并完成");
        }
*/
    }


    public boolean isMerge(){
        int input;
        int[][] startdir = {{}};
        int[] dir = {};
        for(int num = 0;num<Input.length;num++) {
            input = Input[num];
            if (input == 81 || input == 88 || input == 69 || input == 90) {
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
                }
                for (int i = 0; i < startdir.length; i++) {
                    int x = startdir[i][0];
                    int y = startdir[i][1];
                    int[] empty = new int[]{x, y};
                    while (isOutBound(x, y) && isOutBound(x + dir[0], y + dir[1])) {
                        //相邻的方块相等，需要合并，合并之后跨两个格子
                        if (blocks[x][y].getStu() && blocks[x + dir[0]][y + dir[1]].getId() == blocks[x][y].getId()) {
                            int id = blocks[x][y].getId() * 2;
                            return true;
                        } else if (blocks[x][y].getStu()) {
                            int id = blocks[x][y].getId();
                            empty[0] += dir[0];
                            empty[1] += dir[1];
                        }
                        //状态为false，说明方块之后的都没有数字了，直接跳出循环
                        else break;
                        //每次循环到下一个对应坐标位置
                        x += dir[0];
                        y += dir[1];
                    }
                }
            }

            //向下
            if (input == 83) {
                for (int i = size - 1; i >= 0; i--) {
                    int index = size - 1;
                    for (int j = size - 1; j > 0; j--) {
                        if (!filter(i, j) || !filter(i, j - 1)) {
                            if (!filter(i, j - 1)) {
                                index--;
                            } else {
                                index = 3;
                            }
                            continue;
                        }
                        if (blocks[i][j].getStu() && blocks[i][j].getId() == blocks[i][j - 1].getId()) {
                            return true;
                        } else if (blocks[i][j].getStu()) {
                            index--;
                        }
                    }
                    for (int j = index; j >= 0; j--) {
                        if (!filter(i, j))
                            continue;
                    }
                }
            }
            //向上
            if (input == 87) {
                for (int i = 0; i < size; i++) {
                    int index = 0;
                    for (int j = 0; j < size - 1; j++) {
                        if (!filter(i, j) || !filter(i, j + 1)) {
                            if (!filter(i, j + 1)) {
                                index++;
                            } else {
                                index = 1;
                            }
                            continue;
                        }
                        if (blocks[i][j].getStu() && blocks[i][j].getId() == blocks[i][j + 1].getId()) {
                            return true;
                        } else if (blocks[i][j].getStu()) {
                            index++;
                        }
                    }
                    for (int j = index; j < size; j++) {
                        if (!filter(i, j))
                            continue;
                    }
                }
            }

            //向右
            if (input == 68) {
                for (int i = size - 1; i >= 0; i--) {
                    int index = 4;
                    for (int j = size - 1; j > 0; j--) {
                        if (!filter(j, i) || !filter(j - 1, i)) {
                            if (!filter(j - 1, i)) {
                                index--;
                            } else {
                                index = 3;
                            }
                            continue;
                        }
                        if (blocks[j][i].getStu() && blocks[j][i].getId() == blocks[j - 1][i].getId()) {
                            return true;
                        } else if (blocks[j][i].getStu()) {
                            index--;
                        }
                    }
                    for (int j = index; j >= 0; j--) {
                        if (!filter(i, j))
                            continue;
                    }
                }
            }

            //向左
            if (input == 65) {
                for (int i = 0; i < size; i++) {
                    int index = 0;
                    for (int j = 0; j < size - 1; j++) {
                        if (!filter(j, i) || !filter(j + 1, i)) {
                            if (!filter(j + 1, i)) {
                                index++;
                            } else {
                                index = 1;
                            }
                            continue;
                        }
                        if (blocks[j][i].getStu() && blocks[j][i].getId() == blocks[j + 1][i].getId()) {
                            return true;
                        } else if (blocks[j][i].getStu()) {
                            index++;
                        }
                    }
                    for (int j = index; j < size - 1; j++) {
                        if (!filter(i, j))
                            continue;
                    }
                }
            }
        }
        return false;
    }

    //是否还能继续游戏
    public boolean isContinue(){
        if(!Isfull())
            return true;
        else return false;
    }

    //返回容器是否全都被非0方块填满
    public boolean Isfull(){
        if(NullIndex.isEmpty())
            return true;
        else
            return false;
    }

    //随机功能还未能实现
    //在所有空的位置里随机找到一个位置作为新出现的方格，最后将坐标返回
    public void RandomGenerate(){
        if(Isfull())
            return;
        int id = 2;
        System.out.println(getNullIndex().size());
        int length = NullIndex.size();
        Random r = new Random();
        int random = r.nextInt(0, length);
        int new_x = NullIndex.get(random)[0];
        int new_y = NullIndex.get(random)[1];
        blocks[new_x][new_y].setId(id);
        NullIndex.remove(random);
        System.out.println("生成了一个随机方块");
    }

}
