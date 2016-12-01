package net.pixonics.trial.scheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Date;
import net.pixonics.trial.Scheduler;
import net.pixonics.trial.TaskLoader;


public class SchedulerTest {


        /*
            Compare schedule console and executeConsole for test.
        */
	@Test
	public void schedulerCoordination() {
                
                String passed = "";


           Scheduler s = new Scheduler ();
           
           
           Date  d1 = new Date ();
           System.out.println (d1);
           TaskLoader t1 = new TaskLoader ();
           t1.setStartTime(d1);
           t1.setScheduler(s);
           t1.setDelays(new int[] {10, 12, 5, 23});
           t1.setAlias("TEST1");

           TaskLoader t2 = new TaskLoader ();
           t2.setStartTime(d1);
           t2.setScheduler(s);
           t2.setDelays(new int[] {4, 8, 5, 12});
           t2.setAlias("TEST2");

           TaskLoader t3 = new TaskLoader ();
           t3.setStartTime(d1);
           t3.setScheduler(s);
           t3.setDelays(new int[] {2, 29, 15, 23});
           t3.setAlias("TEST3");
        
           TaskLoader t4 = new TaskLoader ();
           t4.setStartTime(d1);
           t4.setScheduler(s);
           t4.setDelays(new int[] {15, 28});
           t4.setAlias("TEST4");

           s.start();

           t1.start();
           t2.start();
           t3.start();

           try {
               
           t1.join();
           t2.join();
           t3.join();

           Thread.currentThread().sleep(10000);   
           t4.start();
           t4.join();
           s.join(30000);
        
           s.interrupt();
   
           } catch (InterruptedException ee) {
             ee.printStackTrace();  
           }
           
           System.out.println (s.printExecuteConsole());
           System.out.println (s.printScheduleConsole());

        

           assertEquals(s.printExecuteConsole(), s.printScheduleConsole());

	}


}
