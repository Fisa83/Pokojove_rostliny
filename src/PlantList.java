import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class PlantList {


    private List<Plant> listOfPlants = new ArrayList<>();

    public void addPlant(Plant plant) {
        listOfPlants.add(plant);
    }

    public Plant getPlant(int index) {
        return listOfPlants.get(index - 1);
    }

    public void removePlant(int plant) {
        listOfPlants.remove(plant);
    }

    public List<Plant> getListOfPlants() {
        return new ArrayList<>(listOfPlants);
    }

    public List<Plant> needWatering(Plant plant) {
        List<Plant> needWateringPlants = new ArrayList<>();
        for (Plant p : listOfPlants) {
            if (p.getWatering().plusDays(p.getFrequencyOfWatering()).isBefore(LocalDate.now().plusDays(1))) {
                needWateringPlants.add(p);
            }
        }
        return needWateringPlants;
    }

    public void sortPlants() {
        listOfPlants.sort(Plant::compareTo);
    }

    public void sortPlantsByName() {
        listOfPlants.sort(Comparator.comparing(Plant::getName));
    }

    public void sortPlantsByWatering() {
        listOfPlants.sort(Comparator.comparing(Plant::getWatering));
    }

    public void readFromTestFile(String filePath, String delimiter) throws PlantException {
        int lineNumber = 0;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filePath)))) {
            listOfPlants.clear();
            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine();
                String[] parts = line.split(delimiter);
                Plant plant = new Plant(parts, lineNumber);
                listOfPlants.add(plant);
            }

        } catch (FileNotFoundException e) {
            throw new PlantException("Soubor " + filePath + "nebylo možno otevřít:" + e.getMessage());
        }
    }

    public void writeToTestFile(String filePath, String delimiter) throws PlantException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            for (Plant p : listOfPlants) {
                writer.println(p.toTextRecord(delimiter));
            }
        } catch (IOException e) {
            throw new PlantException("Soubor již existuje: " + e.getMessage());
        }
    }
    public void showWateringInfo () {
        for (Plant p : listOfPlants) {
            System.out.println("Rostlina " + p.getName()+" byla naposledy zalita dne " + p.getWatering() + ", znovu by měla být zalita " + p.getWatering().plusDays(p.getFrequencyOfWatering()) + ".");
        }
    }
}
