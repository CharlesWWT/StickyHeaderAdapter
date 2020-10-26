package com.test.kutesmart.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.rvContacts)
    RecyclerView rvContacts;
    ArrayList<ContactsBean> contactLists = new ArrayList<>();
    private PhoneContactAdapter contactAdapter;
    private LinearLayoutManager manager;
    private StickyHeaderDecoration decoration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        for(int i = 0; i < 10; i++) {
          ContactsBean bean=new ContactsBean();
          bean.setPinyinFirst("2020-6-11");
          bean.setName("itemA"+i);
          contactLists.add(bean);
        }
        for(int i = 0; i < 10; i++) {
            ContactsBean bean=new ContactsBean();
            bean.setPinyinFirst("2020-6-12");
            bean.setName("itemB"+i);
            contactLists.add(bean);
        }
        manager = new LinearLayoutManager(this);
        contactAdapter = new PhoneContactAdapter(MainActivity.this, contactLists);
        rvContacts.setLayoutManager(manager);
        rvContacts.setHasFixedSize(true);
        decoration = new StickyHeaderDecoration(contactAdapter);
        rvContacts.setAdapter(contactAdapter);
        rvContacts.addItemDecoration(decoration);
    }
}
