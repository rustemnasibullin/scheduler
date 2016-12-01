package  net.pixonics.trial;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * Task Scheduller
 *
 * @author rustamnasibullin@gmail.com
 */
public class Scheduler extends Thread {


       AtomicInteger order = new AtomicInteger();
       public TreeSet console1 = new TreeSet();
       public LinkedHashSet console2 = new LinkedHashSet ();
       PriorityBlockingQueue<JOB> queue = new PriorityBlockingQueue<JOB> ();
       ExecutorService srv = Executors.newCachedThreadPool();
 

       public void run(){
           
           
           for (;;) {
               
               JOB job =  null;
               
               try {
                   
                 job =  queue.take();
                 long td  = Math.max (0L,job.t.getTime() - System.currentTimeMillis());
                 sleep(td);
                 srv.submit(job.c);   
                 console2.add(job);
               
               } catch (InterruptedException ee) {

                 if (job != null) {
                     queue.put(job);
                 }

               }

           }
           
           
       }



       public String printExecuteConsole() {
              String s = "";
              for (Object o: console2) {
                   s+=o+":";
              }
              return s;           
       };
       
       public String printScheduleConsole() {
              String s = "";
              for (Object o: console2) {
                   s+=o+":";
              }
              return s;           
       };



       public synchronized void schedule (Date t, Callable c) {
           
              JOB j = new JOB (t, c);
              console1.add(j);
              
              if (this.isAlive()) {
                  this.interrupt();
              }
              
              queue.put(j);
        
           
       }
       
       class JOB implements Comparable {
           
           
             Date t;
             Callable c;
             int order0 = 0;


        public JOB(Date t, Callable c) {
            super();
            this.t = t;
            this.c = c;
            order0 = order.incrementAndGet();
        }


        @Override
        public int compareTo(Object o) {
            // TODO Implement this method
            if (o instanceof JOB) {
                JOB j = (JOB) o;
                if (j.t.getTime()>t.getTime())return -1; 
                else if (j.t.getTime()==t.getTime()) {
                
                    if (j.order0>order0) {
                        return -1;
                    } else {
                        return 1;
                    }
                
                }
                else return 1; 
            }
            return 1;
        }

        public String toString () {
               return c.toString()+"/"+order0;
        }

    }


    public static void main(String[] a) {

           Scheduler s = new Scheduler ();
           try {
           s.start();
           s.join();
           } catch (Throwable ee) {
           ee.printStackTrace(); 
           }

    }
 

}