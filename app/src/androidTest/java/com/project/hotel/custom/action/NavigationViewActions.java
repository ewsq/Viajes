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

package com.project.hotel.custom.action;

import android.content.res.Resources;
import android.support.design.widget.NavigationView;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.util.HumanReadables;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Elbehiry on 7/18/17.
 * Email: m.elbehiry44@gmail.com
 */

public class NavigationViewActions {

    // no Instance
    private NavigationViewActions(){

    }


    public static ViewAction searchTo(final  int menuItemId){

        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                NavigationView navigationView = (NavigationView) view;
                Menu menu = navigationView.getMenu();
                if (null == menu.findItem(menuItemId)){
                    throw new PerformException.Builder()
                            .withActionDescription(this.getDescription())
                            .withViewDescription(HumanReadables.describe(view))
                            .withCause(new RuntimeException(getErrorMessage(menu,view)))
                            .build();
                }
                menu.performIdentifierAction(menuItemId, 0);
                uiController.loopMainThreadUntilIdle();
            }
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(NavigationView.class),
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        isDisplayingAtLeast(90)
                );
            }

            @Override
            public String getDescription() {
                return "click on menu item with id";
            }



            private String getErrorMessage(Menu menu, View view) {
                String NEW_LINE = System.getProperty("line.separator");
                StringBuilder errorMessage = new StringBuilder("Menu item was not found,available menu items:")
                        .append(NEW_LINE);
                for (int position = 0; position < menu.size(); position++) {
                    errorMessage.append("[MenuItem] position=")
                            .append(position);
                    MenuItem menuItem = menu.getItem(position);
                    if (menuItem != null) {
                        CharSequence itemTitle = menuItem.getTitle();
                        if (itemTitle != null) {
                            errorMessage.append(", title=")
                                    .append(itemTitle);
                        }
                        if (view.getResources() != null) {
                            int itemId = menuItem.getItemId();
                            try {
                                errorMessage.append(", id=");
                                String menuItemResourceName = view.getResources()
                                        .getResourceName(itemId);
                                errorMessage.append(menuItemResourceName);
                            } catch (Resources.NotFoundException nfe) {
                                errorMessage.append("not found");
                            }
                        }
                        errorMessage.append(NEW_LINE);
                    }
                }
                return errorMessage.toString();
            }


        };
    }



}
