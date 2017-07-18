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

package com.project.hotel.hotels;

import com.google.common.collect.Lists;
import com.project.hotel.data.HotelsDataSource.LoadHotelCallback;
import com.project.hotel.data.HotelsRepository;
import com.project.hotel.model.Hotel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Elbehiry on 7/18/17.
 * Email: m.elbehiry44@gmail.com
 */

public class HotelsPresenterTest {
    private static List<Hotel> hotels;

    @Mock
    private HotelsRepository mHotelsRepository;

    @Mock
    private HotelContractor.View mHotelView;

    @Captor
    private ArgumentCaptor<LoadHotelCallback> mLoadHotelCallbackCaptor;

    private HotelPresenter mHotelPresenter;


    @Before
    public void setupHotelPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.

        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mHotelPresenter = HotelPresenter.getInstance(mHotelView, mHotelsRepository);

        // The presenter won't update the view unless it's active.
        when(mHotelView.isActive()).thenReturn(true);

        hotels = Lists.newArrayList(getNewHotel(1));


    }



    @Test
    public void loadAllHotelsFromRepositoryAndLoadIntoView(){
        // Given an initialized TasksPresenter with initialized tasks
        mHotelPresenter.loadHotels();

        // Callback is captured and invoked with stubbed tasks
        verify(mHotelsRepository).getHotels(mLoadHotelCallbackCaptor.capture());
        mLoadHotelCallbackCaptor.getValue().onHotelsLoaded(hotels);

        // Then progress indicator is shown but havn't yet
        //InOrder inOrder = inOrder(mHotelView);

        ArgumentCaptor<List> showHotelsArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mHotelView).showHotels(showHotelsArgumentCaptor.capture());
        //the size entered above
        assertTrue(showHotelsArgumentCaptor.getValue().size() == 1);


    }


    private Hotel getNewHotel(int num){
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














}
