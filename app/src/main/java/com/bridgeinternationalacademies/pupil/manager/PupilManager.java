package com.bridgeinternationalacademies.pupil.manager;

import com.bridgeinternationalacademies.pupil.callback.GenericCallback;
import com.bridgeinternationalacademies.pupil.model.Classroom;
import com.bridgeinternationalacademies.pupil.model.Pupil;
import com.bridgeinternationalacademies.pupil.network.PupilService;
import com.bridgeinternationalacademies.pupil.network.ServiceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sushantchavan on 14/04/17.
 */

public class PupilManager {
private static  final ServiceManager api = new ServiceManager();

    public void getListOfPupils(int num, GenericCallback<Classroom> callback) {
        ServiceManager sm = new ServiceManager();
        sm.getPupils(num, callback);
    }
}
