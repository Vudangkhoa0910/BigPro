package hotelmanagementsystem.service;

import hotelmanagementsystem.exception.GuestNotFoundException;
import hotelmanagementsystem.model.Address;
import hotelmanagementsystem.model.Guest;
import hotelmanagementsystem.repository.GuestRepository;

import java.util.List;
import java.util.Scanner;

public class GuestServiceImpl implements GuestService{

    private Scanner scanner;

    private final GuestRepository guestRepository;

    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public Guest saveGuest() {
        scanner = new Scanner(System.in);


        Guest guest = new Guest();

        System.out.println("Please enter the guest name: ");
        guest.setName(scanner.nextLine());


        Address address = new Address();

        System.out.println("Please enter guest street: ");
        address.setStreet(scanner.nextLine());

        System.out.println("Please enter guest city: ");
        address.setCity(scanner.nextLine());

        System.out.println("Please enter guest country: ");
        address.setCountry(scanner.nextLine());

        System.out.println("Please enter guest zip code: ");
        address.setZipCode(scanner.nextLine());

        // set the address to the guest:

        guest.setAddress(address);

        guestRepository.saveGuest(guest);
        System.out.println("Guest is saved successfully!");
        return guest;

    }

    @Override
    public void findGuestById(Long id) {

        try {
            Guest guestFound = guestRepository.findGuestById(id);
            if (guestFound != null){
                System.out.println("-----------------");
                System.out.println(guestFound);
            } else throw new GuestNotFoundException("Guest cannot be found with id: " + id);
        } catch (GuestNotFoundException e){
            System.out.println(e.getMessage());
        }



    }

    @Override
    public void deleteGuestById(Long guestId) {

        try {
            Guest guestFound = guestRepository.findGuestById(guestId);

            if (guestFound == null){

                throw new GuestNotFoundException("Guest cannot be found with id: " + guestId);

            }

            System.out.println("Are you sure to delete the guest: " + guestFound + "?");
            System.out.println("Press Y for yes, N for no!");

            String confirmation = scanner.next();

            if (confirmation.equalsIgnoreCase("y")){
                guestRepository.deleteGuestById(guestId);
                System.out.println("Guest is deleted successfully!");
            } else System.out.println("Delete operation is cancelled!");

        } catch (GuestNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Guest> findAllGuest() {

        try {
            List<Guest> guestList = guestRepository.findAllGuest();
            if (!guestList.isEmpty()){
                System.out.println("------- Guest List -------");
                for (Guest w : guestList){

                    System.out.println(w);
                    System.out.println("---------------");


                }

            } else throw new GuestNotFoundException("Guest list is empty...");

            return guestList;

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }


    }
}
