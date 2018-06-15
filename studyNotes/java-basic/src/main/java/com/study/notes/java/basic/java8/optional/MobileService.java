package com.study.notes.java.basic.java8.optional;

import java.util.Optional;

public class MobileService {
    public Integer getMobileScreenWidth(Optional<Mobile> mobile){
        return mobile.flatMap(Mobile :: getDisplayFeatures)
                .flatMap(DisplayFeatures :: getResolution)
                .map(ScreenResolution :: getWidth)
                .orElse(0);
    }
}
