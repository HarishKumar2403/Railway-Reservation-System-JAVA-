import java.util.*;
class Main{
    public static void bookTicket(Passenger P){
        TicketBooker booker  = new TicketBooker();
        if(TicketBooker.availableWaitingList==0){
            System.out.println("No Tickets Available");
            return;
        }
        else{
            if((P.berthpreference.equals("L") && TicketBooker.availableLowerBerth>0)||
               (P.berthpreference.equals("M") && TicketBooker.availableMiddleBerth>0)||
               (P.berthpreference.equals("U") && TicketBooker.availableUpperBerth>0)){

                System.out.println("Preference Berth Available");

                if(P.berthpreference.equals("L")){
                    System.out.println("Lower Berth Given");
                    booker.bookTicket(P,(TicketBooker.lowerBerthPositions.get(0)),"L");
                    TicketBooker.availableLowerBerth--;
                    TicketBooker.lowerBerthPositions.remove(0);
                }
                else if(P.berthpreference.equals("M")){
                    System.out.println("Middle Berth Given");
                    booker.bookTicket(P,(TicketBooker.middleBerthPositions.get(0)),"M");
                    TicketBooker.availableMiddleBerth--;
                    TicketBooker.middleBerthPositions.remove(0);
                }

                else if(P.berthpreference.equals("U")){
                    System.out.println("Upper Berth Given");
                    booker.bookTicket(P,(TicketBooker.upperBerthPositions.get(0)),"U");
                    TicketBooker.availableUpperBerth--;
                    TicketBooker.upperBerthPositions.remove(0);
                }
            }
            else if(TicketBooker.availableLowerBerth>0){
                System.out.println("Lower Berth Given");
                booker.bookTicket(P,(TicketBooker.lowerBerthPositions.get(0)),"L");
                TicketBooker.availableLowerBerth--;
                TicketBooker.lowerBerthPositions.remove(0);
            }
            else if(TicketBooker.availableMiddleBerth>0){
                System.out.println("Middle Berth Given");
                booker.bookTicket(P,(TicketBooker.middleBerthPositions.get(0)),"M");
                TicketBooker.availableMiddleBerth--;
                TicketBooker.middleBerthPositions.remove(0);
            }
            else if(TicketBooker.availableUpperBerth>0){
                System.out.println("Upper Berth Given");
                booker.bookTicket(P,(TicketBooker.upperBerthPositions.get(0)),"U");
                TicketBooker.availableUpperBerth--;
                TicketBooker.upperBerthPositions.remove(0);
            }

            else if(TicketBooker.availableRAC>0){
                System.out.println("Added To RAC List");
                booker.addedToRAC(P,(TicketBooker.racPositions.get(0)),"RAC");
                TicketBooker.availableRAC--;
                TicketBooker.racPositions.remove(0);
            }

            else if(TicketBooker.availableWaitingList>0){
                System.out.println("Added To Waiting List");
                booker.addedToWaitingList(P,(TicketBooker.waitingListPositions.get(0)),"WL");
                TicketBooker.availableWaitingList--;
                TicketBooker.waitingListPositions.remove(0);
            }
        }
    }

    public static void cancelTicket(int id){
        
        if(!TicketBooker.passenger.containsKey(id)){
            System.out.println("Passenger Does Not Exits ");
            return;
        }
        else{
            TicketBooker booker = new TicketBooker();
            booker.cancelTicket(id);
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println(" 1.Book Ticket \n 2.Cancel Ticket \n 3.Available Tickets \n 4.Booked Tickets \n 5.Exit");
            int choices = sc.nextInt();
            switch(choices){
                case 1:{
                    System.out.println("Enter Passenger Name: ");
                    String name = sc.next();
                    System.out.println("Enter Passenger Age: ");
                    int age = sc.nextInt();
                    System.out.println("Enter Berth Preference [U,M,L]");
                    String berthpreference = sc.next();
                    
                    Passenger P = new Passenger(name,age,berthpreference);
                    bookTicket(P); 
                }
                break;
                case 2:{
                    System.out.println("Enter Passenger Id To Cancel Ticket");
                    int id = sc.nextInt();
                    cancelTicket(id);
                }
                break;
                case 3:{
                    TicketBooker booker = new TicketBooker();
                    booker.printAvailableTicket();
                }
                break;
                case 4:{
                    TicketBooker booker = new TicketBooker();
                    booker.printBookedTicket();
                }
                break;
                case 5:{
                    loop =false;
                }
                break;
                default:
                break;
            }
        }
    }
}