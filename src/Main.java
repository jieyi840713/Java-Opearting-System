import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {
    public static class RecursiveDemo extends RecursiveTask<Integer> {
        private static final int MAX = 70;
        private int[] arr;
        private int start;
        private int end;

        public RecursiveDemo(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }


        @Override
        protected Integer compute() {
            int sum = 0;
            if ((end - start) < MAX) {
                for (int i = start; i < end; i++) {
                    sum += arr[i];
                }
                return sum;
            } else {
                int middle = (start + end) / 2;
                RecursiveDemo left = new RecursiveDemo(arr, start, middle);
                RecursiveDemo right = new RecursiveDemo(arr, middle, end);
                invokeAll(left, right);
                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[100000000];
        Random random = new Random();

        long startTime = System.currentTimeMillis();
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            int temp = random.nextInt();
            arr[i] = temp;
            total += arr[i];
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Total is " + total);
        System.out.println("Sequential addition takes " + (endTime - startTime) + " milliseconds.");

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        startTime = System.currentTimeMillis();
        int result = forkJoinPool.invoke(new RecursiveDemo(arr, 0, arr.length));
        endTime = System.currentTimeMillis();
        System.out.println("Total is " + result);
        System.out.println("Parallel addition takes " + (endTime - startTime) + " milliseconds.");
        forkJoinPool.shutdown();
    }
}