package face;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;


public class container {
    private int nowScore;
    private static int size = 5;
    private block[][] blocks = new block[5][5];
    private ArrayList<int[]> NullIndex = new ArrayList<>();
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

        //初始化四个值
        //向左移动
        /*
        //向上
        if(input == 87){
            for(int i=0;i<size;i++){
                int empty = 0;
                for(int j=0;j<size;j++) {
                    if (!filter(i, j)) {
                        empty = 1;
                        continue;
                    }
                    if(blocks[i][j].getStu()){
                        int id = blocks[i][empty].getId();
                        blocks[i][empty].setId(blocks[i][j].getId());
                        blocks[i][j].setId(id);
                        empty++;
                    }
                }
            }
            System.out.println("下移完成");
        }

        //向下
        if(input == 83){
            for(int i=size-1;i>=0;i--){
                int empty = 4;
                for(int j=size-1;j>=0;j--) {
                    if (!filter(i, j)) {
                        empty = 3;
                        continue;
                    }
                    if(blocks[i][j].getStu()){
                        int id = blocks[i][empty].getId();
                        blocks[i][empty].setId(blocks[i][j].getId());
                        blocks[i][j].setId(id);
                        empty--;
                    }
                }
            }
            System.out.println("上移完成");
        }


        //向右
        if(input == 68){
            for(int i=size-1;i>=0;i--){
                int empty = 4;
                for(int j=size-1;j>=0;j--) {
                    if (!filter(i, j)) {
                        empty = 3;
                        continue;
                    }
                    if(blocks[j][i].getStu()){
                        int id = blocks[empty][i].getId();
                        blocks[empty][i].setId(blocks[j][i].getId());
                        blocks[j][i].setId(id);
                        empty--;
                    }
                }
            }
            System.out.println("右移完成");
        }

        //向左
        if(input == 65) {
            for (int i = 0; i <size; i++) {
                int empty = 0;
                for (int j = 0; j <size; j++) {
                    if (!filter(i, j)) {
                        empty = 1;
                        continue;
                    }
                    if (blocks[j][i].getStu()) {
                        int id = blocks[empty][i].getId();
                        blocks[empty][i].setId(blocks[j][i].getId());
                        blocks[j][i].setId(id);
                        empty++;
                    }
                }
            }
            System.out.println("左移完成");
        }*/


        else if (input == 81 || input == 88) {
            int[][] upright = new int[][]{{1,0},{1,1},{2,0},{3,0},{0,3},{0,1},{0,2}};
            int[][] downleft = new int[][]{{4,1},{4,2},{4,3},{1,4},{2,4},{3,4},{3,3}};
            int[][] upleft = new int[][]{{1,0},{1,1},{2,0},{3,0},{0,3},{0,1},{0,2}};
            int[][] downright = new int[][]{{1,0},{1,1},{2,0},{3,0},{0,3},{0,1},{0,2}};

            int[] uprightdir = new int[]{1,1};
            int[] downleftdir = new int[]{-1,-1};
            int[] upleftdir = new int[]{1,-1};
            int[] downrightdir = new int[]{1,1};

            int [][] startdir;
            int[] dir;

            if(input == 81){
                startdir = upright;
                dir = uprightdir;
                System.out.println("向左上方移动方块");
            }
            else{
                startdir = downleft;
                dir = downleftdir;
                System.out.println("向右下方移动方块");
            }

            for(int i=0;i<startdir.length;i++){
                int x = startdir[i][0];
                int y = startdir[i][1];
                int[] empty = new int[]{x, y};
                while(isOutBound(x, y)){
                    if (blocks[x][y].getStu()) {
                        blocks[x][y].show();
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



        }/*
        //向左上角滑动
        if(input == 81){
            int[][] arr = new int[][]{{1,0},{1,1},{2,0},{3,0},{0,3},{0,1},{0,2}};
            for(int i=0;i<arr.length;i++){
                int x = arr[i][0];
                int y = arr[i][1];
                int[] empty = new int[]{x, y};
                while(isOutBound(x, y)){
                    if (blocks[x][y].getStu()) {
                        blocks[x][y].show();
                        int id = blocks[empty[0]][empty[1]].getId();
                        blocks[empty[0]][empty[1]].setId(blocks[x][y].getId());
                        blocks[x][y].setId(id);
                        empty[0]+=1;
                        empty[1]+=1;
                    }
                    x += 1;
                    y += 1;
                }
            }
            System.out.println("向左上方移动");
        }

        //向左下角滑动
        if(input == 88){
            int[][] arr = new int[][]{{4,1},{4,2},{4,3},{1,4},{2,4},{3,4},{3,3}};
            for(int i=0;i<arr.length;i++){
                int x = arr[i][0];
                int y = arr[i][1];
                int[] empty = new int[]{x, y};
                while(isOutBound(x, y)){
                    if (blocks[x][y].getStu()) {
                        blocks[x][y].show();
                        int id = blocks[empty[0]][empty[1]].getId();
                        blocks[empty[0]][empty[1]].setId(blocks[x][y].getId());
                        blocks[x][y].setId(id);
                        empty[0]-=1;
                        empty[1]-=1;
                    }
                    x -= 1;
                    y -= 1;
                }
            }
            System.out.println("向左下方移动");
        }*/


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
    public boolean merge(int input){
        boolean flag = false;
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
                        if (!filter(j-1, i)) {
                            blocks[index][i].setId(blocks[j][i].getId());
                            index--;
                        } else {
                            index = 3;
                        }
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

        //向左上角

        return flag;
    }


    //是否还能继续游戏
    public boolean isContinue(){
        if(Isfull() && merge(12))
            return false;
        else return true;
    }
    //返回容器是否全都被非0方块填满
    public boolean Isfull(){
        if(getNullIndex().isEmpty())
            return true;
        else
            return false;
    }

    //交换两个方块，用于方块滑动和合并
    //交换两个方块，但是不改变原来的x和y坐标
    public void Swapblock(block a, block b){
        block temp = a;
        a = b;
        b = temp;
        int a_x = a.getX_index();
        int a_y = a.getY_index();
        a.setX_index(b.getX_index());
        a.setY_index(b.getY_index());
        b.setX_index(a_x);
        b.setY_index(a_y);
    }

    //随机功能还未能实现
    //在所有空的位置里随机找到一个位置作为新出现的方格，最后将坐标返回
    public void RandomGenerate(){
        if(Isfull())
            return;
        int id = 2;
//        for (int i = 0; i < getNullIndex().size(); i++) {
//            System.out.println("x  " + getNullIndex().get(i)[0] + "  y   " + getNullIndex().get(i)[1]);
//        }
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
