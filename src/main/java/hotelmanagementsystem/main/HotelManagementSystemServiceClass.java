package hotelmanagementsystem.main;

import hotelmanagementsystem.exception.HotelNotFoundResourceException;
import hotelmanagementsystem.model.Hotel;
import hotelmanagementsystem.repository.*;
import hotelmanagementsystem.service.*;

import java.util.Scanner;

public class HotelManagementSystemServiceClass {

    public static Scanner scanner;

    public static void displayHotelManagementSystemMenu(){

        scanner = new Scanner(System.in);

        HotelRepository hotelRepository = new HotelRepositoryImpl();
        HotelService hotelService = new HotelServiceImpl(hotelRepository);

        RoomRepository roomRepository = new RoomRepositoryImpl();
        RoomService roomService = new RoomServiceImpl(roomRepository, hotelRepository);

        GuestRepository guestRepository = new GuestRepositoryImpl();
        GuestService guestService = new GuestServiceImpl(guestRepository);

        ReservationRepository reservationRepository = new ReservationRepositoryImpl();
        ReservationService reservationService = new ReservationServiceImpl( reservationRepository,
                                                                            guestRepository,
                                                                            roomRepository        );


        boolean exit = false;

        while (!exit){

            System.out.println("---- Hotel Management System Menu ----");
            System.out.println("1. Hotel Operations: ");
            System.out.println("2. Room Operations: ");
            System.out.println("3. Reservation Operations: ");
            System.out.println("4. Guest Operations: ");
            System.out.println("5. Exit: ");

            System.out.print("Enter your choice please: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    displayHotelOperationMenu(hotelService);
                    break;
                case 2:
                    displayRoomOperationMenu(roomService);
                    break;
                case 3:
                    displayReservationOperationMenu(reservationService);
                    break;
                case 4:
                    displayGuestOperationMenu(guestService);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for using our system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter number from 1 to 5");
                    break;
            }



        }

    }


    private static void displayHotelOperationMenu (HotelService hotelService) {

        scanner = new Scanner(System.in);

        boolean exit = false;

        Long hotelId;

        while (!exit) {

            System.out.println("---- Hotel Operation ----");
            System.out.println("1. Add New Hotel: ");
            System.out.println("2. Find Hotel By ID: ");
            System.out.println("3. Delete Hotel By ID: ");
            System.out.println("4. Find All Hotels: ");
            System.out.println("5. Update Hotel By ID: ");
            System.out.println("6. Return to Main Menu: ");

            System.out.print("Enter your choice please: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    hotelService.saveHotel();
                    break;
                case 2:
                    System.out.println("Please enter hotel ID: ");
                    hotelId = scanner.nextLong();
                    hotelService.getHotelById(hotelId);
                    break;
                case 3:
                    System.out.println("Please enter hotel ID");
                    hotelId = scanner.nextLong();
                    hotelService.deleteHotelById(hotelId);
                    break;
                case 4:
                    hotelService.getAllHotels();
                    break;
                case 5:
                    System.out.println("Please enter hotel ID");
                    hotelId = scanner.nextLong();
                    scanner.nextLine();

                    try {
                        Hotel existHotel = hotelService.getHotelById(hotelId);
                        System.out.println("Enter the hotel name to update");
                        String name = scanner.nextLine();

                        System.out.println("Enter the hotel location to update");
                        String location = scanner.nextLine();
                        Hotel updateHotel = new Hotel();
                        updateHotel.setId(hotelId);
                        updateHotel.setName(name);
                        updateHotel.setLocation(location);
                        hotelService.updateHotelById(hotelId, updateHotel);

                    } catch (HotelNotFoundResourceException e){
                        System.out.println(e.getMessage());
                    }


                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using our system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter number from 1 to 6");
                    break;
            }



        }
    }

    private static void displayRoomOperationMenu (RoomService roomService){

        scanner = new Scanner(System.in);

        boolean exit = false;

        Long roomId;

        while (!exit) {

            System.out.println("---- Room Operation ----");
            System.out.println("1. Add New Room: ");
            System.out.println("2. Find Room By ID: ");
            System.out.println("3. Delete Room By ID: ");
            System.out.println("4. Find All Room: ");
            System.out.println("5. Return to Main Menu: ");

            System.out.print("Enter your choice please: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    roomService.saveRoom();
                    break;
                case 2:
                    System.out.println("Please enter room id: ");
                    roomId = scanner.nextLong();
                    roomService.findRoomById(roomId);
                    break;
                case 3:
                    System.out.println("Please enter room id: ");
                    roomId = scanner.nextLong();
                    roomService.deleteRoomById(roomId);
                    break;
                case 4:
                    roomService.getAllRooms();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for using our system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter number from 1 to 5");
                    break;
            }


        }
    }

    private static void displayReservationOperationMenu (ReservationService reservationService){

        scanner = new Scanner(System.in);

        boolean exit = false;

        Long reservationId;

        while (!exit) {

            System.out.println("---- Reservation Operation ----");
            System.out.println("1. Add New Reservation: ");
            System.out.println("2. Find Reservation By ID: ");
            System.out.println("3. Delete Reservation By ID: ");
            System.out.println("4. Find All Reservation: ");
            System.out.println("5. Return Main Menu: ");

            System.out.print("Enter your choice please: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    reservationService.saveReservation();
                    break;
                case 2:
                    System.out.println("Please enter reservation id:");
                    reservationId = scanner.nextLong();
                    reservationService.findReservationById(reservationId);
                    break;
                case 3:
                    System.out.println("Please enter reservation id:");
                    reservationId = scanner.nextLong();
                    reservationService.deleteReservationById(reservationId);
                    break;
                case 4:
                    reservationService.findAllReservation();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for using our system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter number from 1 to 5");
                    break;
            }



        }

    }

    private static void displayGuestOperationMenu (GuestService guestService){

        scanner = new Scanner(System.in);

        boolean exit = false;

        Long guestId;

        while (!exit) {

            System.out.println("---- Guest Operation ----");
            System.out.println("1. Add New Guest: ");
            System.out.println("2. Find Guest By ID: ");
            System.out.println("3. Delete Guest By ID: ");
            System.out.println("4. Find All Guest: ");
            System.out.println("5. Return Main Menu: ");

            System.out.print("Enter your choice please: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    guestService.saveGuest();
                    break;
                case 2:
                    System.out.println("Please enter guest id: ");
                    guestId = scanner.nextLong();
                    guestService.findGuestById(guestId);
                    break;
                case 3:
                    System.out.println("Please enter guest id:");
                    guestId = scanner.nextLong();
                    guestService.deleteGuestById(guestId);
                    break;
                case 4:
                    guestService.findAllGuest();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for using our system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter number from 1 to 5");
                    break;
            }



        }

    }

}
