import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Plant implements Comparable<Plant> {
    @Override
    public int compareTo(Plant plant) {
        return name.compareTo(plant.name);
    }

    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        setWatering(watering);
        setFrequencyOfWatering(frequencyOfWatering);
    }

    public Plant(String name, int frequencyOfWatering) {
        this(name, "", LocalDate.now(), LocalDate.now(), frequencyOfWatering);
    }

    public Plant(String name) {
        this(name, 7);
    }

    public Plant(String[] plantValues, int lineNumber) throws PlantException {
        final int PLANT_LENGTH = 5;
        if (plantValues.length != PLANT_LENGTH) {
            throw new PlantException("Řádek " + lineNumber + " musí obsahovat " + PLANT_LENGTH + " hodnot oddělenýcch tabulátorem: " + plantValues);
        }
        this.name = plantValues[0].trim();
        this.notes = plantValues[1].trim();
        try {
            this.planted = LocalDate.parse(plantValues[4].trim());
        } catch (DateTimeParseException e) {
            throw new PlantException("Chybný formát data zasazení na řádku " + lineNumber + ": " + e.getMessage());
        }
        try {
            setFrequencyOfWatering(Integer.parseInt(plantValues[2].trim()));
        } catch (NumberFormatException e) {
            throw new PlantException("Chybný formát frekvence zálivky na řádku " + lineNumber + ": " + e.getMessage());
        }
        try {
            setWatering(LocalDate.parse(plantValues[3].trim()));
        } catch (DateTimeParseException e) {
            throw new PlantException("Chybný formát data zálivky na řádku " + lineNumber + ": " + e.getMessage());
        }


    }

    //region Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) {
        if (watering.compareTo(getPlanted()) < 0) {
            throw new IllegalArgumentException("Datum zálivky nesmí být starší než datum zasazení rostliny!");
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) {
        if (frequencyOfWatering < 1) {
            throw new IllegalArgumentException("Frekvence zálivky musí být kladné číslo!");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }
    //endregion

    public void getWateringInfo(String name, LocalDate watering, int frequencyOfWatering) {
        System.out.println("Rostlina " + this.name + ", naposledy zalita dne " + this.watering + " by měla být znovu zalita dne " + (this.watering.plusDays(frequencyOfWatering)));
    }

    public void doWateringNow(LocalDate watering) {
        this.watering = LocalDate.now();
    }


    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", frequencyOfWatering=" + frequencyOfWatering +
                ", watering=" + watering +
                ", planted=" + planted +
                '}';
    }

    public String toTextRecord(String delimiter) {
        String result = ""
                + name + delimiter
                + notes + delimiter
                + frequencyOfWatering + delimiter
                + watering.toString() + delimiter
                + planted.toString();
        return result;
    }
}
