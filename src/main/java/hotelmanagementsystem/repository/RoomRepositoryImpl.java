package hotelmanagementsystem.repository;

import hotelmanagementsystem.config.HibernateUtils;
import hotelmanagementsystem.exception.RoomNotFoundException;
import hotelmanagementsystem.model.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomRepositoryImpl implements RoomRepository{

    @Override
    public Room saveRoom(Room room) {

        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            session.save(room);

            transaction.commit();
            return room;

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Room findRoomById(Long RoomId) {

        Session session = HibernateUtils.getSessionFactory().openSession();

        return session.get(Room.class, RoomId);
    }

    @Override
    public void deleteRoomById(Long id) {

        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Room roomToDelete = session.load(Room.class, id);

            if (roomToDelete != null){
                session.delete(roomToDelete);
                transaction.commit();
            } else throw new RoomNotFoundException("Room cannot be found with id: " + id);

            session.close();
        } catch (RoomNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Room> findAllRooms() {

        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Room> rooms = session.createQuery("FROM Room", Room.class).getResultList();
        return rooms;

    }

}
