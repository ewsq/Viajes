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

package com.project.hotel.data;

import android.content.Context;

import com.google.common.collect.Lists;
import com.project.hotel.model.Hotel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by Elbehiry on 7/18/17.
 * Email: m.elbehiry44@gmail.com
 */

public class HotelRepositoryTest {

    private static List<Hotel> TASKS = Lists.newArrayList(getNewHotel(1));

    private HotelsRepository mHotelsRepository;

//    @Mock
//    private HotelsDataSource mHotelsRemoteDataSource;

    @Mock
    private Context context;

    @Mock
    private HotelsDataSource.LoadHotelCallback mLoadHotelCallback;

    @Captor
    private ArgumentCaptor<HotelsDataSource.LoadHotelCallback> mHotelCallbackCaptor;


    @Before
    public void setupHotelsRepository(){
        MockitoAnnotations.initMocks(this);
        mHotelsRepository = HotelsRepository.getInstance();

    }

    @Test
    public void getHotels_afterfirstApiCall(){
        twoHotelsLoadCallsToRepository(mLoadHotelCallback);
        //will complete it later :)
    }


    @After
    public void destroyRepositoryInstance(){
        HotelsRepository.DestroyInstance();
    }
    private static Hotel getNewHotel(int num){
        Hotel hotel = new Hotel();
        HashMap<String, String> mUploadedImages = new HashMap<>();
        mUploadedImages.put("image1","http://i.imgur.com/DvpvklR.png");
        mUploadedImages.put("image2","http://i.imgur.com/DvpvklR.png");
        mUploadedImages.put("image3","http://i.imgur.com/DvpvklR.png");
        mUploadedImages.put("image4","http://i.imgur.com/DvpvklR.png");
        switch (num){
            case 1:
                hotel.setName("Hotel Name");
                hotel.setUid("1234");
                hotel.setBreakfast(true);
                hotel.setBeach(false);
                hotel.setGym(true);
                hotel.setLat(27.257895700000002);
                hotel.setLon(33.8116067);
                hotel.setPets(true);
                hotel.setPhone("01066770250");
                hotel.setPool(false);
                hotel.setPrice(1300);
                hotel.setRate(4);
                hotel.setResturant(false);
                hotel.setSpa(true);
                hotel.setWebsite("www.website.com");
                hotel.setWifi(true);
                hotel.setImagesUrls(mUploadedImages);
        }


        return hotel;
    }

    /**
     * Convenience method that issues two calls to the tasks repository
     */
    private void twoHotelsLoadCallsToRepository(HotelsDataSource.LoadHotelCallback callback) {
        // When tasks are requested from repository
        //first call
        mHotelsRepository.getHotels(callback);

        mHotelsRepository.getHotels(callback); // Second call to API
    }

}
