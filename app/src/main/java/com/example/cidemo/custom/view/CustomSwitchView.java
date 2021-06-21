package com.example.cidemo.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.cidemo.R;

import java.lang.reflect.Method;

/**
 * @author wenxiaokang
 * @className CustomSwithView
 * @description TODO
 * @date 6/18/21 2:14 PM
 */
public class CustomSwitchView extends ConstraintLayout {
    private SwitchCompat mySwitch;

    public CustomSwitchView(Context context) {
        super(context);
        init(context);
    }

    public CustomSwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public CustomSwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        View inflate = inflate(context, R.layout.my_switch, this);
        mySwitch = inflate.findViewById(R.id.switch1);
    }


    public void setUpWithViewPager(final ViewPager viewPager) {
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    float newPos = constrain(positionOffset, 0, 1);
                    try {
                        if (newPos != 0.0f && newPos != 1f) {
                            //通过反射修改Switch的UI
                            Class<SwitchCompat> switchClass = SwitchCompat.class;
                            Method setThumbPosition = switchClass.getDeclaredMethod("setThumbPosition", float.class);
                            setThumbPosition.setAccessible(true);
                            setThumbPosition.invoke(mySwitch, newPos);
                        }
                        CustomSwitchView.this.invalidate();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onPageSelected(int position) {
                }
                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
            mySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> viewPager.setCurrentItem(isChecked ? 1 : 0));
        } else {
            throw new NullPointerException("viewPager不能为空");
        }

    }


    private float constrain(float amount, float low, float high) {
        return amount < low ? low : (amount > high ? high : amount);
    }
}
