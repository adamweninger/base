package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorTest {
    TrainUser mockUser;
    TrainController mockController;
    TrainSensorImpl sensor;

    @Before
    public void before() {
        mockController = mock(TrainController.class);
        mockUser = mock(TrainUser.class);

        sensor = new TrainSensorImpl(mockController, mockUser);

		sensor.overrideSpeedLimit(50);
    }

    @Test
    public void Test_Alarm_LessThanZero() {
        when(mockUser.getAlarmFlag()).thenReturn(true);
        sensor.overrideSpeedLimit(-1);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    @Test
    public void Test_Alarm_MoreThanFiveHundred() {
        when(mockUser.getAlarmFlag()).thenReturn(true);
        sensor.overrideSpeedLimit(555);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    @Test
    public void Test_Alarm_BadToReference() {
        when(mockUser.getAlarmFlag()).thenReturn(true);
        sensor.overrideSpeedLimit(10);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    @Test
    public void Test_Alarm_NoAlarm() {
        when(mockUser.getAlarmFlag()).thenReturn(false);
        sensor.overrideSpeedLimit(40);
        verify(mockUser, times(1)).setAlarmState(false);
    }
}
