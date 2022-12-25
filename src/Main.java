// lunch threads (Class implement Runnable interface)

class PrintNumber implements Runnable {
    private int max;

    public PrintNumber (int max){
        this.max=max;
    }

    @Override
    public void run() {
        for(int i = 0 ; i< max ;i++){
            System.out.print(i + " ");
        }
    }
}

class PrintChar implements Runnable {
    private int times;
    private char aChar;

    public PrintChar (char aChar, int times){
        this.aChar = aChar;
        this.times = times;
    }

    @Override
    public void run() {
        for(int i = 0; i < times ; i++){
            System.out.print(aChar + " ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new PrintNumber(50));
        Thread thread2 = new Thread(new PrintNumber(50));
        Thread thread3 = new Thread(new PrintChar('a',50));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}