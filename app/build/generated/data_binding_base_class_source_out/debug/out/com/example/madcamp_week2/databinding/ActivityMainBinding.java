// Generated by view binder compiler. Do not edit!
package com.example.madcamp_week2.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.madcamp_week2.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageButton googleLoginButton;

  @NonNull
  public final ImageButton kakaoLoginButton;

  @NonNull
  public final TextView loginText;

  @NonNull
  public final ImageButton naverLoginButton;

  private ActivityMainBinding(@NonNull LinearLayout rootView,
      @NonNull ImageButton googleLoginButton, @NonNull ImageButton kakaoLoginButton,
      @NonNull TextView loginText, @NonNull ImageButton naverLoginButton) {
    this.rootView = rootView;
    this.googleLoginButton = googleLoginButton;
    this.kakaoLoginButton = kakaoLoginButton;
    this.loginText = loginText;
    this.naverLoginButton = naverLoginButton;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.google_login_button;
      ImageButton googleLoginButton = ViewBindings.findChildViewById(rootView, id);
      if (googleLoginButton == null) {
        break missingId;
      }

      id = R.id.kakao_login_button;
      ImageButton kakaoLoginButton = ViewBindings.findChildViewById(rootView, id);
      if (kakaoLoginButton == null) {
        break missingId;
      }

      id = R.id.login_text;
      TextView loginText = ViewBindings.findChildViewById(rootView, id);
      if (loginText == null) {
        break missingId;
      }

      id = R.id.naver_login_button;
      ImageButton naverLoginButton = ViewBindings.findChildViewById(rootView, id);
      if (naverLoginButton == null) {
        break missingId;
      }

      return new ActivityMainBinding((LinearLayout) rootView, googleLoginButton, kakaoLoginButton,
          loginText, naverLoginButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
