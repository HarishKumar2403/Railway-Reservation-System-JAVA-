class Passenger{
    static int id=1;
    String name;
    int age;
    String berthpreference;
    int seatnumber;
    String seatalloted;
    int passengerId;
    Passenger(String name,int age,String berthpreference){
        this.name=name;
        this.age=age;
        this.berthpreference=berthpreference;
        this.passengerId=id++;
        seatnumber=-1;
        seatalloted="";
    }
}