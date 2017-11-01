/*
 * Copyright 2014 Jan Kühle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.kuehle.carreport.data.calculation;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import me.kuehle.carreport.Preferences;
import me.kuehle.carreport.R;

public class DistanceToVolumeCalculation extends AbstractVolumeDistanceCalculation {
    public DistanceToVolumeCalculation(Context context) {
        super(context);
    }

    @Override
    public String getName() {
        return mContext.getString(R.string.calc_option_distance_to_volume,
                getInputUnit(), getOutputUnit());
    }

    @Override
    public String getInputUnit() {
        Preferences prefs = new Preferences(mContext);
        return prefs.getUnitDistance();
    }

    @Override
    public String getOutputUnit() {
        Preferences prefs = new Preferences(mContext);
        return prefs.getUnitVolume();
    }

    @Override
    protected CalculationItem[] onCalculate(double input) {
        List<CalculationItem> items = new ArrayList<>();

        for (int i = 0; i < mNames.size(); i++) {
            String name = mNames.get(i);
            double avgConsumption = mAvgConsumptions.get(i);
            int color = mColors.get(i);

            items.add(new CalculationItem(name, input * avgConsumption, color));
        }

        return items.toArray(new CalculationItem[items.size()]);
    }
}
