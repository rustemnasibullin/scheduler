package net.pixonics.trial;

import java.util.Date;
import java.util.concurrent.Callable;

public class TaskLoader extends Thread {
    
    Date startTime = null;
    int[] delays = null;
    Scheduler scheduler = null;
    String alias = "Test";
    
    
    
    public TaskLoader() {
        super();
    }


    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setDelays(int[] delays) {
        this.delays = delays;
    }

    public int[] getDelays() {
        return delays;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void run() {
        
        
        
        for (int ti: delays) {
            
             long ts = startTime.getTime()+ti*1000;
             Date t = new Date();
             t.setTime(ts); 
             Callable c = new CTest ();
             scheduler.schedule(t, c);
            
        }
        
        
    }
    
    
    class CTest implements Callable  {
        
          
        public Object call () {
            
            
               System.out.println ((new Date())+" : "+alias);
               return null;
            
        }
        
        public String toString () {
               return alias;
        }

        
        
    }
    


}
