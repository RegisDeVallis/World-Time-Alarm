// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.google.appinventor.components.runtime.util.AnimationUtil;

// Referenced classes of package com.google.appinventor.components.runtime:
//            ListPicker

public class ListPickerActivity extends Activity
    implements android.widget.AdapterView.OnItemClickListener
{

    ArrayAdapter adapter;
    private String closeAnim;
    private ListView listView;
    EditText txtSearchBox;

    public ListPickerActivity()
    {
        closeAnim = "";
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        LinearLayout linearlayout = new LinearLayout(this);
        linearlayout.setOrientation(1);
        Intent intent = getIntent();
        if (intent.hasExtra(ListPicker.LIST_ACTIVITY_ANIM_TYPE))
        {
            closeAnim = intent.getStringExtra(ListPicker.LIST_ACTIVITY_ANIM_TYPE);
        }
        if (intent.hasExtra(ListPicker.LIST_ACTIVITY_TITLE))
        {
            setTitle(intent.getStringExtra(ListPicker.LIST_ACTIVITY_TITLE));
        }
        if (intent.hasExtra(ListPicker.LIST_ACTIVITY_ARG_NAME))
        {
            String as[] = getIntent().getStringArrayExtra(ListPicker.LIST_ACTIVITY_ARG_NAME);
            listView = new ListView(this);
            listView.setOnItemClickListener(this);
            adapter = new ArrayAdapter(this, 0x1090003, as);
            listView.setAdapter(adapter);
            String s = intent.getStringExtra(ListPicker.LIST_ACTIVITY_SHOW_SEARCH_BAR);
            txtSearchBox = new EditText(this);
            txtSearchBox.setSingleLine(true);
            txtSearchBox.setWidth(-2);
            txtSearchBox.setPadding(10, 10, 10, 10);
            txtSearchBox.setHint("Search list...");
            if (s == null || !s.equalsIgnoreCase("true"))
            {
                txtSearchBox.setVisibility(8);
            }
            txtSearchBox.addTextChangedListener(new TextWatcher() {

                final ListPickerActivity this$0;

                public void afterTextChanged(Editable editable)
                {
                }

                public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
                {
                }

                public void onTextChanged(CharSequence charsequence, int i, int j, int k)
                {
                    adapter.getFilter().filter(charsequence);
                }

            
            {
                this$0 = ListPickerActivity.this;
                super();
            }
            });
        } else
        {
            setResult(0);
            finish();
            AnimationUtil.ApplyCloseScreenAnimation(this, closeAnim);
        }
        linearlayout.addView(txtSearchBox);
        linearlayout.addView(listView);
        setContentView(linearlayout);
        linearlayout.requestLayout();
        ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        getWindow().setSoftInputMode(3);
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        String s = (String)adapterview.getAdapter().getItem(i);
        Intent intent = new Intent();
        intent.putExtra(ListPicker.LIST_ACTIVITY_RESULT_NAME, s);
        intent.putExtra(ListPicker.LIST_ACTIVITY_RESULT_INDEX, i + 1);
        closeAnim = s;
        setResult(-1, intent);
        finish();
        AnimationUtil.ApplyCloseScreenAnimation(this, closeAnim);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            boolean flag = super.onKeyDown(i, keyevent);
            AnimationUtil.ApplyCloseScreenAnimation(this, closeAnim);
            return flag;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
    }
}
