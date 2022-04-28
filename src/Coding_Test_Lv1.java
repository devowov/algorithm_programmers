import java.util.*;

public class Coding_Test_Lv1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] stages = {2,1,2,4,2,4,3,3};
        int n = 5;

        System.out.println(Arrays.toString(solution.solution(n,stages)));
    }
}

class Solution {

    // kakao_2019_blind
    public int[] solution(int N, int[] stages) {

        int[] answer = new int[N];
        int players = stages.length;
        int[] summary = new int[N];

        // 실패율
        HashMap<Integer,Double> fail = new HashMap<>();

        for(int stage : stages)
        {
            if(stage == N+1) {
                continue;
            }
            summary[stage-1]++;
        }

        for(int i=0;i<summary.length;i++)
        {
            //String key = Integer.toString(i+1);

            if(players > 0) {
                double rate = (double) summary[i] / (double)players;
                fail.put(i, rate);
            }
            else if(players == 0){
                fail.put(i, 0.0);
            }
            players -= summary[i];
        }

        // 이건 왜 안되는 건데? -> 결과랑 다 똑같은데 왜....

//        List<Map.Entry<String,Double>> entryList = new ArrayList<Map.Entry<String, Double>>(fail.entrySet());
//        Collections.sort(entryList, new Comparator<Map.Entry<String, Double>>() {
//            @Override
//            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
//                int result = o2.getValue().compareTo(o1.getValue());
//                // 실패율이 같은 경우
//                if(result == 0)
//                {
//                    // 키 값 오름차순 정렬
//                    result = o1.getKey().compareTo(o2.getKey());
//                }
//                return result;
//            }
//        });
//
//        for (int i=0;i<entryList.size();i++) {
//            answer[i] = Integer.parseInt(entryList.get(i).getKey());
//        }
        for (int i = 0; i < N; i++) {
            double max = -1;
            int maxKey = 0;

            for (int key : fail.keySet()) {
                if (max < fail.get(key)) {
                    max = fail.get(key);
                    maxKey = key;
                }
            }

            answer[i] = maxKey + 1;
            fail.remove(maxKey);
        }

        return answer;
    }

    //폰켓몬
    public int solution(int[] nums) {
        int answer = 0;
        int got = nums.length/2;
        HashSet<Integer> poketmon = new HashSet<>();

        for(int num : nums)
        {
            if(poketmon.size() >= got){

                break;
            }
            else {
                poketmon.add(num);
            }
        }

        answer = poketmon.size();

        return answer;
    }

    // 탐욕법
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        answer += n - lost.length;

