package hotelmanagementsystem.repository;

import hotelmanagementsystem.model.Guest;

import java.util.List;

public interface GuestRepository {

    void saveGuest(Guest guest);

    Guest findGuestById(Long guestId);

    void deleteGuestById(Long guestId);

    List<Guest> findAllGuest();
}
