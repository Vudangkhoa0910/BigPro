package hotelmanagementsystem.repository;

import hotelmanagementsystem.config.HibernateUtils;
import hotelmanagementsystem.exception.HotelNotFoundResourceException;
import hotelmanagementsystem.model.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HotelRepositoryImpl implements HotelRepository{

    @Override
    public Hotel saveHotel(Hotel hotel) {

        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            session.save(hotel);

            transaction.commit();
            return hotel;

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public Hotel findHotel(Long id) {

        Session session = HibernateUtils.getSessionFactory().openSession();

        return session.get(Hotel.class, id);


    }

    @Override
    public void deleteHotelById(Long id) {

        try (Session session = HibernateUtils.getSessionFactory().openSession()){

            Transaction transaction = session.beginTransaction();

            Hotel hotelToDelete = session.get(Hotel.class, id);

            if (hotelToDelete != null){
                session.delete(hotelToDelete);
                transaction.commit();
            } else throw new HotelNotFoundResourceException("Hotel cannot be found with id: " + id);

            session.close();


        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Hotel> findAllHotel() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        return session.createQuery("FROM Hotel", Hotel.class).getResultList();
    }

    @Override
    public void updateHotel(Hotel hotel) {

        try (Session session = HibernateUtils.getSessionFactory().openSession()){

            Transaction transaction = session.beginTransaction();

            Hotel existHotel = session.get(Hotel.class, hotel.getId());

            if (existHotel != null){

                existHotel.setName(hotel.getName());
                existHotel.setLocation(hotel.getLocation());

                // Update other properties as needed.

                session.update(existHotel);
            }

            transaction.commit();
            HibernateUtils.closeSession(session);

        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }

    }

}
