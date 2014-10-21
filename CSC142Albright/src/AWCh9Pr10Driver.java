/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 10 - Ship, Cruise Ship & Cargo Ship
 * HW Due 4/17/14
 */
public class AWCh9Pr10Driver {
	
	public static void main(String[] args) {
		AWShip ship1 = new AWShip("HMS Bounty", "1900");
		ship1.setYearBuilt("1784");
		AWCruiseShip ship2 = new AWCruiseShip("Queen Elizabeth II", "1965", 1777);
		ship2.setMaxPassengers(1892);
		AWCargoShip ship3 = new AWCargoShip("Exxon Valdez", "1985", 209836);
		ship3.setMaxCap(200000);
		AWShip ship4 = new AWShip("RMS Titanic", "1908");
		ship4.setYearBuilt("1909");
		AWCruiseShip ship5 = new AWCruiseShip("Costa Concordia", "2005", 3780);
		ship5.setMaxPassengers(3779);
		AWCargoShip ship6 = new AWCargoShip("MS UND Adriyatik", "2001", 22900);
		ship6.setMaxCap(20000);
		
		AWShip[] ships = {ship1, ship2, ship3, ship4, ship5, ship6};
		for(AWShip ship: ships){
			System.out.println(ship);
		}

	}

}
