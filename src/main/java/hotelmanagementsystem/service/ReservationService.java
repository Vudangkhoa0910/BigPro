package hotelmanagementsystem.service;

import hotelmanagementsystem.model.Reservation;
import java.util.List;
public interface ReservationService {

    Reservation saveReservation();


    void findReservationById(Long reservationId);

    void deleteReservationById(Long reservationId);

    List<Reservation> findAllReservation();
}
