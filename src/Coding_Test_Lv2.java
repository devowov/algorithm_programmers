import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Coding_Test_Lv2 {
    public static void main(String[] args) {
        Solution_Lv2 solution = new Solution_Lv2();

        String s = "xababcdcdababcdcd";
        System.out.println(solution.solution(s));
    }
}

class Solution_Lv2 {

    // lv2_2020_kakao_Blind
    public int solution(String s) {

        int len = s.length();
        int cnt = 1;
        int answer = len;


        for (int i = 1; i <= (len / 2); i++) {
            StringBuffer sb = new StringBuffer();
            String target = s.substring(0, i);
            String tmp = "";

            for (int j = i; j <= len; j += i) {

                if (i + j >= len) {
                    tmp = s.substring(j, len);
                } else {
                    tmp = s.substring(j, j + i);
                }

                if (tmp.equals(target)) {
                    cnt++;
                } else if (cnt == 1) {
                    sb.append(target);
                    target = tmp;
                } else {
                    sb.append(cnt).append(target);
                    //tmp = target;
                    target = tmp;
                    cnt = 1;

                }
            }

            if(!tmp.isEmpty()){
                sb.append(tmp);
            }
            answer = Math.min(answer, sb.length());
        }
        return answer;
    }

    //lv2_2022_kakao_Blind
    public int[] solution(int[] fees, String[] records) {

        int base_time = fees[0];
        int base_price = fees[1];
        int per_time = fees[2];
        int per_price = fees[3];

        List<String> in = new ArrayList<>();
        List<String> out = new ArrayList<>();

        Map<String, Integer> bill = new TreeMap<>();

        for (int i = 0; i < records.length; i++) {
            if (records[i].split(" ")[2].equals("IN")) {
                in.add(records[i]);
            } else if (records[i].split(" ")[2].equals("OUT")) {
                out.add(records[i]);
            }
        }

        for (int i = 0; i < in.size(); i++) {
            String in_data = in.get(i);

            String carno = in_data.split(" ")[1];
            String out_data = "";

            for (int j = 0; j < out.size(); j++) {
                if (out.get(j).split(" ")[1].equals(carno)) {
                    out_data = out.get(j);
                    out.remove(j);
                    break;
                }
            }

            String out_time = out_data.equals("") ? "23:59" : out_data.split(" ")[0];
            String in_time = in_data.split(" ")[0];

            int outt = Integer.valueOf(out_time.split(":")[0]) * 60 + Integer.valueOf(out_time.split(":")[1]);
            int intt = Integer.valueOf(in_time.split(":")[0]) * 60 + Integer.valueOf(in_time.split(":")[1]);
            int total = outt - intt;

            if (bill.containsKey(carno)) {
                bill.put(carno, bill.get(carno) + total);
            } else {
                bill.put(carno, total);
            }
        }


        int[] answer = new int[bill.size()];
        int cnt = 0;

        for (String key : bill.keySet()) {

            int time = bill.get(key);

            if (time <= base_time) {
                answer[cnt] = base_price;
            } else {
                answer[cnt] = (int) (base_price + Math.ceil((double) (time - base_time) / per_time) * per_price);
            }
            cnt++;
        }

        return answer;
    }
}