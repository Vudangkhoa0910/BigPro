package hotelmanagementsystem.repository;

import hotelmanagementsystem.config.HibernateUtils;
import hotelmanagementsystem.exception.ReservationNotFoundException;
import hotelmanagementsystem.model.Reservation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository{
    @Override
    public Reservation saveReservation(Reservation reservation) {

        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(reservation);

            transaction.commit();

            return reservation;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Reservation findReservationById(Long reservationId) {

        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Reservation reservation = session.get(Reservation.class, reservationId);

            if (reservation == null){
                throw new ReservationNotFoundException("Reservation cannot be found with id: " + reservationId);
            }

            return reservation;

        } catch (HibernateException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteReservationById(Long reservationId) {

        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Reservation reservation = session.load(Reservation.class, reservationId);

            if (reservation == null){
                throw new ReservationNotFoundException("Reservation cannot be found with id: "  + reservationId);
            }

            session.delete(reservation);
            transaction.commit();
            session.close();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Reservation> findAllReservation() {

        try {

            Session session = HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("From Reservation", Reservation.class).getResultList();

        } catch (HibernateException e){
            System.out.println(e.getMessage());
            return null;
        }


    }
}
