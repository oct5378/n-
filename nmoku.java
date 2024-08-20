public class nmoku{
    public static void main(String[] args){
        
        //変数宣言
        System.out.println("n, size x, size y");
        int n=Integer.parseInt(System.console().readLine("n="));  //n目並べの n を入力
        int x=Integer.parseInt(System.console().readLine("x="));  //横のマス目を入力
        int y=Integer.parseInt(System.console().readLine("y="));  //縦のマス目を入力
        
        String stone;  //現在ターンの石を格納する変数
        int player_x, player_y;  //ゲーム時プレイヤーが配置する石の位置を格納する変数 x と y
        int round=0, count=0, point=0;  //ラウンド数round, 判定用変数count, 得点point

        boolean game_bool=true, turn_bool=true, input_bool=true;  //ゲーム部分のwhile持続用, 現在ターンの石決定用, プレイヤー入力時のwhile持続用

        String space_tmp_String;  //ban出力時に使う  space[], space_tmpの2変数の部分が冗長な気がする
        int space_tmp_int;


        //side_short, side_long用意 (短辺と長辺  斜め判定に使う)
        int side_short, side_long;
        if (x>y)
        {
            side_long=x;
            side_short=y;
        }
        else
        {
            side_long=y;
            side_short=x;
        }


        //space[]用意  表示する数字の桁数のズレを補正する  ban出力時使用
        String X=x+"", Y=y+"";  //Stringに変換
        int length_max;  //最大ケタ数を格納  この変数はゲーム時使用しない

        if (X.length()>Y.length())
        {
            length_max = X.length();
        }
        else
        {
            length_max = Y.length();
        }

        String[] space=new String[length_max];  //space[]宣言

        for (int i=0; i < length_max; i++)  //space[]作成
        {
            space[i]="";
        }

        for (int i=0; i < length_max; i++)  //space[]用意  x,yの最大ケタ数がaだったら -> space[k]=半角空白(a-k-1)個
        {                                   //正直分かりにくい  たぶんこの部分全部削って、簡単にできる
            for (int j=0; j < i; j++)
            {
                space[length_max-i-1]+=" ";
            }
        }


        //ban用意
        String[][] ban=new String[x][y];
        for (int tx=0;tx<x;tx++)
        {
            for (int ty=0;ty<y;ty++)
            {
                ban[tx][ty]="-";  //何も石が置かれていない部分は - と表示
            }
        }


        //ゲーム部分
        while(game_bool)
        {
            //現在の石(交互)
            if (turn_bool)
            {
                turn_bool=false;
                stone="○";
            }
            else
            {
                turn_bool=true;
                stone="●";
            }


            //プレイヤー石配置
            if (round!=0)  //round==0の時回避
            {
                input_bool=true;
            }
            else
            {
                input_bool=false;
            }

            while (input_bool)  //プレイヤー入力部分
            {
                System.out.println(stone);  //石表示  今誰の番か
                player_x=Integer.parseInt(System.console().readLine("x="));  //x入力
                player_y=Integer.parseInt(System.console().readLine("y="));  //y入力
                
                if (1 > player_x || player_x > x || 1 > player_y || player_y > y)  //入力した値が範囲を超えている場合
                {
                    System.out.println("enter position between 1 - "+x);
                }
                else if (ban[player_x-1][player_y-1]!="-")  //入力した位置に既に石が置かれている場合
                {
                    System.out.println("unavailable position");
                }
                else  //配置可能な場合
                {
                    input_bool=false;
                    ban[player_x-1][player_y-1]=stone;
                }
            }


            //ban出力
            System.out.print(round+"\n "+space[0]+" ");  //何ラウンド目か表示

            for (int tx=0;tx<x;tx++)  //ban上部1行の数字部分 (x座標) を表示
            {
                space_tmp_String = (tx+1)+"";
                space_tmp_int = space_tmp_String.length()-1;
                System.out.print((tx+1) + space[space_tmp_int] + " ");
            }
            
            for (int ty=0;ty<y;ty++)  //ban内部を表示
            {
                space_tmp_String = (ty+1)+"";
                space_tmp_int = space_tmp_String.length()-1;
                System.out.print("\n" + (ty+1) + space[space_tmp_int] + " ");
                
                for (int tx=0;tx<x;tx++)
                {
                    System.out.print(ban[tx][ty] + space[0] + " ");
                }
            }
            System.out.println();


            //n並び判定  //横  //縦  //斜め1 (右下へ走査)  //斜め2 (右上へ走査)  の4部分
            //横
            for (int t=0;t<y;t++)
            {
                count=0;
                for (int s=0;s<x;s++)
                {
                    if (ban[s][t]==stone){count+=1;}
                    else {count=0;}
                    if (count>=n){point+=1;}
                }
            }

            //縦
            for (int s=0;s<x;s++)
            {
                count=0;
                for (int t=0;t<y;t++)
                {
                    if (ban[s][t]==stone){count+=1;}
                    else {count=0;}
                    if (count>=n){point+=1;}
                }
            }

            //斜め1 (右下の方向へ走査)
            for (int g=0;g<side_short-1;g++)  //左の三角形部分
            {
                count=0;
                for (int f=0;f<g+1;f++)
                {
                    if (ban[f][y-g+f-1]==stone){count+=1;}
                    else {count=0;}
                    if (count>=n){point+=1;}
                }
            }

            for (int g=0;g<side_short-1;g++)  //右の三角形部分
            {
                count=0;
                for (int f=0;f<g+1;f++)
                {
                    if (ban[x-g+f-1][f]==stone){count+=1;}
                    else {count=0;}
                    if (count>=n){point+=1;}
                }
            }

            if (x>y)  //平行四辺形の形の部分 (真ん中)  //どの辺が長いか 2パターンで場合分け
            {
                for (int h=0;h<side_long-side_short+1;h++)
                {
                    count=0;
                    for (int i=0;i<side_short;i++)
                    {
                        if (ban[h+i][i]==stone){count+=1;}
                        else {count=0;}
                        if (count>=n){point+=1;}
                    }
                }
            }
            else
            {
                for (int h=0;h<side_long-side_short+1;h++)
                {
                    count=0;
                    for (int i=0;i<side_short;i++)
                    {
                        if (ban[i][h+i]==stone){count+=1;}
                        else {count=0;}
                        if (count>=n){point+=1;}
                    }
                }
            }

            //斜め2 (右上の方向へ走査)
            for (int g=0;g<side_short-1;g++)  //左の三角形部分
            {
                count=0;
                for (int f=0;f<g+1;f++)
                {
                    if (ban[f][g-f]==stone){count+=1;}
                    else {count=0;}
                    if (count>=n){point+=1;}
                }
            }

            for (int g=0;g<side_short-1;g++)  //右の三角形部分
            {
                count=0;
                for (int f=0;f<g+1;f++)
                {
                    if (ban[x-g+f-1][y-f-1]==stone){count+=1;}
                    else {count=0;}
                    if (count>=n){point+=1;}
                }
            }

            if (x>y)  //平行四辺形の形の部分 (真ん中)  //どの辺が長いか 2パターンで場合分け
            {
                for (int h=0;h<side_long-side_short+1;h++)
                {
                    count=0;
                    for (int i=0;i<side_short;i++)
                    {
                        if (ban[h+i][y-i-1]==stone){count+=1;}
                        else {count=0;}
                        if (count>=n){point+=1;}
                    }
                }
            }
            else
            {
                for (int h=0;h<side_long-side_short+1;h++)
                {
                    count=0;
                    for (int i=0;i<side_short;i++)
                    {
                        if (ban[i][y-h-i-1]==stone){count+=1;}
                        else {count=0;}
                        if (count>=n){point+=1;}
                    }
                }
            }
            
            //勝敗判定 -> ゲーム続行/ 終了
            if (point>=1)
            {
                game_bool=false;
                System.out.print(stone);  //勝者の石を表示
            }

            round+=1;

        }  //ゲーム部分 while終端
        
        System.out.print(" win!");
    }
}
//System.out.print("/input_bool="+count+"/");  //デバッグ用のやつ
/*
メモ
・オブジェクト指向？
・一回の入力で一気に2つ以上数値入力したい　バラバラで数値入力しなくて済む方法
・あるいはマウス入力(GUI？)
・対戦コンピュータ
・重力
・3D
*/