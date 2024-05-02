package com.sopt.now.presentation.main.mypage

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.model.SignUpData
import com.sopt.now.model.info.ResponseGetInfoDto
import com.sopt.now.model.info.UserInfo
import com.sopt.now.utils.Constants.Companion.MEMBER_ID
import com.sopt.now.utils.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = requireNotNull(_binding)

    //private var userData: SignUpData? = null
    private var memberId: String = ""
    private val infoService by lazy { ServicePool.infoService }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        memberId = getMemberId()
        getUserInfo()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun getMemberId(): String {
        return arguments?.getString(MEMBER_ID)!!
    }

    private fun getUserInfo() {
        // 서버 통신
        Toast.makeText(context, "memberID $memberId", Toast.LENGTH_SHORT).show()

        infoService.getUserInfo(memberId).enqueue(object : Callback<ResponseGetInfoDto> {
            override fun onResponse(
                call: Call<ResponseGetInfoDto>,
                response: Response<ResponseGetInfoDto>
            ) {
                if (response.isSuccessful) {
                    val result = response.body() as ResponseGetInfoDto
                    showUserInfo(result.data)
                }
            }

            override fun onFailure(call: Call<ResponseGetInfoDto>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        /*with(binding) {
            tvMainUserNickname.text = userData?.nickname
            tvMainUserMbti.text = userData?.mbti
            tvMainUserId.text = userData?.id
            tvMainUserPw.text = userData?.password
        }*/
    }

    private fun showUserInfo(data: UserInfo) {
        with(binding) {
            tvMainUserNickname.text = data.nickname
            tvMainUserId.text = data.authenticationId
            tvMainUserPhone.text = data.phone
        }
    }
}