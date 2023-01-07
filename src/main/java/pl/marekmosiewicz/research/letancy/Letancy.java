/*
 * Public domain
 */

package pl.marekmosiewicz.research.letancy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Demonstrates performance of memory access
 * time when not hitting processor cache.
 * 
 * First loop gets data only from start of list
 * so it can be stored in processor cache.
 * It gets around 1 billion iterations per second
 * 
 * Second loop takes random data from whole list.
 * Then we have only around 70 million iterations
 * per second (one thread)
 * 
 * So it shows that random memory access is 
 * bottleneck  in current computers
 * 
 * Test performed on Intel i5 11 and memory
 * DDR4 2600
 * 
 * @author Marekj Andrzej Mosiewicz
 */
public class Letancy {
    public final static int SIZE=100000000;
    public final static int COUNT=100000000;
    public final static int FIT_CACHE=10;
    public final static int STRING_LENGTH=10;
    
    List<String> data = new ArrayList<>();
    public static void main(String[] args) {
        Letancy test = new Letancy();
        System.out.println("Inititalizing data");
        test.init();
        Date now = new Date();
        System.out.println("1000 000 000 charAt without shuffle");
        test.withOutShuffle();
        System.out.println("Time (ms):"+(new Date().getTime()-now.getTime()));
        now = new Date();
        System.out.println("1000 000 000 charAt with shuffle");
        test.withShuffle();
        System.out.println("Time (ms):"+(new Date().getTime()-now.getTime()));        
    }
    private void init(){
        for(int i=0;i<SIZE;i++){
            Random random = new Random();
            var buf = new StringBuilder(10);
            for(int j=0;j<10;j++){
                buf.append((char)(random.nextInt(26)+'a'));
            }
            data.add(buf.toString());
        }
    }

    private void withShuffle() {
        Random random = new Random();
        for(int i=0;i<COUNT;i++){
            int pos = random.nextInt(SIZE);
            char test = data.get(pos).charAt(pos%(STRING_LENGTH-1));
        }
    }

    private void withOutShuffle() {
        Random random = new Random();
        for(int i=0;i<COUNT;i++){
            int pos = random.nextInt(FIT_CACHE);
            char test = data.get(pos).charAt(pos%(STRING_LENGTH-1));
        }
    }
}
