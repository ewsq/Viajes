/*
 *
 *  * Copyright 2017, The Android Open Source Project
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.project.hotel;

import android.support.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.project.hotel.data.HotelsDataSource;
import com.project.hotel.model.Hotel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Elbehiry on 7/18/17.
 * Email: m.elbehiry44@gmail.com
 */

public class FakeHotelsDataSource implements HotelsDataSource {
    private static final Map<String, Hotel> HOTELS_SERVICE_DATA = new LinkedHashMap<>();

    private static FakeHotelsDataSource Instance = null;
    private FakeHotelsDataSource(){}
    public static FakeHotelsDataSource getInstance(){
        if (Instance == null){
            Instance = new FakeHotelsDataSource();
        }
        return Instance;
    }


    @Override
    public void getHotels(@Nullable LoadHotelCallback callback) {
        callback.onHotelsLoaded(Lists.newArrayList(HOTELS_SERVICE_DATA.values()));
    }

    @Override
    public void saveHotel(@Nullable Hotel hotel) {
        HOTELS_SERVICE_DATA.put(hotel.getItemId(),hotel);
    }

    @Override
    public void seachName(@Nullable String name, @Nullable LoadHotelCallback callback) {
        if (HOTELS_SERVICE_DATA != null && HOTELS_SERVICE_DATA.size() >0){
            name = name.toLowerCase();
            ArrayList<Hotel> filteredList = new ArrayList<>();
            Iterator<Map.Entry<String, Hotel>> it = HOTELS_SERVICE_DATA.entrySet().iterator();
            while (it.hasNext()){
                Map.Entry<String,Hotel> hotel = it.next();
                if (hotel.getValue().getName().contains(name)){
                    filteredList.add(hotel.getValue());
                }
            }
            if (filteredList.size() > 0 ){
                callback.onHotelsLoaded(filteredList);
            }
            else {
                callback.onDataNotAvailable();
            }


        }
    }

    @Override
    public void searchData(@Nullable Hotel hotel, @Nullable LoadHotelCallback callback) {
        if (HOTELS_SERVICE_DATA != null && HOTELS_SERVICE_DATA.size() >0) {
            ArrayList<Hotel> filteredList = new ArrayList<>();
            Iterator<Map.Entry<String, Hotel>> it = HOTELS_SERVICE_DATA.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String,Hotel> hotelIt = it.next();
                Hotel mHotel = hotelIt.getValue();
                if (hotel.getPrice() < mHotel.getPrice() && hotel.isWifi() == mHotel.isWifi() && hotel.isPool() == mHotel.isPool()
                        && hotel.isSpa() == mHotel.isSpa() && hotel.isPets() == mHotel.isPets() && hotel.isGym() == mHotel.isGym() &&
                        hotel.isResturant() == mHotel.isResturant() && hotel.isBreakfast() == mHotel.isBreakfast()
                        && hotel.isBeach() == mHotel.isBeach()) {
                    filteredList.add(mHotel);
                }

            }
            if (filteredList.size() > 0 ){
                callback.onHotelsLoaded(filteredList);
            }
            else {
                callback.onDataNotAvailable();
            }
        }
    }

    @Override
    public void setLiked(boolean liked, Hotel hotel) {

    }




}
