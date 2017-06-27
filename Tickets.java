import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Tickets {
	public static void main(String[] args) throws InterruptedException {
		BusNo buscom = new BusNo();
		Scanner sc=new Scanner(System.in);
		String namefirst=sc.nextLine();
		String namesecond=sc.nextLine();
		String namethird=sc.nextLine();
		
		Passenger p1 = new Passenger(buscom, namefirst);
		Passenger p2 = new Passenger(buscom, namesecond);
		Passenger p3 = new Passenger(buscom, namethird);

		p1.start();
		p2.start();
		p3.start();
	}
}
class BusNo {
	private final boolean[] seats = new boolean[3];
	private int nextSeat = 0;

	public void bookSeat() throws Exception {
		if (nextSeat < seats.length) {
			seats[nextSeat] = true;
			nextSeat++;
			System.out.print("Seat number " + nextSeat + " booked");
		} 
	}
}

class Passenger extends Thread {

	BusNo bus;
	String passengerName;

	public Passenger(BusNo bus, String passengerName) {
		this.bus = bus;
		this.passengerName = passengerName;
	}

	public void run() {
		synchronized (bus) {
			try {
				bus.bookSeat();
				Thread.sleep(500);
			} catch (Exception ex) {
				Logger.getLogger(Passenger.class.getName()).log(Level.SEVERE, null, ex);
			}
			System.out.println("by " + passengerName);
      }
	}
	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
}

