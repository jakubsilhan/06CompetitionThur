
package silhan;

public class Date {
    int hour;
    int min;
    int seconds;

    public Date(int hour, int min, int seconds) {
        this.hour = hour;
        this.min = min;
        this.seconds = seconds;
    }
    
    public Date(String time){ //09:12:00
        String[] parts = time.split("[:]");
        this.hour = Integer.parseInt(parts[0]);
        this.min = Integer.parseInt(parts[1]);
        this.seconds = Integer.parseInt(parts[2]);
    }
    
    public Date(int time){
        this.hour = time/3600;
        time = time%3600;
        this.min = time/60;
        this.seconds = time%60;
    }
    
    public int getTime(){
        return this.hour*3600 + this.min*60 + this.seconds;
    }
    
    public String getTimeString(){
        return String.format("%02d:%02d:%02d", this.hour, this.min, this.seconds);
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getSeconds() {
        return seconds;
    }
    
    public int timeCompare(Date time){
        return time.getTime() - this.getTime();
    }
    
    public Date timeCompareDate(Date time){
        return new Date(time.getTime() - this.getTime());
    }
    
}
