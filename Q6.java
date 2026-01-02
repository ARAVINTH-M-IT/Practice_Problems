import java.io.*;
import java.util.*;

public class Q6{

    static Set<String> BlockedMethods =
            new HashSet<>(Arrays.asList("Crypto", "Unknown"));

    static Map<String, UserDetails> UserDet = new HashMap<>();

    static class UserDetails {
        Set<String> countries;
        int minTime;
        int maxTime;
        int minAmt;
        int maxAmt;

        UserDetails(Set<String> countries, int minTime, int maxTime, int minAmt, int maxAmt) {
            this.countries = countries;
            this.minTime = minTime;
            this.maxTime = maxTime;
            this.minAmt = minAmt;
            this.maxAmt = maxAmt;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("src.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));

        String line = br.readLine();

        while ((line = br.readLine()) != null) {

            String[] val = line.split(",");
            List<String> err = new ArrayList<>();

            String txid = val[0];

            if (isEmp(val)) {
                err.add("EMPTY_FIELD");
            } else {
                String u1 = val[1];
                String country = val[2];
                int amt = Integer.parseInt(val[3]);
                String met = val[4];
                int time = Integer.parseInt(val[5]);

                if (amt < 5 || amt > 10)
                    err.add("INVALID_AMOUNT");

                if (BlockedMethods.contains(met))
                    err.add("BLOCKED_METHOD");

                if (!UserDet.containsKey(u1)) {
                    err.add("UNKNOWN_USER");
                } else {
                    double mat = userMat(UserDet.get(u1), country, time, amt);
                    if (mat < 0.5)
                        err.add("BEHAVIOR_MISMATCH");
                }
            }

            String sts = err.isEmpty() ? "OK" : "SUSPICIOUS";
            String errors = err.isEmpty() ? "-" :
                    String.join(",", err.subList(0, Math.min(2, err.size())));

            bw.write(txid + "," + sts + "," + errors + "\n");
        }

        br.close();
        bw.close();
    }

    static boolean isEmp(String[] f) {
        for (String s : f)
            if (s == null || s.trim().isEmpty())
                return true;
        return false;
    }

    static double userMat(UserDetails u, String country, int time, int amt) {
        int match = 0;
        if (u.countries.contains(country)) match++;
        if (time >= u.minTime && time <= u.maxTime) match++;
        if (amt >= u.minAmt && amt <= u.maxAmt) match++;
        return match / 3.0;
    }
}