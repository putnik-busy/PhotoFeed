package com.justapp.photofeed.presentation.base;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.justapp.photofeed.utils.ComponentUtils;

/**
 * @author Sergey Rodionov
 */
public class BaseFragment extends MvpAppCompatFragment {

    protected <C> C getComponent(Class<C> componentType) {
        return ComponentUtils.getComponent(getActivity(), componentType);
    }

}
