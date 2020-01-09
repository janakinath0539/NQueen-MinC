
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {


    public static int[] MIN_conflict(int[][] b,int N,int col){

        int[] c=new int[N];
        for (int k=0;k<N;k++){

            for(int j=0;j<N;j++){

                if(b[k][j]==1&&j!=col){
                    c[k]++;
                    break;
                }
            }

            for(int i=k,j=col;i<N&&j>=0;i++,j--){

                if(b[i][j]==1&&j!=col){
                    c[k]++;
                    break;
                }
            }


            for(int i=k,j=col;i<N&&j<N;i++,j++){

                if(b[i][j]==1&&j!=col){
                    c[k]++;
                    break;
                }
            }

            for(int i=k,j=col;i>=0&&j>=0;i--,j--){

                if(b[i][j]==1&&j!=col){
                    c[k]++;
                    break;
                }
            }

            for(int i=k,j=col;i>=0&&j<N;i--,j++){

                if(b[i][j]==1&&j!=col){
                    c[k]++;
                    break;
                }
            }

        }

        return c;
    }

    public static  boolean checkToStop(int [][] b,int N){

        int[] c=new int[N];
        for(int m=0;m<N;m++){
            for(int n=0;n<N;n++){
                if(b[m][n]==1){

                    for(int j=0;j<N;j++){

                        if(b[m][j]==1&&j!=n){
                            c[m]++;
                            break;
                        }
                    }

                    for(int i=m,j=n;i<N&&j>=0;i++,j--){

                        if(b[i][j]==1&&j!=n){
                            c[m]++;
                            break;
                        }
                    }


                    for(int i=m,j=n;i<N&&j<N;i++,j++){

                        if(b[i][j]==1&&j!=n){
                            c[m]++;
                            break;
                        }
                    }

                    for(int i=m,j=n;i>=0&&j>=0;i--,j--){

                        if(b[i][j]==1&&j!=n){
                            c[m]++;
                            break;
                        }
                    }

                    for(int i=m,j=n;i>=0&&j<N;i--,j++){

                        if(b[i][j]==1&&j!=n){
                            c[m]++;
                            break;
                        }
                    }


                }
            }
        }

        for(int i=0;i<N;i++){
            if(c[i]!=0)
                return false;
        }

        return true;

    }

    public static void print(int b[][],int N){

        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {

                if(b[i][j]==1)
                  System.out.print("x");
                else
                    System.out.print("o");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("enter the size of the board:");
            int N = Integer.parseInt(br.readLine());
            int[][] b = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print("enter the value \"x\" if u wish to put the queen in (" + i + "," + j + ")" + "else enter \"o\":");
                    if(br.readLine().matches("x"))
                        b[i][j]=1;
                    else
                        b[i][j]=0;
                }
            }
            System.out.println("Initial State configuration is:");
        print(b,N);
        while(checkToStop(b,N)!=true) {
            int col = (int) (Math.random() * N);
            int[] c = MIN_conflict(b, N, col);
            int min = c[0];
            for (int i = 1; i < N; i++) {

                if (c[i] < min)
                    min = c[i];
            }
            List<Integer> ls = new ArrayList<>();
            for (int i = 0; i < N; i++)
                if (c[i] == min)
                    ls.add(i);
            Collections.shuffle(ls);
            int move = ls.get(0);
            for (int i = 0; i < N; i++) {

                if (i == move)
                    b[i][col] = 1;
                else
                    b[i][col] = 0;
            }
        }

        System.out.println("Final state configuration:");
        print(b,N);
        }
        catch(IOException e){
            System.out.println(e);
        }
     }
}
