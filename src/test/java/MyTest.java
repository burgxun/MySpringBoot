import java.util.concurrent.*;

/**
 * @ClassName MyTest
 * @Auther burgxun
 * @Description:
 * @Date 2020/07/09 10:38
 **/
public class MyTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName() + "---burgxun";
            }
        };

        FutureTask<String> futureTask = new FutureTask(callable);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS, new LinkedBlockingQueue());
        threadPoolExecutor.submit(futureTask);

        String msg = futureTask.get();
        System.out.println("Now main thread name:" + Thread.currentThread().getName());
        System.out.println(msg);

        threadPoolExecutor.shutdown();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        FutureTask<String> stringFutureTask = new FutureTask<>(() -> Thread.currentThread().getName() + "---burgxun");
        executorService.submit(stringFutureTask);
        String msg2 = stringFutureTask.get();
        System.out.println(msg2);
        executorService.shutdown();

        // %s  %d  %f


    }
}

class MyThread extends Thread {

    public MyThread() {
        this.setName("MyThread");
    }

    @Override
    public void run() {
        System.out.printf("【%s】 is runing", Thread.currentThread().getName());
        super.run();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       /* MyThread myThread=new MyThread();
        myThread.run();*/

      /* Runnable runnable=new Runnable() {
           @Override
           public void run() {
               System.out.printf("【%s】 is runing", Thread.currentThread().getName());
           }
       };

       Thread runnableThread=new Thread(runnable);
        runnableThread.start();*/


        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName() + "---burgxun";
            }
        };

        FutureTask<String> futureTask = new FutureTask(callable);
        Thread callableThread = new Thread(futureTask);
        callableThread.start();

        String msg = futureTask.get();
        System.out.println(msg);
    }
}
