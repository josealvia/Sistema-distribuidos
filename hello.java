  
import java.util.concurrent.BrokenBarrierException;  
import java.util.concurrent.CyclicBarrier;  
  
public class hello {  
    // Una clase auxiliar de sincronización que permite a un grupo de subprocesos esperar el uno al otro hasta que alcanza un punto de barrera común (punto de barrera común)  
    final CyclicBarrier barrier;  
      
    // Número de hilos  
    int count;  
      
    class Worker implements Runnable{  
        int index;  
        Worker(int index){  
            this.index = index;  
        }  
        public void run() {  
            System.out.println("Sección" + index + "Los hilos duermen "+(2 * index)+"¡Segundo!");  
            try {  
                Thread.sleep(2000 * index);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            System.out.println("Sección" + index + "¡Un hilo termina durmiendo!");  
              
            try {  
                // Espere hasta que los otros hilos hayan terminado de procesarse, luego continúe la ejecución del siguiente código  
                barrier.await();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            } catch (BrokenBarrierException e) {  
                e.printStackTrace();  
            }  
              
            System.out.println(index);  
        }  
    }  
      
    public hello(int count){  
        this.count = count;  
          
        // Punto de barrera pública Después de esperar 5 hilos, ejecute la barreraAcción correspondiente  
        barrier = new CyclicBarrier(count, new Runnable() {  
            public void run(){  
                System.out.println("¡Todos los hilos se han ejecutado!");  
            }  
        });  
          
        for(int i=1;i<=this.count;i++){  
            new Thread(new Worker(i)).start();  
        }  
    }  
      
    public static void main(String[] args) {  
        new hello(5);  
    }  
} 