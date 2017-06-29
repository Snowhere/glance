package service;

import Entity.AdEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * code
 *
 * @author STH
 * @create 2017-06-02
 **/
public class AdService {
    public List<AdEntity> getAd() {
        List<AdEntity> ads = new ArrayList<>();
        ads.add(new AdEntity("http://game.snowhere.me/1010","游戏","/img/ad1.jpg"));
        ads.add(new AdEntity("http://say.zzubox.com/","聊天","/img/ad2.jpg"));
        return ads;
    }
}
