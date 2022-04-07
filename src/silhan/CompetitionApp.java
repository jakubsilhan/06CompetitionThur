
package silhan;


public class CompetitionApp {

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public void addCompetitors(int number){
        String name; String surname; int year; char gender; String club;
        int prevComp = nCompetitors;
        for(int i =prevComp;i<number;i++){
            if (i==competitors.length){
                extendArray();
            }
            System.out.println("Jmeno:");
            name = sc.next();
            System.out.println("Prijmeni");
            surname = sc.next();
            System.out.println("Rocnik");
            year = sc.nextInt();
            System.out.println("Pohlavi");
            gender = sc.next().charAt(0);
            System.out.println("Klub");
            club = sc.next();
            this.competitors[i]= Zavodnik.getInstance(name, surname, year, gender, club);
            nCompetitors++;
        }
    }
    
}
