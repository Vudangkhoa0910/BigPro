package hotelmanagementsystem.repository;

import hotelmanagementsystem.config.HibernateUtils;
import hotelmanagementsystem.exception.GuestNotFoundException;
import hotelmanagementsystem.model.Address;
import hotelmanagementsystem.model.Guest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class GuestRepositoryImpl implements GuestRepository{

    @Override
    public void saveGuest(Guest guest) {

        try (Session session = HibernateUtils.getSessionFactory().openSession()){

            Transaction transaction = session.beginTransaction();

            // Create address class obj:

            Address address = new Address();

            address.setStreet(guest.getAddress().getStreet());
            address.setCity(guest.getAddress().getCity());
            address.setCountry(guest.getAddress().getCountry());
            address.setZipCode(guest.getAddress().getZipCode());

            guest.setAddress(address);

            session.save(guest);

            transaction.commit();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Guest findGuestById(Long guestId) {

        Session session = HibernateUtils.getSessionFactory().openSession();

        return session.get(Guest.class, guestId);

    }

    @Override
    public void deleteGuestById(Long guestId) {


        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Guest guest = session.load(Guest.class, guestId);

            if (guest == null){
                throw new GuestNotFoundException("Guest cannot be found with id: " +guestId);
            }
            session.delete(guest);
            transaction.commit();
            session.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }



    }

    @Override
    public List<Guest> findAllGuest() {

        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            List<Guest> guests = session.createQuery("FROM Guest").getResultList();
            return guests;
        } catch (HibernateException e){
            System.out.println(e.getMessage());
            return null;
        }



    }
}
