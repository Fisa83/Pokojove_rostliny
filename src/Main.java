public class Main {
    public static void main(String[] args) {

        PlantList plantList = new PlantList();
        try {
            plantList.readFromTestFile(Config.getFilePath(), Config.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při čtení souboru: " + e.getMessage());
        }
        plantList.showWateringInfo();
        plantList.addPlant(new Plant("Fikus", 7));
        for (int i=1; i<=10; i++) {
            plantList.addPlant(new Plant("Tulipán na prodej " + i, 14));
        }
        plantList.removePlant(2);


        try {
            plantList.writeToTestFile(Config.getFilePath2(), Config.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při zápisu do souboru: " + e.getMessage());
        }
        try {
            plantList.readFromTestFile(Config.getFilePath2(), Config.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při čtení souboru: " + e.getMessage());
        }
        plantList.getListOfPlants().forEach(System.out::println);
        plantList.sortPlants();
        plantList.getListOfPlants().forEach(System.out::println);
        plantList.sortPlantsByWatering();
        plantList.getListOfPlants().forEach(System.out::println);
    }
}