package com.simonhochrein.StrategicCommander.core;

import java.util.List;

public class NameGenerator {
    public static String generateName() {

        String[] nm1 = new String[] {"a", "o", "u", "e"};
        String[] nm2 = new String[] {"b", "d", "g", "h", "j", "k", "l", "m", "n", "p", "r", "s", "t", "v", "w", "y", "ts", "th", "tr", "st", "sh", "gr", "ch", "kr", "kl", "dr"};
        String[] nm3 = new String[] {"a", "e", "i", "o", "u", "a", "o"};
        String[] nm4 = new String[] {"k", "k", "k", "m", "t", "r", "v", "g", "p", "n", "l", "d", "z", "b", "h", "m", "t", "r", "v", "g", "p", "n", "l", "d", "z", "b", "h", "r", "r", "r", "cl", "dm", "dr", "gh", "gr", "hl", "hm", "ll", "mp", "mt", "nk", "nm", "nt", "rg", "rk", "rl", "rn", "rp", "rr", "rt", "sk", "th", "tr", "wr", "yb"};
        String[] nm5 = new String[] {"k", "k", "m", "r", "k", "l", "n", "rgh", "ng", "x", "s", "n", "th", "hk", "hl", "d", "l", "c", "gh", "ss", "z", "ll", "rrd", "rd", "t", "q", "sh", "w", "rf"};
        String[] nm6 = new String[] {"o", "a", "i", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        String[] nm7 = new String[] {"'", "", "", "", "", "", "", "", "", ""};
        String[] nm8 = new String[] {"", "Ch'", "D'", "H'", "J'", "K'", "L'", "T'", "W'", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        String[] nm9 = new String[] {"b", "d", "g", "h", "j", "k", "l", "m", "n", "p", "r", "s", "t", "v", "w", "y", "ts", "th", "tr", "st", "sh", "gr", "ch", "kr", "kl", "dr"};
        String[] nm10 = new String[] {"d", "g", "h", "k", "l", "m", "n", "r", "t", "v", "x", "z", "lk", "nn", "tb", "hl", "rs", "ll", "lkr", "km", "dr", "rl", "lk", "lg", "rg", "sk", "th", "tr", "dm", "hm", "ng", "nk", "l", "n", "l", "n", "k"};
        String[] nm11 = new String[] {"r", "nn", "l", "h", "g", "n", "ss", "s", "yr", "st", "th", "j", "m", "v", "ll", "sh", "hl", "ng", "w"};
        String[] nm12 = new String[] {"o", "a", "i", "", "", "", "", "", ""};
        String[] nm13 = new String[] {"", "", "", "b", "c", "g'g", "d", "d'gh", "dr", "f", "g'", "g", "gr", "h", "j", "k'g", "k't", "k'mp", "k", "kh", "kl", "kr", "l", "m", "mn", "mr", "mv", "n", "ng", "p", "q", "r", "rr", "s", "sh", "t", "th", "tr", "v", "vr", "w", "x", "z"};
        String[] nm14 = new String[] {"c", "ct", "ck", "ch", "b", "d", "g", "gg", "ggr", "hn", "hnr", "k", "k'M", "ll", "lk", "lv", "lm", "lt", "mm", "mmr", "m", "mp", "mr", "nn", "nk", "nl", "nj", "nz", "ndl", "ns", "n", "nt", "r", "rr", "rs", "rmd", "rn", "rp", "rtr", "rst", "rt", "rg", "rm", "rd", "rsh", "ss", "str", "sht", "tzh", "v", "wr", "x", "yg", "z", "zh"};
        String[] nm15 = new String[] {"bh", "c", "ct", "ck", "cx", "ch", "d", "dh", "j", "g", "gh", "h", "k", "l", "lt", "m", "n", "nn", "ng", "r", "rc", "rr", "rgh", "rk", "rv", "rn", "rg", "sh", "sht", "s", "ss", "t", "th", "v", "x", "z", "zh"};

        String names = "";
        for(int i=0;i< 10;i++){
            if(i< 4){
                int rnd=(int)Math.floor(Math.random()*nm1.length);
                int rnd2=(int)Math.floor(Math.random()*nm9.length);
                int rnd3=(int)Math.floor(Math.random()*nm3.length);
                int rnd4=(int)Math.floor(Math.random()*nm11.length);
                int rnd5=(int)Math.floor(Math.random()*nm12.length);
                int rnd6=(int)Math.floor(Math.random()*nm7.length);
                int rnd7=(int)Math.floor(Math.random()*nm8.length);
                if(rnd6==0){
                    rnd7=0;
                }
                int rnd8=(int)Math.floor(Math.random()*nm13.length);
                int rnd9=(int)Math.floor(Math.random()*nm3.length);
                int rnd10=(int)Math.floor(Math.random()*nm14.length);
                int rnd11=(int)Math.floor(Math.random()*nm3.length);
                int rnd12=(int)Math.floor(Math.random()*nm15.length);
                int rnd13=(int)Math.floor(Math.random()*nm6.length);
                names=nm8[rnd7]+nm1[rnd]+nm9[rnd2]+nm7[rnd6]+nm3[rnd3]+nm11[rnd4]+nm12[rnd5]+" "+nm13[rnd8]+nm3[rnd9]+nm14[rnd10]+nm3[rnd11]+nm15[rnd12]+nm6[rnd13];
            }else if(i< 8){
                int rnd=(int)Math.floor(Math.random()*nm9.length);
                int rnd2=(int)Math.floor(Math.random()*nm3.length);
                int rnd3=(int)Math.floor(Math.random()*nm10.length);
                int rnd4=(int)Math.floor(Math.random()*nm3.length);
                int rnd5=(int)Math.floor(Math.random()*nm11.length);
                int rnd6=(int)Math.floor(Math.random()*nm12.length);
                int rnd7=(int)Math.floor(Math.random()*nm7.length);
                int rnd8=(int)Math.floor(Math.random()*nm8.length);
                if(rnd7==0){
                    rnd8=0;
                }
                int rnd9=(int)Math.floor(Math.random()*nm13.length);
                int rnd10=(int)Math.floor(Math.random()*nm3.length);
                int rnd13=(int)Math.floor(Math.random()*nm15.length);
                int rnd12=(int)Math.floor(Math.random()*nm6.length);
                names=nm8[rnd8]+nm9[rnd]+nm3[rnd2]+nm10[rnd3]+nm7[rnd7]+nm3[rnd4]+nm11[rnd5]+nm12[rnd6]+" "+nm13[rnd9]+nm3[rnd10]+nm15[rnd13]+nm6[rnd12];
            }else{
                int rnd=(int)Math.floor(Math.random()*nm9.length);
                int rnd2=(int)Math.floor(Math.random()*nm3.length);
                int rnd3=(int)Math.floor(Math.random()*nm11.length);
                int rnd4=(int)Math.floor(Math.random()*nm12.length);
                int rnd5=(int)Math.floor(Math.random()*nm8.length);
                int rnd8=(int)Math.floor(Math.random()*nm13.length);
                int rnd9=(int)Math.floor(Math.random()*nm3.length);
                int rnd10=(int)Math.floor(Math.random()*nm14.length);
                int rnd11=(int)Math.floor(Math.random()*nm3.length);
                int rnd12=(int)Math.floor(Math.random()*nm15.length);
                int rnd13=(int)Math.floor(Math.random()*nm6.length);
                names=nm8[rnd5]+nm9[rnd]+nm3[rnd2]+nm11[rnd3]+nm12[rnd4]+" "+nm13[rnd8]+nm3[rnd9]+nm14[rnd10]+nm3[rnd11]+nm15[rnd12]+nm6[rnd13];
            }
        }
        return names;
    }
}