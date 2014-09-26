/*
 * Copyright (C) 2014 Vlad Mihalachi
 *
 * This file is part of Turbo Editor.
 *
 * Turbo Editor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Turbo Editor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.maskyn.fileeditor;

import android.app.Activity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Calendar;

import sharedcode.turboeditor.preferences.PreferenceHelper;

public class AdsHelper {
    private Activity activity;
    private InterstitialAd interstitial;

    public AdsHelper(Activity activity) {
        this.activity = activity;
        int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int lastDayAdShowed = PreferenceHelper.getLastDayAdShowed(activity);
        boolean showAd = today != lastDayAdShowed;
        if (showAd) {

            interstitial = new InterstitialAd(activity);
            interstitial.setAdUnitId("ca-app-pub-5679083452234719/7178038180");

            // Create ad request.
            AdRequest adRequest = new AdRequest.Builder().build();

            // Begin loading your interstitial.
            interstitial.loadAd(adRequest);
        }
    }

    public void displayInterstitial() {
        if (interstitial != null && interstitial.isLoaded()) {
            interstitial.show();
            int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            PreferenceHelper.setLastDayAdShowed(activity, today);
        }
    }
}