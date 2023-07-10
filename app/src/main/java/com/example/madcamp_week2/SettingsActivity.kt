package com.example.madcamp_week2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK

class SettingsActivity : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 101
    private lateinit var kakaoLogoutButton: Button
    private lateinit var kakaoUnlinkButton: Button
    private lateinit var addprofileImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val rootView = layoutInflater.inflate(R.layout.activity_settings, null)

        val crewSpinner = rootView.findViewById<Spinner>(R.id.spinner_settings_crew)
        val crews = arrayOf("group1", "group2", "group3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, crews)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        crewSpinner.adapter = adapter

        crewSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedGroup = parent.getItemAtPosition(position).toString()
                // 선택된 그룹을 처리하는 로직을 여기에 작성하세요.
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 선택이 해제될 때의 처리 로직을 여기에 작성하세요.
            }
        }
        addprofileImage = findViewById(R.id.addprofileImg)
        addprofileImage.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        kakaoLogoutButton = findViewById(R.id.kakao_logout_button)
        kakaoUnlinkButton = findViewById(R.id.kakao_unlink_button)
        kakaoLogoutButton.setOnClickListener {
            // Check if the user is logged in with Kakao
            UserApiClient.instance.me { user, error ->
                if (user != null) {
                    // Logged in with Kakao, so log out from Kakao
                    UserApiClient.instance.logout { error ->
                        if (error != null) {
                            Toast.makeText(this, "카카오 로그아웃 실패 $error", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "카카오 로그아웃 성공", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    // Not logged in with Kakao, try Naver
                    val naverAccessToken = NaverIdLoginSDK.getAccessToken()
                    if (naverAccessToken != null) {
                        // Logged in with Naver, so log out from Naver
                        NaverIdLoginSDK.logout()
                        Toast.makeText(this, "네이버 로그아웃 성공", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }

        // The same logic for the unlink button
        kakaoUnlinkButton.setOnClickListener {
            // Check if the user is logged in with Kakao
            UserApiClient.instance.me { user, error ->
                if (user != null) {
                    // Logged in with Kakao, so unlink from Kakao
                    UserApiClient.instance.unlink { error ->
                        if (error != null) {
                            Toast.makeText(this, "카카오 회원 탈퇴 실패 $error", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "카카오 회원 탈퇴 성공", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    // Not logged in with Kakao, try Naver
                    val naverAccessToken = NaverIdLoginSDK.getAccessToken()
                    if (naverAccessToken != null) {
                        // Logged in with Naver, so unlink from Naver
                        // Naver does not provide a direct unlink function, but you can invalidate the access token
                        NaverIdLoginSDK.logout()
                        Toast.makeText(this, "네이버 회원 탈퇴 성공", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val uri = data.data
            Glide.with(this)
                .load(uri)
                .into(addprofileImage)
            // Here you can use the uri to create a File object, open an input stream, etc.
            // ...
        }
    }
}