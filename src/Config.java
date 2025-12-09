public class Config {
    private static final String FILE_PATH = "resources/kvetiny.txt";
    private static final String FILE_PATH_OUT = "resources/kvetiny_out.txt";
    private static final String FILE_PATH_WRONG_DATE = "resources/kvetiny-spatne-datum.txt";
    private static final String FILE_PATH_WRONG_FREQ = "resources/kvetiny-spatne-frekvence.txt";
    private static final String DELIMITER = "\t";

    public static String getFilePath () {return FILE_PATH;}
    public static String getFilePathOut() {return FILE_PATH_OUT;}
    public static String getFilePathWrongDate() {return FILE_PATH_WRONG_DATE;}
    public static String getFilePathWrongFreq() {return FILE_PATH_WRONG_FREQ;}
    public static String getDelimiter () {return DELIMITER;}
}
