
package silhan;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Competitor implements Comparable<Competitor>{
    private String name;
    private String surname;
    private int year; //rocnik
    private char gender;
    private int regNum;
    private Date startTime; //pocet sekund
    private LocalTime finishTime; //pocet sekund
    private int totalTime;
    private static int counter=0;
    //private String competitorCondition; //pouzit enum, neni potreba mit v promenne jen vracet metodou
    private String club;
    
    public Competitor(String name, String surname, int year, char gender, int regNum){
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.gender = gender;
        //this.counter+=1;
        this.regNum=regNum;
        this.startTime= new Date(0);
        this.totalTime=0;
    }
    
    //kopie zavodnika | metoda clone()
    public Competitor(Competitor c){
        this.name = c.name;
        this.surname = c.surname;
        this.year = c.year;
        this.gender = c.gender;
        this.club = c.club;
        this.regNum = c.regNum;
        this.startTime=c.startTime;
        this.finishTime=c.finishTime;
        this.totalTime=c.getTotalTime();
    }
    
    //factory method
    public static Competitor getInstance(String name, String surname, int year, char gender, int regNum){
        return new Competitor(name,surname,year,gender,regNum);
    }
    //settery
    public void setStartTime(int startTime) { //9*3600 + 12*60
        this.startTime = new Date(startTime);
    }
    public void setStartTime(int hour, int min, int sec){ //9 12 0
        this.startTime = new Date(hour,min,sec);
    }
    public void setStartTime(String time){ //09:12:00
        this.startTime = new Date(time);
    }
    
    public void setFinishTime(int finishTime) {
        //if(this.startTime == 0){
        if(this.startTime == null){
            throw new StartTimeNotSet("Nebyl nastaven cas startu, nelze nastavit cas v cili");
        }
        this.finishTime = LocalTime.ofSecondOfDay(finishTime);
        setTotalTime();
    }
    public void setFinishTime(int hour, int min, int sec){
        if(this.startTime == null){
            throw new StartTimeNotSet("Nebyl nastaven cas startu, nelze nastavit cas v cili");
        }
        this.finishTime = LocalTime.of(hour, min, sec);
        setTotalTime();
    }
    public void setFinishTime(String time){
        if(this.startTime == null){
            throw new StartTimeNotSet("Nebyl nastaven cas startu, nelze nastavit cas v cili");
        }
        this.finishTime = LocalTime.ofSecondOfDay(TimeTools.timeToSeconds(time));
        setTotalTime();
    }
    
    public void setTotalTime(){ //cas chceme pocitat jen pokud zavodnik dokoncil
        if(getCompetitorCondition() == CompetitorCondition.FINISHED){
            this.totalTime = TimeTools.timeCompare(startTime.getTime(), finishTime.getSecond());
        }
    }
    
    //gettery
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getYear() {
        return year;
    }

    public char getGender() {
        return gender;
    }

    public int getRegNum() {
        return regNum;
    }

    public int getStartTime() {
        return startTime.getTime();
    }

    public int getFinishTime() {
        return finishTime.getSecond();
    }

    public int getTotalTime() {
        return totalTime;
    }

    public static int getCounter() {
        return counter;
    }
    
    public CompetitorCondition getCompetitorCondition() {
        if(this.startTime==null){
            return CompetitorCondition.NOTSTARTED;
        } else if(this.startTime!=null && this.finishTime==null){
            return CompetitorCondition.STARTED;
        } else {
            return CompetitorCondition.FINISHED;
        }
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = checkClub(club);
    }
    
    private String checkClub(String club){ //klub musi zacinat velkým písmene a 2-6 znaku So - Sooooo
        if(!club.matches("^[A-Z][a-z]]{1,5}$")){ //regularni vyrazy
            throw new IllegalArgumentException("Nevalidni nazev klubu. Validni zacina velkym pismenem a ma jedna az 5 dalsich malych pismen");
        }
        return club;
    }    
    
    public int getAge(){
        LocalDate current_date = LocalDate.now(); //dnesni datum
        int year = current_date.getYear();
        return year - this.year;
    }
    
    //toString
    @Override
    public String toString() {
        return String.format("%5d %10s %10s %5d %1s %10s %10s %10s",
                this.regNum,this.name, this.surname, this.getAge(), this.gender,
                startTime.getTimeString(), finishTime.format(DateTimeFormatter.ISO_DATE), TimeTools.secondsToTime(this.getTotalTime()));
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try{
        Competitor c = new Competitor("Jakub", "Silhan",sc.nextInt(),'M', 1);
        //System.out.println(c);
        c.setStartTime(9,0,0);
        //System.out.println(c);
        //try{
            c.setFinishTime("10:02:05");
            System.out.println(c);
        }catch(StartTimeNotSet e){
            //System.out.println(c);
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println("Chyba");
        }
    }


    @Override
    public int compareTo(Competitor o) {
        /*
        if(this.getTime() < o.getTime(){
            return -1;
        }else if(this.getTime() > o.getTime()){
            return 1;
        }else{
            return 0;
        }
        */
        return this.getTotalTime() - o.getTotalTime();
    }
    
    
}