        // 여별을 가져온 학생이 도난당한 경우
        for(int i = 0;i<lost.length;i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (lost[i] == reserve[j]) {
                    answer++;
                    lost[i] = -1;
                    reserve[j] = -1;
                }
            }
        }


        for (int l : lost) {
                for (int i = 0; i < reserve.length; i++) {
                    int r = reserve[i];

                    if (l == r + 1 || l == r - 1) {
                        answer++;
                        reserve[i] = -1;
                        break;
                    }
                }
        }

        return answer;
    }

    // 완전탐색
    public int[] solution_03(int[] answers) {
        int[][] players = {{1, 2, 3, 4, 5},{2, 1, 2, 3, 2, 4, 2, 5},{3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
        int[] cnt = {0,0,0};

        for (int j=0;j<players.length;j++)
        {
            for(int i=0;i<answers.length;i++)
            {
                if(answers[i] == players[j][i%players[j].length])
                {
                    cnt[j] += 1;
                }
            }
        }
        // 최댓값 구하기
        int max = Math.max(Math.max(cnt[0], cnt[1]),cnt[2]); // max값 구하기

        int size = 0;
        for(int i=0;i<cnt.length;i++)
        {
            if(max == cnt[i])
            {
                size++;
            }
        }

        int[] answer = new int[size];
        size=0;

        for(int i=0;i<cnt.length;i++)
        {
            if(max == cnt[i])
            {
                answer[size] = i+1;
                size++;
            }
        }

        return answer;
    }

    // sort
    public int[] solution(int[] array, int[][] commands) {

        int[] answer = new int[commands.length];
        int size = 0;

        for(int[] command : commands)
        {
            int to = command[0]-1;
            int from = command[1]-1;
            int target = command[2]-1;

            int[] tmp = new int[(from-to)+1];

            int cnt = 0;

            for(int i=to;i<=from;i++)
            {
                tmp[cnt] = array[i];
                cnt++;
            }

            Arrays.sort(tmp);
            answer[size] = tmp[target];
            size++;
        }

        return answer;
    }

    // S_W Coding_1-1
    public int solution_02(int[] nums) {
        int answer = 0;
        int length = nums.length;

        for(int i=0;i<length;i++) {
            for(int j=i+1;j<length;j++) {
                for(int k=j+1;k<length;k++) {
                    if(isPrime(nums[i]+nums[j]+nums[k])) {
                        answer += 1;
                    }
                }
            }
        }

        return answer;
    }
    // S_W Coding_1-2
    private boolean isPrime(int num)
    {
        for(int i =2;i<num;i++)
        {
            if(num % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    // MCC_S1
    public int solution(int[] a, int[] b) {
        int answer = 0;

        for(int i=0;i<a.length;i++)
        {
            answer += a[i] * b[i];
        }

        return answer;
    }

    // MCC_S2
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;

        for (int i=0;i<absolutes.length;i++)
        {
            if(signs[i] == true)
            {
                answer = answer + absolutes[i];
            }
            else
            {
                answer = answer - absolutes[i];
            }
        }

        return answer;
    }

    // MCC_S3
    public int solution_001(int[] numbers) {
        int answer = 0;
        int[] base = {1,2,3,4,5,6,7,8,9,0};

        for (int num : base)
        {
            if(!Arrays.stream(numbers).anyMatch(n ->n == num))
            {
                answer = answer+num;
            }
        }

        return answer;
    }

    // kakao_2019_Internship
    public int solution(int[][] board, int[] moves) {

        int answer = 0;
        int[] yy = new int[board.length];
        Arrays.fill(yy, 0);

        Stack<Integer> basket = new Stack<>();
//
//        for (int i = 0; i < board.length; i++) {
//            System.out.println(Arrays.toString(board[i]));
//        }

        for (int i = 0; i < moves.length; i++) {
            int x = moves[i] - 1;
            int y = yy[x];

            yy[x] = yy[x] + 1;

            if(yy[x] <= board.length) {
                if (board[y][x] == 0) {

                    while (board[y][x] == 0) {
                        y = yy[x];
                        yy[x] = yy[x] + 1;
                    }
                    //System.out.println(board[y][x]);

                }
//                else {
//                    System.out.println(board[y][x]);
//                }


                if(!basket.isEmpty())
                {
                    int num = basket.pop();
                    if(num == board[y][x])
                    {
                        answer = answer+2;
                    }
                    else
                    {
                        basket.push(num);
                        basket.push(board[y][x]);
                    }

                }
                else {
                    basket.push(board[y][x]);
                }
            }
        }

        return answer;
    }
    
    // kakao_2020_Internship
    public String solution(int[] numbers, String hand) {

        String answer = "";
        int[] ex = {2,5,8,11};
        String handle = "";

        Stack<Integer> r_num = new Stack<>();
        Stack<Integer> l_num = new Stack<>();

        for (int num : numbers)
        {
            if(num == 0)
            {
                num = 11;
            }

            int nn = num;

            if(num%3==0)
            {
                handle = "R";
            }
            else
            {
                if(Arrays.stream(ex).anyMatch(n -> n == nn)) {
                    int r = r_num.isEmpty()?12: r_num.pop();
                    int l = l_num.isEmpty()?10: l_num.pop();

                    r_num.push(r);
                    l_num.push(l);

                    int r_dis = (Math.abs(nn - r) / 3) + (Math.abs(nn - r) % 3) ;
                    int l_dis = (Math.abs(nn - l) / 3) + (Math.abs(nn - l) % 3) ;

                    if (r_dis == l_dis) {
                        if (hand.equals("right")) {
                            handle = "R";
                        } else {
                            handle = "L";
                        }
                    } else if (r_dis > l_dis) {
                        handle = "L";
                    } else {
                        handle = "R";
                    }
                }
                else {
                    handle = "L";
                }
            }

            if(handle.equals("L")) {
                l_num.push(nn);
            }
            else {
                r_num.push(nn);
            }

            answer = answer+handle;
        }

        return answer;
    }

    // kakao_2021_Blind_Lv1
    public String solution(String new_id) {
        String answer = "";

        // 1단계
        new_id = new_id.toLowerCase();


        for (int i = 0; i < new_id.length(); i++) {

            char c = new_id.charAt(i);

            if (!Character.isDigit(c) && !Character.isAlphabetic(c)) {
                switch (c) {
                    case '-':
                    case '_':
                        break;
                    case '.':
                        if (answer.trim().length() == 0) {
                            c = ' ';
                        }

                        if (answer.trim().length() >= 1) {
                            if (answer.charAt(answer.length() - 1) == '.') {
                                c = ' ';
                            }
                        }
                        break;

                        // 2단계
                    default:
                        c = ' ';
                        break;
                }
            }
            answer = answer + c;
            answer = answer.trim();
        }


        if(answer.length() == 0) {
            answer = "a";
        }

        while (answer.charAt(answer.length()-1) == '.')
        {
            answer = answer.substring(0,answer.length()-1);
        }

        if(answer.length() >= 16)
        {
            answer =  answer.substring(0,15);
            while (answer.charAt(answer.length()-1) == '.')
            {
                answer = answer.substring(0,answer.length()-1);
            }
        }
        else if(answer.length() <= 2)
        {
            while (answer.length() < 3) {
                answer = answer + answer.charAt(answer.length()-1);

            }
        }



        return answer;
    }

    // kakao_2022_Blind_Lv1
    public int[] solution(String[] id_list, String[] report, int k) {

        int[] answer = new int[id_list.length];

        Map<String, HashSet<String>> data = new HashMap<>();
        Map<String, Integer> keyMap = new HashMap<>();

        for (int i = 0; i < id_list.length; i++) {
            String name = id_list[i];
            data.put(name, new HashSet<>());
            keyMap.put(name, i);
        }

        for (String warning : report) {
            String user = warning.split(" ")[0];
            String person = warning.split(" ")[1];

            data.get(person).add(user);
        }

        //System.out.println(data);

        for (int i = 0; i < id_list.length; i++) {
            HashSet<String> send = data.get(id_list[i]);
            //System.out.println(send.size());

            if (send.size() >= k) {
                for (String name : send) {
                    answer[keyMap.get(name)]++;
                }
            }
        }

        return answer;
    }

    //Dev_2021_Lv1
    public int[] solution_01 (int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        int match = 0;
        int zero = 0;

        Arrays.sort(win_nums);

        for (int i : lottos) {
            if (i == 0) {
                zero++;
                continue;
            }

            if (Arrays.binarySearch(win_nums, i) >= 0) {
                match++;
            }
        }

        // System.out.println(zero+ " / "+match);

        answer[0] = Math.max((7 - (zero + match)), 1) == 7 ? 6 : Math.max((7 - (zero + match)), 1);
        answer[1] = 7 - Math.max(match, 1);

        return answer;
    }
}


class kakao_2017_Lv2_01 {
    int answer = 0;
    String friends[] = {"A", "C", "F", "J", "M", "N", "R", "T"};
    boolean visited[];

    public int solution(int n, String[] data) {

        visited =  new boolean[friends.length];

        permutation(data, "", 0);

        return answer;
    }

    public void permutation(String[] data, String line,  int depth) {

        if (depth == friends.length) {
            if (checkPosition(data, line)) {
                //System.out.println(line);
                answer++; // 경우의 수를 담는 변수
            }
            return;
        }

        for (int i = 0; i < friends.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation(data, line + friends[i],  depth + 1);
                visited[i] = false;
            }
        }

    }

    boolean checkPosition(String[] data, String line) {

        for (String str : data) {

            int index_1 = line.indexOf(String.valueOf(str.charAt(0)));
            int index_2 = line.indexOf(String.valueOf(str.charAt(2)));

            String sign = String.valueOf(str.charAt(3));

            int num = Integer.parseInt(String.valueOf(str.charAt(4)));
            int dist = Math.abs(index_1 - index_2) - 1;

            if (sign.equals("=") && dist != num) {
                return false;
            } else if (sign.equals("<") && dist >= num) {
                return false;
            } else if (sign.equals(">") && dist <= num) {
                return false;
            }
        }
        return true;
    }
}

