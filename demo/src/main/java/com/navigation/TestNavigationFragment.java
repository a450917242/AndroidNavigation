package com.navigation;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import me.listenzz.navigation.AppUtils;
import me.listenzz.navigation.AwesomeFragment;
import me.listenzz.navigation.BarStyle;
import me.listenzz.navigation.DrawableUtils;
import me.listenzz.navigation.DrawerFragment;
import me.listenzz.navigation.NavigationFragment;
import me.listenzz.navigation.Style;
import me.listenzz.navigation.TabBarFragment;


/**
 * Created by Listen on 2018/1/11.
 */

public class TestNavigationFragment extends AwesomeFragment {

    public static String fromCharCode(int... codePoints) {
        return new String(codePoints, 0, codePoints.length);
    }

    private static final int REQUEST_CODE = 1;

    TextView resultText;

    EditText resultEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_test, container, false);

        resultText = root.findViewById(R.id.result_text);
        resultEditText = root.findViewById(R.id.result);

        TextView tagView = root.findViewById(R.id.tag);
        tagView.setText(getDebugTag());

        root.findViewById(R.id.present).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationFragment navigationFragment = new NavigationFragment();
                navigationFragment.setRootFragment(new TestNavigationFragment());
                presentFragment(navigationFragment, REQUEST_CODE);
            }
        });

        root.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle result = new Bundle();
                result.putString("text", resultEditText.getText().toString());
                setResult(Activity.RESULT_OK, result);
                dismissFragment();
            }
        });

        root.findViewById(R.id.push).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationFragment navigationFragment = getNavigationFragment();
                if (navigationFragment != null) {
                    navigationFragment.pushFragment(new TestNavigationFragment());
                }
            }
        });

        root.findViewById(R.id.pop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationFragment navigationFragment = getNavigationFragment();
                if (navigationFragment != null) {
                    Bundle result = new Bundle();
                    result.putString("text", resultEditText.getText().toString());
                    setResult(Activity.RESULT_OK, result);
                    navigationFragment.popFragment();
                }
            }
        });

        root.findViewById(R.id.pop_to_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationFragment navigationFragment = getNavigationFragment();
                if (navigationFragment != null) {
                    Bundle result = new Bundle();
                    result.putString("text", resultEditText.getText().toString());
                    setResult(Activity.RESULT_OK, result);
                    navigationFragment.popToRootFragment();
                }
            }
        });

        root.findViewById(R.id.replace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationFragment navigationFragment = getNavigationFragment();
                if (navigationFragment != null) {
                    navigationFragment.replaceFragment(new TestNavigationFragment());
                }
            }
        });

        root.findViewById(R.id.replace_to_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationFragment navigationFragment = getNavigationFragment();
                if (navigationFragment != null) {
                    navigationFragment.replaceToRootFragment(new TestNavigationFragment());
                }
            }
        });

        root.findViewById(R.id.toggle_tab_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabBarFragment tabBarFragment = getTabBarFragment();
                if (tabBarFragment != null) {
                    tabBarFragment.toggleBottomBar();
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        AppUtils.hideSoftInput(resultEditText);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle("导航");
        if (isNavigationRoot()) {
            Drawable icon = DrawableUtils.fromFont(getContext(), "FontAwesome", fromCharCode(61641), 24, -1 );
            setToolbarLeftButton(icon, "Menu", true, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DrawerFragment drawerFragment = getDrawerFragment();
                    if (drawerFragment != null) {
                        drawerFragment.toggleMenu();
                    }
                }
            });
        }

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode != 0) {
                String text = data.getString("text", "");
                resultText.setText("present result：" + text);
            } else {
                resultText.setText("ACTION CANCEL");
            }
        } else {
            if (resultCode != 0) {
                String text = data.getString("text", "");
                resultText.setText("pop result：" + text);
            }
        }
    }

    @Override
    protected void onCustomStyle(Style style) {
        style.setToolbarStyle(BarStyle.DarkContent);
    }

}
