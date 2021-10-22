public class ReservationAppTwo {
	/*
	private static ArrayList<TableTwo> listOfTables = new ArrayList<>();
	private static HashMap<Customer, ArrayList<Reservation>> reservationMap = new HashMap<>();

	public static boolean addReservation(Reservation r) {
		if (r.getDate().isBefore(LocalDate.now()) || (r.getDate().isEqual(LocalDate.now()) && r.getTime().isBefore(LocalTime.now()))){
			System.out.println("Reservation time selected is before current time");
			return false;
		}
		LocalDate bookingDate = r.getDate();
		LocalTime bookingTime = r.getTime();
		int bookingHour = bookingTime.getHour();

		//find a suitable table
		for (TableTwo t : listOfTables) {
			if (t.getCapacity() <= r.getNoOfPax()) {
				//if there is currently a reservation on that date
				if (t.getReservations().containsKey(bookingDate)) {
					//if no reservation on that hour
					if (!t.getReservations().get(bookingDate)[bookingHour]) {
						r.setTableNo(t.getTableID());
						t.getReservations().get(bookingDate)[bookingHour] = true;

						//checks if customer books before
						if (reservationMap.containsKey(r.getCustomer())) {
							reservationMap.get(r.getCustomer()).add(r);
						} else {
							ArrayList<Reservation> reservationArrayList = new ArrayList<Reservation>();
							reservationArrayList.add(r);
							reservationMap.put(r.getCustomer(), reservationArrayList);
						}
						return true;
					}
				} else {
					//no reservation on that date, we can safely add reservation
					Boolean[] reservationsArray = new Boolean[24];
					for (int i=0; i<24; i++) {
						reservationsArray[i] = false;
					}
					r.setTableNo(t.getTableID());
					reservationsArray[bookingHour] = true;
					t.getReservations().put(bookingDate, reservationsArray);

					//checks if customer books before
					if (!reservationMap.containsKey(r.getCustomer())) {
						ArrayList<Reservation> reservationArrayList = new ArrayList<Reservation>();
						reservationArrayList.add(r);
						reservationMap.put(r.getCustomer(), reservationArrayList);
					} else {
						reservationMap.get(r.getCustomer()).add(r);
					}
					return true;
				}
			}
		}

		System.out.println("No available table found");
		return false;
	}

	public static Reservation findReservation(LocalDateTime dt, String name, String contactNo, boolean member) {
		LocalDate bookingDate = dt.toLocalDate();
		LocalTime bookingTime = dt.toLocalTime();

		Customer cus = new Customer(name, contactNo, member);
		//checks if reservation exits for customer
		if (reservationMap.containsKey(cus)) {
			for (Reservation r : reservationMap.get(cus)) {
				if (r.getTime() == bookingTime && r.getDate() == bookingDate) {
					return r;
				}
			}
		}
		return null;
	}

	private static void cancelReservation(Reservation r) {
		if (r == null) {
			System.out.println("Reservation cannot be found");
			return;
		}
		// get the date and time of booking
		LocalDate bookingDate = r.getDate();
		LocalTime bookingTime = r.getTime();
		int bookingHour = bookingTime.getHour();
		TableTwo restaurantTable = listOfTables.get(r.getTableNo());

		if (reservationMap.get(r.getCustomer()).size() == 1) {
			reservationMap.remove(r.getCustomer());
		} else {
			int i = 0;
			for (Reservation re : reservationMap.get(r.getCustomer())) {
				if (r.getTime() == re.getTime() && r.getDate() == re.getDate()) {
					reservationMap.get(r.getCustomer()).remove(i);
				}
				i += 1;
			}
		}

		listOfTables.get(r.getTableNo()).getReservations().get(r.getDate())[r.getHour()] = false;

		boolean isNotEmpty = false;
		for (int i=0; i<24; i++) {
			if (restaurantTable.getReservations().get(bookingDate)[i] != null) {
				isNotEmpty = true;
				break;
			}
		}

		if (!isNotEmpty) {
			restaurantTable.getReservations().remove(bookingDate);
		}
	}

	public static void updateReservation(Reservation oldReservation, Reservation newReservation) {
		if (oldReservation != null) {
			System.out.println("Reservation updated by performing the actions below");
			cancelReservation(oldReservation);
			addReservation(newReservation);
		} else {
			System.out.println("Previous reservation not found, New reservation not added");
		}
		/*
		cancelReservation(oldReservation);
		if (addReservation(newReservation)) {
			System.out.println("Successfully updated");
		} else {
			addReservation(oldReservation);
			System.out.println("Unable to amend reservation. Old reservation kept.");
		}*/

	public static void main(String[] args) {
		/*
		Customer c1 = new Customer("James", "123", false);
		Customer c2 = new Customer("John", "456", false);
		Customer c3 = new Customer("Mark", "789", false);
		Table t1 = new Table(0, 2);
		Table t2 = new Table(1, 2);
		Table t3 = new Table(2, 4);
		Table t4 = new Table(3, 4);
		*/
	}
}