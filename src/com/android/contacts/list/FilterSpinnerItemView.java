/*
 * Copyright (C) 2010 The Android Open Source Project
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

package com.android.contacts.list;

import com.android.contacts.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Contact list filter parameters.
 */
public final class FilterSpinnerItemView extends LinearLayout {

    private ImageView mIcon;
    private TextView mLabel;
    private TextView mIndentedLabel;
    private ContactListFilter mFilter;

    public FilterSpinnerItemView(Context context) {
        super(context);
    }

    public FilterSpinnerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setContactListFilter(ContactListFilter filter) {
        mFilter = filter;
    }

    public ContactListFilter getContactListFilter() {
        return mFilter;
    }

    public void bindView(boolean dropdown) {
        if (mIcon == null) {
            mIcon = (ImageView) findViewById(R.id.icon);
            mLabel = (TextView) findViewById(R.id.label);
            mIndentedLabel = (TextView) findViewById(R.id.indented_label);
        }

        switch (mFilter.filterType) {
            case ContactListFilter.FILTER_TYPE_ALL_ACCOUNTS: {
                mIcon.setVisibility(View.GONE);
                mLabel.setText(R.string.list_filter_all_accounts);
                mLabel.setVisibility(View.VISIBLE);
                if (dropdown) {
                    mIndentedLabel.setVisibility(View.GONE);
                }
                break;
            }
            case ContactListFilter.FILTER_TYPE_CUSTOM: {
                mIcon.setVisibility(View.GONE);
                mLabel.setText(dropdown
                        ? R.string.list_filter_customize
                        : R.string.list_filter_custom);
                mLabel.setVisibility(View.VISIBLE);
                if (dropdown) {
                    mIndentedLabel.setVisibility(View.GONE);
                }
                break;
            }
            case ContactListFilter.FILTER_TYPE_ACCOUNT: {
                mIcon.setVisibility(View.VISIBLE);
                if (mFilter.icon != null) {
                    mIcon.setImageDrawable(mFilter.icon);
                } else {
                    mIcon.setImageResource(R.drawable.unknown_source);
                }
                mLabel.setText(mFilter.accountName);
                mLabel.setVisibility(View.VISIBLE);
                if (dropdown) {
                    mIndentedLabel.setVisibility(View.GONE);
                }
                break;
            }
            case ContactListFilter.FILTER_TYPE_GROUP: {
                mIcon.setVisibility(View.GONE);
                if (dropdown) {
                    mLabel.setVisibility(View.GONE);
                    mIndentedLabel.setText(mFilter.title);
                    mIndentedLabel.setVisibility(View.VISIBLE);
                } else {
                    mLabel.setText(mFilter.title);
                    mLabel.setVisibility(View.VISIBLE);
                }
                break;
            }
        }
    }
}
