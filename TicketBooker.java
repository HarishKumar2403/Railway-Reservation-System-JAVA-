import java.util.*;
class TicketBooker{
    static int availableLowerBerth=1;
    static int availableMiddleBerth=1;
    static int availableUpperBerth=1;
    static int availableRAC=1;
    static int availableWaitingList=1;


    static Queue<Integer> RACList = new LinkedList<>();
    static Queue<Integer> WaitingList = new LinkedList<>();
    static ArrayList<Integer> BookedTicketList = new ArrayList<>();


    static List<Integer> lowerBerthPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> middleBerthPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> upperBerthPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> racPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1));


    static HashMap<Integer,Passenger> passenger = new HashMap<>();

    public void bookTicket(Passenger P,int seat,String alloted){
        P.seatnumber=seat;
        P.seatalloted=alloted;
        BookedTicketList.add(P.passengerId);
        passenger.put(P.passengerId,P);
        System.out.println("----------[Booked Successfully------------]");
    }

    public void addedToRAC(Passenger P,int seat,String alloted){
        P.seatnumber=seat;
        P.seatalloted=alloted;
        RACList.add(P.passengerId);
        passenger.put(P.passengerId,P);
        System.out.println("----------[RAC Ticket Booked Successfully------------]");
    }


    public void addedToWaitingList(Passenger P,int seat,String alloted){
        P.seatnumber=seat;
        P.seatalloted=alloted;
        WaitingList.add(P.passengerId);
        passenger.put(P.passengerId,P);
        System.out.println("----------[Waiting List Booked Successfully------------]");
    }

    public void cancelTicket(int id){
        Passenger cancelPassenger = passenger.get(id);
        int cancelPassengerPosition = cancelPassenger.seatnumber;
        BookedTicketList.remove(Integer.valueOf(id));
        passenger.remove(Integer.valueOf(id));
        System.out.println("-------------[Canceled Successfully]-----------------");
        System.out.println("");
        if(cancelPassenger.seatalloted.equals("L")){
            availableLowerBerth++;
            lowerBerthPositions.add(cancelPassengerPosition);
        }
        else if(cancelPassenger.seatalloted.equals("M")){
            availableMiddleBerth++;
            middleBerthPositions.add(cancelPassengerPosition);

        }
        else if(cancelPassenger.seatalloted.equals("U")){
            availableUpperBerth++;
            upperBerthPositions.add(cancelPassengerPosition);
        }

        if(RACList.size()>0){
            Passenger racPassenger = passenger.get(RACList.poll());
            int racseat = racPassenger.seatnumber;
            RACList.remove(Integer.valueOf(racPassenger.passengerId));
            racPositions.add(racseat);
            availableRAC++;

            if(WaitingList.size()>0){
                Passenger waitingListPassenger = passenger.get(WaitingList.poll());
                int waitingListSeat = waitingListPassenger.seatnumber;
                WaitingList.remove(Integer.valueOf(waitingListPassenger.passengerId));
                waitingListPositions.add(waitingListSeat);


                waitingListPassenger.seatnumber=racPositions.get(0);
                waitingListPassenger.seatalloted="RAC";
                RACList.add(waitingListPassenger.passengerId);
                racPositions.remove(0);
                availableRAC--;
                availableWaitingList++;
            }

            Main.bookTicket(racPassenger);
        }
    }

    public void printAvailableTicket(){
        System.out.println("-------------------------------");
        System.out.println("Available Lower Berth: "+availableLowerBerth);
        System.out.println("Available Middle Berth: "+availableMiddleBerth);
        System.out.println("Available Upper Berth: "+availableUpperBerth);
        System.out.println("Available RAC Tickets: "+availableRAC);
        System.out.println("Available Waiting List Tickets: "+availableWaitingList);
        System.out.println("-------------------------------");
    }

    public void printBookedTicket(){
        if(passenger.size()==0){
        System.out.println("No Tickets were booked");
        return;
        }
        for(Passenger P:passenger.values()){
        System.out.println("-------------------------------");
        System.out.println("Passenger Id: "+P.passengerId);
        System.out.println("Passenger Name: "+P.name);
        System.out.println("Passenger Age: "+P.age);
        System.out.println("Passenger Seat Number: "+P.seatnumber+P.seatalloted);
        System.out.println("-------------------------------");
        }
    }
}