package com.mahditaz.contactsmanager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class SearchHolder {
    private MutableLiveData<String> search = new MutableLiveData<>();

    public SearchHolder(){
        search.setValue("");
    }

    public LiveData<String> getLiveSearch(){return search;}
    public String getSearch() {return search.getValue();}
    public void setSearch(String search) {this.search.setValue(search);}
}
