/*
 * Copyright (C) 2015 The Nevolution Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oasisfeng.nevo.decorators.bundle;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.oasisfeng.nevo.decorators.bundle.R;

/**
 * Created by Richard on 2015/9/12.
 */
public class BundleActionDialog extends BaseBundleDialog {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getTitle() == null) {
            Toast.makeText(getActivity(), R.string.hint_title_not_recognized, Toast.LENGTH_LONG).show();
            getActivity().finish();
            return null;
        }
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    protected void onSetRemoveText(TextView textView) {
        if (TextUtils.isEmpty(getTitleRule())) {
            if (TextUtils.isEmpty(getPackageRule())) textView.setVisibility(View.GONE);
            else {
                textView.setText(getString(R.string.bundle_remove_rule, getPackageRule()));
            }
        } else {
            textView.setText(getString(R.string.bundle_remove_rule, getTitleRule()));
        }
    }

    @Override
    protected void onRemove() {
        setBundleRule(getPackage(), getTitle(), "");
    }

    @Override
    protected void onApply(String newBundle) {
        setBundleRule(getPackage(), getTitle(), newBundle);
        dismiss();
    }

    @Override
    protected String getDialogTitle() {
        return getString(R.string.bundle_action_title_primary, getAppName(), getTitle());
    }

    @Override
    protected String getCurrentBundle() {
        return TextUtils.isEmpty(getTitleRule()) ? getPackageRule() : getTitleRule();
    }
}
