package com.example.cidemo.injust;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wenxiaokang
 * @className ViewBind
 * @description TODO
 * @date 6/21/21 2:08 PM
 */
public class ViewBind {

    @NonNull
    @UiThread
    public static void bind(@NonNull Activity activity) {
        findViewById(activity);
        onClick(activity);

    }

    private static void onClick(Activity activity) {

        Class<?> clazz = activity.getClass();
        try {
            final Method method = clazz.getMethod("onClick", View.class);
            OnClick onClick = method.getAnnotation(OnClick.class);
            if (onClick != null) {
                int[] viewIds = onClick.value();
                for (int viewId : viewIds) {
                    final View view = activity.findViewById(viewId);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                method.setAccessible(true);
                                method.invoke(activity);
                            } catch (Exception e) {
                                e.printStackTrace();
                                try {
                                    method.invoke(activity, view);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Class<? extends Activity> aClass = activity.getClass();
//        try {
//            Method onClick = aClass.getMethod("onClick", View.class);
//            OnClick annotation = onClick.getAnnotation(OnClick.class);
//            if (annotation != null) {
//                for (int viewId : annotation.value()) {
//                    View viewById = activity.findViewById(viewId);
//                    viewById.setOnClickListener(v -> {
//                        onClick.setAccessible(true);
//                        try {
//                            onClick.invoke(activity);
//                        } catch (IllegalAccessException e) {
//                            try {
//                                onClick.invoke(activity, viewById);
//                            } catch (IllegalAccessException illegalAccessException) {
//                                illegalAccessException.printStackTrace();
//                            } catch (InvocationTargetException invocationTargetException) {
//                                invocationTargetException.printStackTrace();
//                            }
//                            e.printStackTrace();
//                        } catch (InvocationTargetException e) {
//                            e.printStackTrace();
//                        }
//                    });
//
//                }
//            }
//        } catch (
//                NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//
//        Method[] methods = aClass.getMethods();


    }

    private static void findViewById(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            BindView annotation = field.getAnnotation(BindView.class);
            if (annotation != null) {
                View viewById = activity.findViewById(annotation.value());
                field.setAccessible(true);
                try {
                    field.set(activity, viewById);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
