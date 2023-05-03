import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomEinvoiceGenerator {

    //建立List記憶體變數
    static List<String> memoryList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int qty = 100;

        createData(qty);
        ReadDataToMemory();
        displayLogForAA();
        writeFileEvery5Datas();
    }

    public static void createData(Integer dataQty) throws IOException {


        String filePath = "C:\\Users\\user\\Desktop\\myEinvoice.txt";
        File myTXT = new File(filePath);

        StringBuilder builder = new StringBuilder();

        //        System.out.println(getEinvoice());
        BufferedWriter writeData = new BufferedWriter(new FileWriter(myTXT));
        for (int i = 1; i <= dataQty; i++) {
            builder.append(getEinvoice()).append("\n");

            if (i % 5 == 0) {
                writeData.write(builder.toString());
                writeData.flush();
                builder.setLength(0);
            }
        }
        writeData.close();
    }



    public static void ReadDataToMemory() throws IOException {
        //取得myTXT的文字檔
        String filePath = "C:\\Users\\user\\Desktop\\myEinvoice.txt";
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);

        //資料寫入記憶體中 List

        while (br.ready()){
//            System.out.println(br.readLine());
            memoryList.add(br.readLine());
        }

    }

    public static void displayLogForAA() throws IOException {

        //讀取記憶體
        for(String memoryString : memoryList) {
//            System.out.println(memoryString);
            //判斷記憶體內容是否為AA
            String headerString = memoryString.substring(0,2);
            if("AA".equals(headerString)){
                //若開頭為AA的資料的就印出LOG
                System.out.println("印出LOG:" + memoryString);
            }
        }
    }
    public static void writeFileEvery5Datas() throws IOException {
        List<String> TempEinvoiceList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        String eInvoiceFilePath = "C:\\Users\\user\\Desktop\\eInvoiceFromMemory.txt";
        File eInvoiceFile = new File(eInvoiceFilePath);
        FileWriter fw = new FileWriter(eInvoiceFile);
        BufferedWriter writeData = new BufferedWriter(fw);
        //讀取記憶體
        for(String memoryString : memoryList) {
            builder.append(getEinvoice()).append("\n");
            //如果資料已經滿5筆就寫入檔案
            TempEinvoiceList.add(memoryString);
            if (TempEinvoiceList.size() == 4) {
                writeData = new BufferedWriter(fw);
                writeData.write(builder.toString());
                writeData.flush();
                builder.setLength(0);
                //clear暫存記憶體
                TempEinvoiceList.clear();
            }
        }
        writeData.close();
    }

    /**
     * 亂數產生發票號碼 兩個英文字大寫+8個數字
     */
    private static String getEinvoice() {
        String myString = "";
        StringBuilder myNum = new StringBuilder();
        for (int i = 1; i <= 2; i++) {
            int randomAsciiForAtoZ = new Random().nextInt(26) + 65;
//            int randomAsciiForAtoZ = new Random().nextInt(2) + 65;
            myString = myString + (char) randomAsciiForAtoZ;
        }
        for (int i = 1; i <= 8; i++) {
            int randomNumFor0to9 = new Random().nextInt(10);
            myNum.append(randomNumFor0to9);
        }
        return myString + myNum;
    }
}

