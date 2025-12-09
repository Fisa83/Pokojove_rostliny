public class Main {
    public static void main(String[] args) throws PlantException {

        PlantList plantList = new PlantList();

        try {
            plantList.readFromTestFile(Config.getFilePathWrongDate(), Config.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při čtení souboru " + Config.getFilePathWrongDate() + " : " + e.getMessage());
        }

        try {
            plantList.readFromTestFile(Config.getFilePathWrongFreq(), Config.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při čtení souboru " + Config.getFilePathWrongFreq() + " : " + e.getMessage());
        }

        try {
            plantList.readFromTestFile(Config.getFilePath(), Config.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při čtení souboru " + Config.getFilePath() + " : " + e.getMessage());
        }

        plantList.showWateringInfo();

        plantList.addPlant(new Plant("Fikus", 7));

        for (int i=1; i<=10; i++) {
            plantList.addPlant(new Plant("Tulipán na prodej " + i, 14));
        }

        plantList.removePlant(2);


        try {
            plantList.writeToTestFile(Config.getFilePathOut(), Config.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při zápisu do souboru: " + e.getMessage());
        }
        try {
            plantList.readFromTestFile(Config.getFilePathOut(), Config.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při čtení souboru: " + e.getMessage());
        }

        System.out.println();
        System.out.println("Neseřazený seznam květin:");
        plantList.getListOfPlants().forEach(System.out::println);

        System.out.println();
        System.out.println("Seřazený seznam květin podle výchozího klíče (názvu):");
        plantList.sortPlants();
        plantList.getListOfPlants().forEach(System.out::println);

        System.out.println();
        System.out.println("Seřazený seznam květin podle data zálivky:");
        plantList.sortPlantsByWatering();
        plantList.getListOfPlants().forEach(System.out::println);
    }
}