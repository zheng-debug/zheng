package com.example.demo;

/*快速排序*/
public class QuickSort {
    public static void main(String[] args){
        int arr[] = {5,9,6,7,4,3};
        quickSort(arr,0,arr.length-1);
        for (int i:arr) {
            System.out.println(arr[i]+",");
        }

    }
    public static void quickSort(int[] arr, int left, int right){
        int index = getIndex(arr,left,right);
        quickSort(arr,0,index-1);
        quickSort(arr,index+1,right);
    }
    public static int getIndex(int[] arr, int left, int right){
        int tmp = arr[left];
        while(left != right){
            if (left<right && arr[right]>=tmp){
                right--;
            }else if(left<right && arr[right]<tmp){
                arr[left] = arr[right];
            }else if(left<right && arr[left]<tmp){
                left++;
            }else if(left<right && arr[left]>=tmp){
                arr[right] = tmp;
            }
        }
        return left;
    }

}
