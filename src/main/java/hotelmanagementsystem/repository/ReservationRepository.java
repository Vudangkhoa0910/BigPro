package hotelmanagementsystem.repository;

import hotelmanagementsystem.model.Reservation;

import java.util.List;

public interface ReservationRepository {

    Reservation saveReservation(Reservation reservation);

    Reservation findReservationById(Long reservationId);

    void deleteReservationById(Long reservationId);

    List<Reservation> findAllReservation();
}
