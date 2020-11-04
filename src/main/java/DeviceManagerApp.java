import lombok.AllArgsConstructor;
import model.Device;
import repository.CrudRepository;
import repository.MemoryRepository;

import java.util.Optional;
import java.util.Scanner;

import static model.Device.DeviceState.OFF;
import static model.Device.DeviceState.ON;

@AllArgsConstructor
public class DeviceManagerApp {

    private final CrudRepository crudRepository;

    public static void main(final String[] args) {
        new DeviceManagerApp(new MemoryRepository()).starUp();
    }

    private void starUp() {
        while (true) {
            var scanner = new Scanner(System.in);
            System.out.println("Insert command: ADD,DEL,GET,MOD,LIST or EXIT");
            var command = scanner.nextLine();
            var result = true;
            switch (command) {
                case "ADD":
                    result = addDevice();
                    break;
                case "DEL":
                    result = removeDevice();
                    break;
                case "GET":
                    result = getDevice();
                    break;
                case "MOD":
                    result = changeState();
                    break;
                case "LIST":
                    result = getAll();
                    break;
                case "EXIT":
                    result = exit();
                    break;
                default:
                    System.out.println("Invalid command");
                    result = false;
                    break;
            }
            if (result) {
                System.out.println("All was correct");
            } else {
                System.out.println("There have been same problems during the execution");
            }
        }
    }

    private boolean addDevice() {
        System.out.println("Inset name device");
        var name = new Scanner(System.in).nextLine();

        System.out.println("Insert state device");
        var state = new Scanner(System.in).nextLine().equalsIgnoreCase("ON") ? ON : OFF;

        var device = Device.builder()
                .name(name)
                .deviceState(state)
                .build();

        return crudRepository.add(device);
    }

    private boolean removeDevice() {
        System.out.println("Inset name device");
        var name = new Scanner(System.in).nextLine();
        return crudRepository.delete(name);

    }

    public boolean getDevice() {
        System.out.println("Inset name device");
        var name = new Scanner(System.in).nextLine();

        return Optional.ofNullable(crudRepository.get(name))
                .map(device -> {
                    System.out.println(device.toString());
                    return true;
                }).orElseGet(() -> {
                    System.out.println("Device not found");
                    return false;
                });
    }

    private boolean changeState() {
        System.out.println("Inset name device");
        var name = new Scanner(System.in).nextLine();
        return crudRepository.modify(name);
    }

    public boolean getAll() {
        crudRepository.getAll().forEach(System.out::println);
        return true;
    }

    private boolean exit() {
        System.exit(0);
        return false;
    }


}
