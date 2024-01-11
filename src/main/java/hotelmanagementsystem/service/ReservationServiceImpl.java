package hotelmanagementsystem.service;

import hotelmanagementsystem.exception.GuestNotFoundException;
import hotelmanagementsystem.exception.ReservationNotFoundException;
import hotelmanagementsystem.exception.RoomNotFoundException;
import hotelmanagementsystem.model.Guest;
import hotelmanagementsystem.model.Reservation;
import hotelmanagementsystem.model.Room;
import hotelmanagementsystem.repository.GuestRepository;
import hotelmanagementsystem.repository.ReservationRepository;
import hotelmanagementsystem.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReservationServiceImpl implements ReservationService{

    private Scanner scanner;
    private final ReservationRepository reservationRepository;


    private final GuestRepository guestRepository;

    private final RoomRepository roomRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  GuestRepository guestRepository,
                                  RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
    }


    @Override
    public Reservation saveReservation() {

        scanner = new Scanner(System.in);

        System.out.println("Please enter the guest id:");
        Long guestId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Please enter the room id:");
        Long roomId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Please enter check in date (yyyy-MM-dd): ");
        LocalDate checkInDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Please enter check out date (yyyy-MM-dd): ");
        LocalDate checkOutDate = LocalDate.parse(scanner.nextLine());

        try {
            Guest existGuest = guestRepository.findGuestById(guestId);
            if (existGuest == null){

                throw new GuestNotFoundException("Guest cannot be found with id: " + guestId);

            }

            Room existRoom = roomRepository.findRoomById(roomId);
            if (existRoom == null){

                throw new RoomNotFoundException("Room cannot be found with id: " + roomId);

            }

            Reservation reservation = new Reservation();

            reservation.setGuest(existGuest);
            reservation.setRoom(existRoom);
            reservation.setCheckInDate(checkInDate);
            reservation.setCheckOutDate(checkOutDate);

            reservationRepository.saveReservation(reservation);

            System.out.println("Reservation saved successfully with id: " + reservation.getId());

            return reservation;

        } catch (GuestNotFoundException | RoomNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public void findReservationById(Long reservationId) {

        try {
            Reservation reservation = reservationRepository.findReservationById(reservationId);

            if (reservation == null){
                throw new ReservationNotFoundException("Reservation cannot be found with id: " + reservation);
            }

            System.out.println(reservationId);

        } catch (ReservationNotFoundException e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void deleteReservationById(Long reservationId) {

        try {

            Reservation reservation = reservationRepository.findReservationById(reservationId);

            if (reservation == null){
                throw new ReservationNotFoundException("Reservation cannot be found with id: " + reservationId);
            }

            System.out.println("Are you sure to delete the reservation: " + reservation + "?");
            System.out.println("Press Y for yes, N for no!");
            String confirmation = scanner.next();

            if (confirmation.equalsIgnoreCase("y")){

                reservationRepository.deleteReservationById(reservationId);
                System.out.println("Following reservation is deleted successfully: " + reservation);

            } else System.out.println("Delete operation is cancelled.");

        } catch (ReservationNotFoundException e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public List<Reservation> findAllReservation() {

        try {

            List<Reservation> reservationList = reservationRepository.findAllReservation();

            if (reservationList.isEmpty()){
                System.out.println("Reservation list is empty...");
            }

            System.out.println("----------- Reservation List -----------");

            for (Reservation w: reservationList){

                System.out.println(w);
                System.out.println("-------------");

            }

            return reservationList;

        } catch (ReservationNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

}
