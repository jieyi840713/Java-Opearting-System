class MergeSort{

    public static void mergeSort(int[] list){
        if(list.length >1){
            // merge sort the first half
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf,0,list.length/2);
            mergeSort(firstHalf);

            // merge sort the second half
            int secondHalfLength = list.length - list.length /2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length/2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            // merge
            merge(firstHalf,secondHalf, list);
        }
    }
    public static void merge (int[] list1, int[] list2, int[] temp){
        int i = 0 ;
        int j = 0;
        int k = 0;

        while (i< list1.length && j < list2.length){
            if(list1[i] < list2[j]){
                temp[k] = list1[i];
                i++;
            }else{
                temp[k] = list2[j];
                j++;
            }
            k++;
        }

        while (i< list1.length){
            temp[k] = list1[i];
            k++;
            i++;
        }

        while (j< list2.length){
            temp[k] = list2[j];
            k++;
            j++;
        }
    }

    public static void main(String[] args) {
        int[] list= {2,4,6,7,1,0,-3,-55,3214,4364,2,3,6,878,5,4};
        mergeSort(list);
        for(int n : list){
            System.out.print(n + " ");
        }
    }
}

public class Main {

}