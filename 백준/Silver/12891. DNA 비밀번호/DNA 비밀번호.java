import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int S, P, ans;
	static char[] dna;
	static int minA, minC, minG, minT, cntA, cntC, cntG, cntT;

	public static void main(String args[]) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        dna = br.readLine().toCharArray();
        
        st = new StringTokenizer(br.readLine());
        minA = Integer.parseInt(st.nextToken());
        minC = Integer.parseInt(st.nextToken());
        minG = Integer.parseInt(st.nextToken());
        minT = Integer.parseInt(st.nextToken());
        
        solve();
        System.out.println(ans);
    }
	
	static void solve() {
		// 전체 문자열 S의 맨 앞 P(부분문자열의 길이)개의 문자를 우선 계산
		for (int i = 0; i < P; i++) {
			switch(dna[i]) {
				case 'A': cntA++; break;
				case 'C': cntC++; break;
				case 'G': cntG++; break;
				case 'T': cntT++; break;
			}
		}
		
		check();
		// 이후에는 P 문자열 맨 앞 문자를 버리고, P 문자열 맨 뒤 문자에 이어지는 문자를 얻는 과정 반복 ( 끝까지 도달할때 까지 )
		// 함께 유효성 검증 => 통과하면 ans 추가 check() 를 별도로 구현
		
		for (int i = P; i < S; i++) {
			// 이전 부분문자열보다 새로운 부분 문자열을 완성
			// 맨 앞 하나 버리고
			switch(dna[i-P]) {
				case 'A': cntA--; break;
				case 'C': cntC--; break;
				case 'G': cntG--; break;
				case 'T': cntT--; break;
			}
			// 맨 뒤 다음 것 하나를 얻는 과정 반복
			switch(dna[i]) {
				case 'A': cntA++; break;
				case 'C': cntC++; break;
				case 'G': cntG++; break;
				case 'T': cntT++; break;
			}
			check();
		}
	}
	
	// check() 가 호출될 때는 부분문자열 경우가 하나 완성된 상태
	static void check() {
		if( cntA >= minA && cntC >= minC && cntG >= minG && cntT >= minT ) ans++;
	}
}