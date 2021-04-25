package business_logic.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;

public class TestJobIntentService extends IntentService
{
    public TestJobIntentService(){
        super("BackgroundService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("I'm running on the IntentService thread");
        Log.i("TEST_INTENT_SERVICE", "HELLO THERE FROM INTENT SERVICE");
    }
}
