package com.imooc.util;

/**
 * 帖子排名算法,Reddit算法
 */

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Reddit {

    public static final long BASE_TIME = ZonedDateTime.parse("2015-01-01T00:00:00.0+08:00[Asia/Shanghai]").toEpochSecond();

    private static long age(ZonedDateTime date) {
        return date.toEpochSecond() - BASE_TIME;
    }

    private static int score(int ups, int downs) {
        return ups - downs;
    }

    public static double hot(int ups, int downs, ZonedDateTime date, int share) {
        int score = score(ups, downs);
        double distance = Math.log10(Math.max(Math.abs(score), 1));  //赞成票与反对票的差额越大，得分越高。
        double shareRank = Math.log(share) / Math.log(2);     //分享越多得分越高
        int sign = score > 0 ? 1 : (score < 0 ? -1 : 0);    //产生加分或减分
        return distance + shareRank + (double) (sign * age(date)) / 45000.0;   //相同情况下,新帖子的得分会高于老帖子。
    }

    public static void main(String[] args) {
        ZoneId zone = ZoneId.of("Europe/Paris");
        LocalDate date = LocalDate.of(2015, Month.AUGUST, 19);
        double hoter = hot(1000, 10, date.atStartOfDay(zone), 100);
        System.out.print(hoter);
    }
}