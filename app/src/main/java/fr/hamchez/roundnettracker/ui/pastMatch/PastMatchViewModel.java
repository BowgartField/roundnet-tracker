package fr.hamchez.roundnettracker.ui.pastMatch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PastMatchViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PastMatchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}