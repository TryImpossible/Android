package com.barry.basic.other;

public class DataStructures {

    public static void main(String[] args) {
        Integer[] srcArray = new Integer[]{ 1,3,5,7,9,11,13,15,17,19,21,23,25,27 };
        int index = Search.binarySearch(srcArray, 3);
    }

    private static class Search {
        public static int binarySearch(Integer[] src, int des) {
            int x = 0;
            int startIndex = 0;
            int endIndex = src.length - 1;
            while (startIndex <= endIndex) {
                System.out.print("第" + x + "次比较\n");
                x++;

                int middleIndex = (startIndex + endIndex) / 2;
//                System.out.println("startIndex:" + startIndex);
//                System.out.println("endIndex:" + endIndex);
//                System.out.println("middleIndex:" + middleIndex);
                if (des == src[middleIndex]) {
                    System.out.println(des + "在第" + middleIndex + "个位置");
                    return middleIndex;
                } else if (des > src[middleIndex]) {
                    System.out.println(src[middleIndex] + "比" + des + "小，继续查找");
                    startIndex = middleIndex + 1;
                } else {
                    System.out.println(src[middleIndex] + "比" + des + "大，继续查找");
                    endIndex = middleIndex - 1;
                }
            }
            return  -1;
        }
    }
}
