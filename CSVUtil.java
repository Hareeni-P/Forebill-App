import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class CSVUtil {
    public static void saveTransaction(Transaction t) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("transactions.csv", true))) {
            pw.printf("%s,%d,%.2f,%.2f,%.2f,%.2f\n",
                t.item, t.qty, t.op, t.sp, t.total, t.profit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
