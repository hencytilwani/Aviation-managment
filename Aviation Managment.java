import java.util.*;

class Flight {
    int flightNumber;
    String destination;
    int capacity;
    int price;
    int bookedSeats;
    Flight next;

    public Flight(int flightNumber, String destination, int capacity, int price) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.capacity = capacity;
        this.price = price;
        this.bookedSeats = 0;
        this.next = null;
    }
}

class FlightLinkedList {
    Flight head;
    int a[] = new int[100];

    public FlightLinkedList() {
        this.head = null;
    }

    // Add Flight

    public void addFlight(int flightNumber, String destination, int capacity, int price) {
        Flight newFlight = new Flight(flightNumber, destination, capacity, price);

        if (head == null) {
            head = newFlight;
        } else if (head.flightNumber != flightNumber) {
            {
                Flight current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newFlight;
                return;
            }
        }
    }

    // method to check id flight already exists or not

    boolean check(int flightnumber) {
        boolean found = false;
        Flight current = head;
        while (current != null) {
            if (current.flightNumber == flightnumber) {
                System.out.println("flight already exist");
                found = true;
                break;
            }
            current = current.next;
        }
        if (found) {
            return false;
        } else {
            return true;
        }
    }

    // Display

    public void displayFlights() {
        Flight current = head;
        if (current == null) {
            System.out.println("No flights available.");
        } else {
            while (current != null) {
                System.out.println("Flight Number: " + current.flightNumber);
                System.out.println("Destination: " + current.destination);
                System.out.println("price per seat: " + current.price);
                System.out.println("Capacity: " + current.capacity);
                System.out.println("Booked Seats: " + current.bookedSeats);
                System.out.println("Available seats: " + (current.capacity - current.bookedSeats));
                System.out.println("-------------------------");
                System.out.println();
                current = current.next;
            }
        }
    }

    // Searh flight by using destination

    public void searchFlightsByDestination(String destination) {
        Flight current = head;
        boolean found = false;

        while (current != null) {
            if (current.destination.equalsIgnoreCase(destination)) {
                System.out.println("Flight Number: " + current.flightNumber);
                System.out.println("Destination: " + current.destination);
                System.out.println("Capacity: " + current.capacity);
                System.out.println("Booked Seats: " + current.bookedSeats);
                System.out.println("available seats:" + (current.capacity - current.bookedSeats));
                System.out.println("------------------------");
                System.out.println();
                found = true;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("No flights found for the destination: " + destination);
        }
    }

    // Book seat method

    public void bookSeat(int flightNumber, int seat) {
        Flight current = head;
        Boolean found = false;
        while (current != null) {
            if (current.flightNumber == flightNumber) {
                int availableseat = current.capacity;
                found = true;
                if (availableseat - seat >= 0) {
                    availableseat = availableseat - seat;
                    current.bookedSeats = current.bookedSeats + seat;
                    System.out.println(seat + " " + "Seat booked successfully for Flight " + flightNumber);

                } else {
                    System.out.println("Sorry, available seat are only: " + availableseat);
                }
                break;
            }

            current = current.next;
        }
        if (!found) {
            System.out.println("Flight not found.");
        }

    }

    // Method for cancle booking

    public void cancelBooking(int flightNumber, int seat) {
        Flight current = head;
        Boolean found = false;
        while (current != null) {
            if (current.flightNumber == flightNumber) {
                int bookedSeats = current.bookedSeats;
                found = true;
                if (bookedSeats >= seat) {
                    current.bookedSeats = bookedSeats - seat;
                    System.out.println(seat + " Seat(s) canceled successfully for Flight " + flightNumber);

                } else {
                    System.out.println(
                            "Sorry, there are only " + bookedSeats + " booked seat(s) for Flight " + flightNumber);

                }
                return;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("Flight not found.");
        }
    }

    // Method to remove flight

    public void removeFlight(int flightNumber) {
        Flight current = head;
        boolean found = false;

        while (current != null) {
            if (current.flightNumber == flightNumber) {
                found = true;
                break;
            }
            current = current.next;
        }

        if (found) {
            if (current == head) {
                head = current.next;
            } else {
                Flight prev = head;
                while (prev.next != current) {
                    prev = prev.next;
                }
                prev.next = current.next;
            }

            System.out.println("Flight " + flightNumber + " has been removed.");
        } else {
            System.out.println("Flight " + flightNumber + " not found.");
        }
    }

}

class AirlineManagementSystem {
    public static void main(String[] args) {
        FlightLinkedList flightList = new FlightLinkedList();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println();
            System.out.println("                       ================ Airline Management System ===============\n");
            System.out.println("                                       1. Add a Flight");
            System.out.println("                                       2. Remove a Flight");
            System.out.println("                                       3. Display All Flights");
            System.out.println("                                       4. Search Flights by Destination");
            System.out.println("                                       5. Book a Seat");
            System.out.println("                                       6. Cancle Booking");
            System.out.println("                                       7. Exit");
            System.out.println();
            System.out.println("                        ===========================================================");
            System.out.print("Select an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println();
                    System.out.print("Enter Flight Number: ");
                    int flightNumber = scanner.nextInt();
                    scanner.nextLine();
                    boolean n = flightList.check(flightNumber);
                    if (n) {
                        System.out.print("Enter Destination: ");
                        String destination = scanner.nextLine();
                        System.out.print("Enter Capacity: ");
                        int capacity = scanner.nextInt();
                        System.out.print("enter price of seat");
                        int price = scanner.nextInt();
                        flightList.addFlight(flightNumber, destination, capacity, price);
                        System.out.println("Flight added successfully.");
                    }
                    break;

                case 2:
                    System.out.println();
                    System.out.print("Enter Flight Number to Remove: ");
                    int removeFlightNumber = scanner.nextInt();
                    flightList.removeFlight(removeFlightNumber);
                    // System.out.println("Flight removed successfully.");
                    break;

                case 3:
                    System.out.println();
                    System.out.println("All Flights:");
                    flightList.displayFlights();
                    break;
                case 4:
                    System.out.println();
                    System.out.print("Enter Destination to Search: ");
                    String searchDestination = scanner.nextLine();
                    flightList.searchFlightsByDestination(searchDestination);
                    break;
                case 5:
                    System.out.println();
                    System.out.print("Enter Flight Number to Book a Seat: ");
                    int bookFlightNumber = scanner.nextInt();
                    System.out.print("Enter number of seat you want booked");
                    int seat = scanner.nextInt();
                    flightList.bookSeat(bookFlightNumber, seat);
                    break;
                case 6:
                    System.out.println();
                    System.out.print("Enter Flight Number to cancel a Seat: ");
                    int bookFlightNumber1 = scanner.nextInt();
                    System.out.print("enter number of seat you want to cancle");
                    int seat1 = scanner.nextInt();
                    flightList.cancelBooking(bookFlightNumber1, seat1);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }
}
